package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.JSprite;
import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/** com.JEngine.JCamera (c) Noah Freelove
 * Brief Explanation:
 * JCamera converts the objects in a scene to a rendered panel which JWindow can show.
 * JCamera's output depends on its position and FOV (in pixels)
 * **/
public class JCamera extends JObject {
    // fov is added to the right and bottom of the camera, does not start from the middle of the camera
    public float fovX;
    public float fovY;
    private JScene scene;
    public JWindow window;
    public JObject parent;
    public JObject[] objectsInView;
    private boolean renderAll = false;

    public JCamera(Vector3 position, JWindow window, JScene scene, JObject parent, JIdentity JIdentity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), JIdentity);

       /* if(parent.transform == null){
            LogWarning(String.format("Camera: '%s' parent property is null. The camera will not move.", JIdentity.getName()));
            super.transform = new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1));
        }*/

        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fovX = window.getScaleMultiplier()*1280;
        this.fovY = window.getScaleMultiplier()*720;
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
            super.getTransform().position = parent.getTransform().position;
        }*/

        float leftBound = (getTransform().getPosition().x);
        float rightBound = (getTransform().getPosition().x) + 1280;
        float upBound = (getTransform().getPosition().y);
        float downBound = (getTransform().getPosition().y + 720);

        objectsInView = new JObject[scene.getMaxObjects()];

        int i = 0;

        for (ObjRef obj: scene.sceneObjects) {
            if(obj==null || (obj.objRef.isQueuedForDeletion()))
            {
                continue;
            }


            try {
                JSprite objSprite = (JSprite) obj.objRef;
                if(renderAll)
                {
                    objectsInView[i] = objSprite;
                    i++;
                    continue;
                }
                //System.out.println("Y Pos " + obj.objRef.getTransform().position.y);

                // left tip of object is in frame
                boolean con1 = (obj.objRef.getTransform().getPosition().x + (objSprite.getSprite().getXSize() * obj.objRef.getTransform().getScale().x)) >= leftBound;

                // right tip of object isn't out of frame
                boolean con2 = (obj.objRef.getTransform().getPosition().x) <= rightBound;

                // top tip of object isn't out of frame
                boolean con3 = (obj.objRef.getTransform().getPosition().y + (objSprite.getSprite().getYSize() * obj.objRef.getTransform().getScale().y)) >=upBound;

                // bottom tip of object is in frame
                boolean con4 = (obj.objRef.getTransform().getPosition().y)<=downBound;

                if( con1 && con2 && con3 && con4)
                {
                    objectsInView[i] = objSprite;
                }
            }
            catch (Exception ignore)
            {
                //ignore
            }
            i++;
        }

        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                    Platform.runLater(() -> Render());
                return null;
            }
        };
        task.run();


    }


    private void RenderObjects(Group gameObjects)
    {
        for (JObject jObject : objectsInView) {
            if (jObject == null) {
                continue;
            }
            // make sure we don't render inactive things
            if (!jObject.getActive()) {
                continue;
            }

            //System.out.println("Object: " + objectsInView[i].identity.getName() + " : " + objectsInView[i].getClass().)
            try {
                float xPos = (jObject.getTransform().position.x * window.getScaleMultiplier() - getTransform().position.x);
                float yPos = (jObject.getTransform().position.y * window.getScaleMultiplier() - getTransform().position.y);

                JSprite s = (JSprite) jObject;
                Image image = s.getSprite().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(jObject.getTransform().getScale().x * window.getScaleMultiplier() * s.getSprite().getXSize());
                imageView.setFitHeight(jObject.getTransform().getScale().y * window.getScaleMultiplier() * s.getSprite().getYSize());
                imageView.setX(xPos);
                imageView.setY(yPos);
                imageView.setRotate(jObject.getTransform().rotation.x * window.getScaleMultiplier());
                gameObjects.getChildren().add(imageView);
                //JLabel jl = new JLabel(new ImageIcon(s.getSprite().getIcon().getImage().getScaledInstance((int)(objectsInView[i].getTransform().getScale().x* s.getSprite().getXSize()), (int)(objectsInView[i].getTransform().getScale().y* s.getSprite().getYSize()), Image.SCALE_DEFAULT)));

            } catch (Exception ignore) {
                if (jObject != null) {
                    LogAnnoyance("Didn't add object: " + jObject.JIdentity.getName() + " to render queue because it doesn't have a sprite");
                }
            }
        }
    }

    private void Render()
    {
        Group gameObjects = new Group();

        LogAnnoyance("Start Sprite Render");
        RenderObjects(gameObjects);

        LogAnnoyance("Rendered Objects");
        window.refreshWindow(gameObjects);
    }

    public void setActiveScene(JScene activeScene){
        scene = activeScene;
        LogInfo("Changed active scene");
    }
    public JScene getActiveScene() {return scene;}

    public boolean getRenderAll() {
        return renderAll;
    }

    public void setRenderAll(boolean renderAll) {
        this.renderAll = renderAll;
    }
}
