package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.UserInterface.JText;

import javax.swing.*;
import java.awt.*;

/** JEngine.JCamera (c) Noah Freelove
 * Brief Explanation:
 * JCamera converts the objects in a scene to a rendered panel which JWindow can show.
 * JCamera's output depends on its position and FOV (in pixels)
 * **/
public class JCamera extends JObject {
    public int fov;
    private JScene scene;
    public JWindow window;
    public JObject parent;
    public JObject[] objectsInView;

    public JCamera(Vector3 position, JWindow window, JScene scene, JObject parent, int fov, JIdentity JIdentity) {
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

    public void setParent(JObject newParent) {parent = newParent;}
    public JObject getParent() {return parent;}

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

        objectsInView = new JObject[scene.getMaxObjects()];

        int i = 0;

        for (ObjRef obj: scene.sceneObjects) {
            if(obj==null)
            {
                LogExtra("Tried to get object that doesn't exist! Try lowering your maxObjects parameter");
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
                if(obj.objRef.getClass().equals(JText.class))
                {
                    objectsInView[i] = obj.objRef;
                    continue;
                }


                if((obj.objRef.transform.getPosition().x >= leftBound && obj.objRef.transform.getPosition().x <=rightBound))
                {
                    objectsInView[i] = obj.objRef;
                    //System.out.println(objectsInView[i].getClass());
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
                //System.out.println(objectsInView[i].getClass());
                if(objectsInView[i].getClass().equals(JText.class))
                {
                    JText jText = (JText)objectsInView[i];
                    jText.getLabel().setBounds(200,200,1000,1000);
                    window.frame.add(jText.getLabel());

                    //panel.add();


                }
                else
                {
                    Sprite s = (Sprite)objectsInView[i];
                    JLabel jl = new JLabel(s.getSprite().getImage());
                    Dimension size = jl.getPreferredSize();
                    jl.setBounds((int)scene.sceneObjects[i].objRef.transform.position.x, (int)scene.sceneObjects[i].objRef.transform.position.y, size.width, size.height);
                    window.frame.add(jl);

                    //panel.add(jl);
                }
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
