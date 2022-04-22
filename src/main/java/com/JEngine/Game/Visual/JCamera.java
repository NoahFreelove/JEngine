package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.JSprite;
import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
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


/** JCamera (c) Noah Freelove
 * Brief Explanation:
 * JCamera converts the objects in a scene to a rendered panel which JWindow can show.
 * JCamera's output depends on its position and FOV (in pixels)
 * **/
public class JCamera extends JObject {
    // fov is added to the right and bottom of the camera, does not start from the middle of the camera
    public float fovX;
    public float fovY;
    private JScene scene;
    private final JWindow window;
    private JObject parent;
    private JSprite[] sprites;

    /**
     * Default constructor
     * @param position init position
     * @param window init window
     * @param scene init scene
     * @param parent parent
     * @param JIdentity Identity
     */
    public JCamera(Vector3 position, JWindow window, JScene scene, JObject parent, JIdentity JIdentity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), JIdentity);

        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fovX = window.getScaleMultiplier()*1280;
        this.fovY = window.getScaleMultiplier()*720;
        JSceneManager.init(scene, window, this);
    }
    /**
     * Default constructor
     * @param position init position
     * @param window init window
     * @param scene init scene
     * @param JIdentity Identity
     */
    public JCamera(Vector3 position, JWindow window, JScene scene, JIdentity JIdentity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), JIdentity);

        this.window = window;
        this.scene = scene;
        this.fovX = window.getScaleMultiplier()*1280;
        this.fovY = window.getScaleMultiplier()*720;
        JSceneManager.init(scene, window, this);
    }

    /**
     * Default setter for parent
     * @param newParent new parent
     */
    public void setParent(JObject newParent) {parent = newParent;}

    /**
     * Default getter for parent
     * @return parent
     */
    public JObject getParent() {return parent;}

    /**
     * Start converting objects in the scene to sprites
     */
    public void InitiateRender()
    {
        convertObjRefToObj();
    }

    /**
     * Converts all render-able scene objects to sprites
     */
    private void convertObjRefToObj(){
        sprites = new JSprite[scene.getObjects().length];
        int i = 0;
        for (ObjRef objRef : scene.getObjects()) {
            if(objRef==null || objRef.objRef.isQueuedForDeletion())
                continue;
            if(objRef.objRef instanceof JSprite sprite){
                sprites[i] = sprite;
                i++;
            }
        }
        // Run on new thread, so we can run other things while rendering
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> Render());
                return null;
            }
        };
        task.run();
    }

    /**
     * Renders the scene
     * @param gameObjects Group to add the rendered objects to
     */
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
                LogDebug("Didn't add object: " + sprite.getJIdentity().getName() + " to render queue");
            }
        }
    }

    /**
     * Start the render process
     */
    private void Render()
    {
        Group gameObjects = new Group();

        LogDebug("Start Sprite Render");
        RenderObjects(gameObjects);

        // Add rendered objects to the window
        LogDebug("Rendered Objects");
        window.refreshWindow(gameObjects);
    }

    /**
     * Set the scene for the camera to render
     * @param activeScene The scene to start rendering
     */
    public void setActiveScene(JScene activeScene){
        scene = activeScene;
        LogInfo("Changed active scene");
    }

    /**
     * Get the scene that the camera is rendering
     * @return The scene that the camera is rendering
     */
    public JScene getScene() {
        return scene;
    }

    /**
     * Get the window that this camera is using
     * @return The window that this camera is using
     */
    public JWindow getWindow() {
        return window;
    }

    /**
     * Get the camera's parent object
     * @return the camera's parent object
     */
    public JObject getParentObject() {
        return parent;
    }
}
