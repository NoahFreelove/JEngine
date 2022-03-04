package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.JScene;
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


/** JEngine.JCamera (c) Noah Freelove
 * Brief Explanation:
 * JCamera converts the objects in a scene to a rendered panel which JWindow can show.
 * JCamera's output depends on its position and FOV (in pixels)
 * **/
public class JCamera extends JObject {
    // fov is added to the right and bottom of the camera, does not start from the middle of the camera
    public int fovX;
    public int fovY;
    private JScene scene;
    public JWindow window;
    public JObject parent;
    public JObject[] objectsInView;

    public JCamera(Vector3 position, JWindow window, JScene scene, JObject parent, JIdentity JIdentity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), JIdentity);

       /* if(parent.transform == null){
            LogWarning(String.format("Camera: '%s' parent property is null. The camera will not move.", JIdentity.getName()));
            super.transform = new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1));
        }*/

        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fovX = (int) (window.getScaleMultiplier()*1280);
        this.fovY = (int) (window.getScaleMultiplier()*720);
    }

    public void setParent(JObject newParent) {parent = newParent;}
    public JObject getParent() {return parent;}

    public void InitiateRender()
    {
        GetObjectsInView();
    }


    private void GetObjectsInView()
    {
        /*if (parent.transform != null)
        {
            super.transform.position = parent.transform.position;
        }*/

        float leftBound = (transform.getPosition().x*window.getScaleMultiplier());
        float rightBound = (transform.getPosition().x*window.getScaleMultiplier()) + fovX;
        float upBound = (transform.getPosition().y*window.getScaleMultiplier());
        float downBound = (transform.getPosition().y*window.getScaleMultiplier()) + fovY;

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
                boolean con1 = (obj.objRef.transform.getPosition().x*window.getScaleMultiplier() +
                        (objSprite.getSprite().getXSize()*window.getScaleMultiplier() *
                                obj.objRef.transform.getScale().x*window.getScaleMultiplier())) >= leftBound;
                // right tip of object isn't out of frame
                boolean con2 = (obj.objRef.transform.getPosition().x*window.getScaleMultiplier()) <= rightBound;
                // bottom tip of object isn't out of frame
                boolean con3 = (obj.objRef.transform.getPosition().y*window.getScaleMultiplier() +
                        (objSprite.getSprite().getYSize()*window.getScaleMultiplier() *
                                obj.objRef.transform.getScale().y*window.getScaleMultiplier())) >=upBound;
                // bottom tip of object is in frame
                boolean con4 = (obj.objRef.transform.getPosition().y*window.getScaleMultiplier())<=downBound;
          /*      LogInfo(String.valueOf((obj.objRef.transform.getPosition().y*window.getScaleMultiplier())));
                LogInfo(String.valueOf((obj.objRef.transform.getPosition().x*window.getScaleMultiplier())));
                LogInfo(String.valueOf(window.getScaleMultiplier()));*/

                if( con1 && con2 && con3 && con4)
                {
                    objectsInView[i] = objSprite;
                    System.out.println("Visible");
                }
            }
            catch (Exception ignore)
            {
                if(obj.objRef.getClass().equals(JText.class))
                {
                    objectsInView[i] = obj.objRef;
                    continue;
                }

                if((obj.objRef.transform.getPosition().x*window.getScaleMultiplier() >= leftBound && obj.objRef.transform.getPosition().x*window.getScaleMultiplier() <=rightBound))
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

            int totalScaleX = (int)(scene.juiObjects[i].transform.getScale().x*window.getScaleMultiplier() * scene.juiObjects[i].sizeX*window.getScaleMultiplier());
            int totalScaleY = (int)(scene.juiObjects[i].transform.getScale().y*window.getScaleMultiplier() * scene.juiObjects[i].sizeY*window.getScaleMultiplier());

            float xPos = (scene.juiObjects[i].transform.position.x*window.getScaleMultiplier() - transform.position.x*window.getScaleMultiplier());
            float yPos = (scene.juiObjects[i].transform.position.y*window.getScaleMultiplier() - transform.position.y*window.getScaleMultiplier());

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
                jText.getLabel().setX(xPos*window.getScaleMultiplier());
                jText.getLabel().setY(yPos*window.getScaleMultiplier());
                uiObjects.getChildren().add(jText.getLabel());
                continue;
            }

            if(scene.juiObjects[i].getClass().equals(JButton.class))
            {
                JButton jButton = (JButton)scene.juiObjects[i];
                Button b = jButton.getButton();
                b.setLayoutX(xPos*window.getScaleMultiplier());
                b.setLayoutY(yPos*window.getScaleMultiplier());
                uiObjects.getChildren().add(b);
                continue;
            }


            if(scene.juiObjects[i].getClass().isAssignableFrom(JUIObject.class))
            {
                Image image = scene.juiObjects[i].getImage();
                ImageView imageView = new ImageView(image);
                imageView.setX(xPos*window.getScaleMultiplier());
                imageView.setY(yPos*window.getScaleMultiplier());
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
                int xPos = (int)(scene.sceneObjects[i].objRef.transform.position.x*window.getScaleMultiplier() - transform.position.x*window.getScaleMultiplier());
                int yPos = (int)(scene.sceneObjects[i].objRef.transform.position.y*window.getScaleMultiplier() - transform.position.y*window.getScaleMultiplier());

                Sprite s = (Sprite)objectsInView[i];
                Image image = s.getSprite().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(objectsInView[i].transform.getScale().x*window.getScaleMultiplier()*s.getSprite().getXSize()*window.getScaleMultiplier());
                imageView.setFitHeight(objectsInView[i].transform.getScale().y*window.getScaleMultiplier()*s.getSprite().getYSize()*window.getScaleMultiplier());

                imageView.setX(xPos*window.getScaleMultiplier());
                imageView.setY(yPos*window.getScaleMultiplier());
                imageView.setRotate(scene.sceneObjects[i].objRef.transform.rotation.x*window.getScaleMultiplier());

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
        LogInfo("Changed active scene");
    }
    public JScene getActiveScene() {return scene;}
}
