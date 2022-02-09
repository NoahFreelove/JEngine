package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
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
    public JCamera(Vector3 position, JWindow window, JScene scene, Object parent, int fov, Identity identity) {
        super(parent.transform, identity);

        if(parent.transform == null){
            System.out.println("Parent was null");
            super.transform = new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1));
        }
        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fov = fov;
    }

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
        /*System.out.println("Left bound:" + leftBound);
        System.out.println("Right bound:" + rightBound);*/
        objectsInView = new Object[scene.getMaxObjects()];

        int i = 0;

        for (ObjRef obj: scene.sceneObjects) {
            //System.out.println("Object");
            //System.out.println("Object:" + obj.objRef.transform.getPosition().toString());
            if(obj.getClass().isInstance(Sprite.class))
            {
                Sprite objSprite = (Sprite)obj.objRef;
                if((objSprite.transform.getPosition().x+objSprite.getSprite().getXSize()) >= leftBound && obj.objRef.transform.getPosition().x <=rightBound)
                {
                    System.out.println(obj.objRef.identity.getName() + "InRange");
                    objectsInView[i] = objSprite;
                }
            }
            else
            {
                if((obj.objRef.transform.getPosition().x >= leftBound && obj.objRef.transform.getPosition().x <=rightBound))
                {
                    objectsInView[i] = obj.objRef;
                }
            }
            i++;
        }
        //System.out.println("render");
        Render();
    }

    private void Render()
    {
        JPanel panel = (JPanel) window.getWindow().getContentPane();
        panel.removeAll();
        for (int i = 0; i < objectsInView.length; i++) {

            if(objectsInView[i] == null) { continue; }

            // make sure we don't render inactive things
            if(!objectsInView[i].getActive()) { continue; }
            System.out.println("Object: " + objectsInView[i].identity.getName() + " : " + objectsInView[i].getClass().);
            if(!(objectsInView[i].getClass().isInstance(Sprite.class)))
            {
                continue;
            }

            JLabel jl = new JLabel(((Sprite)objectsInView[i]).getSprite().getImage());
            Dimension size = jl.getPreferredSize();
            jl.setBounds((int)scene.sceneObjects[i].objRef.transform.position.x, (int)scene.sceneObjects[i].objRef.transform.position.y, size.width, size.height);
            panel.add(jl);
        }
        LogExtra("Rendered Objects");
        window.refreshWindow(panel);
    }

    public void setActiveScene(JScene activeScene){
        scene = activeScene;
        LogInfo("Changed active scene");
    }
}
