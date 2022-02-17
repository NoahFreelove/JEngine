package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.UserInterface.JButton;
import com.JEngine.UserInterface.JText;
import com.JEngine.UserInterface.JUIBackgroundImage;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


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
                LogAnnoyance("Tried to get object that doesn't exist! Try lowering your maxObjects parameter");
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

        Platform.runLater(this::Render);
    }
    private void RenderUI(Group uiObjects)
    {
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
                /*Image image = scene.juiObjects[i].getImage();
                BackgroundImage bImg = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bGround = new Background(bImg);
                StackPane sp = new StackPane();
                ImageView imageView = new ImageView(image);
                imageView.setX(xPos);
                imageView.setY(yPos);
                imageView.toBack();
                uiObjects.getChildren().add(imageView);*/
                continue;
            }

            if(scene.juiObjects[i].getClass().equals(JText.class))
            {
                JText jText = (JText)scene.juiObjects[i];
                jText.getLabel().setX(xPos);
                jText.getLabel().setY(xPos);
                uiObjects.getChildren().add(jText.getLabel());
                continue;
            }

            if(scene.juiObjects[i].getClass().equals(JButton.class))
            {
                JButton jButton = (JButton)scene.juiObjects[i];
                Button b = jButton.getButton();
                b.setLayoutX(xPos);
                b.setLayoutY(yPos);
                uiObjects.getChildren().add(b);
                continue;
            }


            if(scene.juiObjects[i].getClass().isAssignableFrom(JUIObject.class))
            {
                Image image = scene.juiObjects[i].getImage();
                ImageView imageView = new ImageView(image);
                imageView.setX(xPos);
                imageView.setY(yPos);
                imageView.setRotate(scene.juiObjects[i].transform.rotation.x);
                uiObjects.getChildren().add(imageView);
            }
        }
    }

    private void RenderObjects(Group gameObjects)
    {
        for (int i = 0; i < objectsInView.length; i++) {
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
                int xPos = (int)(scene.sceneObjects[i].objRef.transform.position.x - transform.position.x);
                int yPos = (int)(scene.sceneObjects[i].objRef.transform.position.y - transform.position.y);

                Sprite s = (Sprite)objectsInView[i];
                Image image = s.getSprite().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(objectsInView[i].transform.getScale().x*s.getSprite().getXSize());
                imageView.setFitHeight(objectsInView[i].transform.getScale().y*s.getSprite().getYSize());

                imageView.setX(xPos);
                imageView.setY(yPos);
                imageView.setRotate(scene.sceneObjects[i].objRef.transform.rotation.x);

                gameObjects.getChildren().add(imageView);
                //JLabel jl = new JLabel(new ImageIcon(s.getSprite().getIcon().getImage().getScaledInstance((int)(objectsInView[i].transform.getScale().x* s.getSprite().getXSize()), (int)(objectsInView[i].transform.getScale().y* s.getSprite().getYSize()), Image.SCALE_DEFAULT)));

            } catch (Exception ignore)
            {
                if(objectsInView[i] !=null)
                {
                    LogAnnoyance("Didn't add object: " + objectsInView[i].JIdentity.getName() + " to render queue because it doesn't have a sprite");
                }
            }
        }
    }

    private void Render()
    {
        Group gameObjects = new Group();
        Group uiObjects = new Group();


        LogAnnoyance("Start Sprite Render");
        RenderObjects(gameObjects);

        LogAnnoyance("Start UI Render");
        RenderUI(uiObjects);


        LogExtra("Rendered Objects");
        window.refreshWindow(gameObjects, uiObjects);
    }

    public void setActiveScene(JScene activeScene){
        scene = activeScene;
        JSceneManager.setScene(activeScene);
        LogInfo("Changed active scene");
    }
    public JScene getActiveScene() {return scene;}
}
