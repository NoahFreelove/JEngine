package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;

import javax.swing.*;
import java.awt.*;

// Convert objects in the viewport to a visual representation
// Requires JViewport
public class JCamera extends Object {
    public int fov;
    private JScene scene;
    public JWindow window;
    public Object parent;
    public Object[] objectsInView;
    public JCamera(Vector3 position, JWindow window, JScene scene, Object parent, int fov, JIdentity JIdentity) {
        super(parent.transform, JIdentity);

        if(parent.transform == null){
            LogWarning(String.format("Camera: '%s' parent property is null. The camera will not move.", JIdentity.getName()));
            super.transform = new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1));
        }

        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fov = fov;
    }
    @Override
    public void Update(){}

    public void setParent(Object newParent) {parent = newParent;}
    public Object getParent() {return parent;}

    public void InitiateRender()
    {
        GetObjectsInView();
    }

    private void GetObjectsInView()
    {
        if(parent.transform != null)
        {
            super.transform.position = parent.transform.position;
        }

        int leftBound = (int)transform.position.x - fov;
        int rightBound = (int)transform.position.x + fov;
        int upBound = (int)transform.position.y + fov;
        int downBound = (int)transform.position.y - fov;

        objectsInView = new Object[scene.getMaxObjects()];

        int i = 0;

        for (ObjRef obj: scene.sceneObjects) {
            if(obj==null)
            {
                LogError("Tried to get object that doesn't exist! Try lowering your maxObjects parameter");
                continue;
            }
            try {
                Sprite objSprite = (Sprite) obj.objRef;
                if(((objSprite.transform.getPosition().x+objSprite.getSprite().getXSize()) >= leftBound && obj.objRef.transform.getPosition().x <=rightBound) && (objSprite.transform.getPosition().y+objSprite.getSprite().getYSize()) >= downBound && obj.objRef.transform.getPosition().y <=upBound)
                {
                    //System.out.println(obj.objRef.identity.getName() + "InRange");
                    objectsInView[i] = objSprite;
                }
            }
            catch (Exception ignore)
            {
                if((obj.objRef.transform.getPosition().x >= leftBound && obj.objRef.transform.getPosition().x <=rightBound))
                {
                    objectsInView[i] = obj.objRef;
                }
            }
            i++;
        }
        Render();
    }

    private void Render()
    {
        JPanel panel = (JPanel) window.getWindow().getContentPane();
        panel.removeAll();
        LogExtra("Start Render");
        for (int i = 0; i < objectsInView.length; i++) {
            LogExtra("Render: " + i);
            if (objectsInView[i] == null) {
                continue;
            }

            // make sure we don't render inactive things
            if (!objectsInView[i].getActive()) {
                continue;
            }
            //System.out.println("Object: " + objectsInView[i].identity.getName() + " : " + objectsInView[i].getClass().);
            try
            {
                Sprite s = (Sprite)objectsInView[i];
                JLabel jl = new JLabel(s.getSprite().getImage());
                Dimension size = jl.getPreferredSize();
                jl.setBounds((int)scene.sceneObjects[i].objRef.transform.position.x, (int)scene.sceneObjects[i].objRef.transform.position.y, size.width, size.height);
                panel.add(jl);
            } catch (Exception ignore)
            {
                LogExtra("Didn't add object: " + objectsInView[i].JIdentity.getName() + " to render queue because it doesn't have a sprite");
            }
         }
        LogExtra("Rendered Objects");
        window.refreshWindow(panel);
    }

    public void setActiveScene(JScene activeScene){
        scene = activeScene;
        LogInfo("Changed active scene");
    }
    public JScene getActiveScene() {return scene;}
}
