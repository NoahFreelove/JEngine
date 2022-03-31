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
    private JSprite[] sprites;

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
        convertObjRefToObj();
    }

    private void convertObjRefToObj(){
        sprites = new JSprite[scene.getObjects().length];
        int i = 0;
        for (ObjRef objRef : scene.getObjects()) {
            if(objRef==null)
                continue;
            if(objRef.objRef instanceof JSprite sprite){
                sprites[i] = sprite;
                i++;
            }
        }

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> Render());
                return null;
            }
        };
        task.run();
    }

    private void RenderObjects(Group gameObjects)
    {
        for (JSprite sprite : sprites) {
            if (sprite == null) {
                continue;
            }
            // make sure we don't render inactive things
            if (!sprite.getActive()) {
                continue;
            }

            try {
                float xPos = (sprite.getTransform().position.x * window.getScaleMultiplier() - getTransform().position.x);
                float yPos = (sprite.getTransform().position.y * window.getScaleMultiplier() - getTransform().position.y);

                Image image = sprite.getSprite().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(sprite.getTransform().getScale().x * window.getScaleMultiplier() * sprite.getSprite().getWidth());
                imageView.setFitHeight(sprite.getTransform().getScale().y * window.getScaleMultiplier() * sprite.getSprite().getHeight());
                imageView.setX(xPos);
                imageView.setY(yPos);
                imageView.setRotate(sprite.getTransform().rotation.x * window.getScaleMultiplier());
                gameObjects.getChildren().add(imageView);

            } catch (Exception ignore) {
                LogAnnoyance("Didn't add object: " + sprite.getJIdentity().getName() + " to render queue because it doesn't have a sprite");
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

}
