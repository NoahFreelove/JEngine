package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.JIcon;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.UserInterface.JText;
import com.JEngine.UserInterface.JUIBackgroundImage;

import javax.swing.*;
import java.awt.*;

/** JEngine.JCamera (c) Noah Freelove
 * Brief Explanation:
 * JCamera converts the objects in a scene to a rendered panel which JWindow can show.
 * JCamera's output depends on its position and FOV (in pixels)
 * **/
public class JCamera extends JObject {
    // fov is added to the right and bottom of the camera, does not start from the middle of the camera
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

    float scaleSize(int size, float scaleFactor)
    {
        return ((size*scaleFactor)/2 - 1);
    }

    private void GetObjectsInView()
    {
        if (parent.transform != null)
        {
            super.transform.position = parent.transform.position;
        }

        int leftBound = (int)transform.getPosition().x;
        int rightBound = (int)transform.getPosition().x + fov;
        int upBound = (int)transform.getPosition().y;
        int downBound = (int)transform.getPosition().y + fov;

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
                // right tip of object is in frame
                boolean con1 = (obj.objRef.transform.getPosition().x + (objSprite.getSprite().getXSize() * obj.objRef.transform.getScale().x)) >= leftBound;
                // right tip of object isn't out of frame
                boolean con2 = (obj.objRef.transform.getPosition().x) <= rightBound;
                // bottom tip of object isn't out of frame
                boolean con3 = (obj.objRef.transform.getPosition().y + (objSprite.getSprite().getYSize() * obj.objRef.transform.getScale().y)) >=upBound;
                // bottom tip of object is in frame
                boolean con4 = (obj.objRef.transform.getPosition().y)<=downBound;

                if( con1 && con2 && con3 && con4)
                {
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
        JLayeredPane panel = (JLayeredPane) window.getWindow().getContentPane();
        panel.removeAll();
        LogExtra("Start UI Render");
        int layerIndex = scene.getMaxObjects();
        for (int i = 0; i < scene.juiObjects.length; i++) {
            if (scene.juiObjects[i] == null) {
                continue;
            }

            // make sure we don't render inactive things
            if (!scene.juiObjects[i].getActive()) {
                continue;
            }

            int totalScaleX = (int)(scene.juiObjects[i].transform.getScale().x * scene.juiObjects[i].sizeX);
            int totalScaleY = (int)(scene.juiObjects[i].transform.getScale().y * scene.juiObjects[i].sizeY);

            int xPos = (int)(scene.juiObjects[i].transform.position.x - transform.position.x);
            int yPos = (int)(scene.juiObjects[i].transform.position.y - transform.position.y);

            if(scene.juiObjects[i].getClass().equals(JUIBackgroundImage.class))
            {
                Image image = scene.juiObjects[i].getImage();
                JLabel jl = new JLabel(new ImageIcon(image));
                jl.setBounds(0,0 , scene.juiObjects[i].sizeX, scene.juiObjects[i].sizeY);
                panel.add(jl,101);
                continue;
            }

            if(scene.juiObjects[i].getClass().equals(JText.class))
            {
                JText jText = (JText)scene.juiObjects[i];
                JLabel jl = new JLabel(jText.getText());
                jl.setBounds(xPos,yPos ,totalScaleX,totalScaleY);
                panel.add(jl, 100);

                continue;
            }


            if(scene.juiObjects[i].getClass().isAssignableFrom(JUIObject.class))
            {
                Image image = scene.juiObjects[i].getImage().getScaledInstance(totalScaleX, totalScaleY, Image.SCALE_DEFAULT);
                JLabel jl = new JLabel(new ImageIcon(image));

                jl.setBounds(xPos,yPos,totalScaleX,totalScaleY);
                panel.add(jl,99);

            }
        }

        LogExtra("Start Sprite Render");
        for (int i = 0; i < objectsInView.length; i++) {
            layerIndex--;
            if (objectsInView[i] == null) {
                continue;
            }
            // make sure we don't render inactive things
            if (!objectsInView[i].getActive()) {
                continue;
            }

            //System.out.println("Object: " + objectsInView[i].identity.getName() + " : " + objectsInView[i].getClass().)
            try
            {
                //System.out.println(objectsInView[i].getClass());
                    Sprite s = (Sprite)objectsInView[i];
                    JLabel jl = new JLabel(new ImageIcon(s.getSprite().getIcon().getImage().getScaledInstance((int)(objectsInView[i].transform.getScale().x* s.getSprite().getXSize()), (int)(objectsInView[i].transform.getScale().y* s.getSprite().getYSize()), Image.SCALE_DEFAULT)));
                    Dimension size = jl.getPreferredSize();
                    int xPos = (int)(scene.sceneObjects[i].objRef.transform.position.x - transform.position.x);
                    int yPos = (int)(scene.sceneObjects[i].objRef.transform.position.y - transform.position.y);

                jl.setBounds(xPos, yPos, size.width, size.height);
                    panel.add(jl,layerIndex);
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
