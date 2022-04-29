package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector2;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.GameObject;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/** GameCamera (c) Noah Freelove
 * Brief Explanation:
 * Camera converts the objects in a scene to a rendered panel which JWindow can show.
 * Camera's output depends on its position and FOV (in pixels)
 * **/
public class GameCamera extends GameObject {
    // fov is added to the right and bottom of the camera, does not start from the middle of the camera
    public float fovX;
    public float fovY;
    private GameScene scene;
    private final GameWindow window;
    private GameObject parent;
    private Sprite[] sprites;

    /**
     * Default constructor
     * @param position init position
     * @param window init window
     * @param scene init scene
     * @param parent parent
     * @param Identity Identity
     */
    public GameCamera(Vector3 position, GameWindow window, GameScene scene, GameObject parent, Identity Identity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), Identity);

        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fovX = window.getScaleMultiplier()*1280;
        this.fovY = window.getScaleMultiplier()*720;
        SceneManager.init(scene, window, this);
    }

    /**
     * Default constructor
     * @param position init position
     * @param window init window
     * @param scene init scene
     * @param Identity Identity
     */
    public GameCamera(Vector3 position, GameWindow window, GameScene scene, Identity Identity) {
        super(new Transform(position, new Vector3(0,0,0), new Vector3(1,1,1)), Identity);

        this.window = window;
        this.scene = scene;
        this.fovX = window.getScaleMultiplier()*1280;
        this.fovY = window.getScaleMultiplier()*720;
        SceneManager.init(scene, window, this);
    }

    /**
     * Default setter for parent
     * @param newParent new parent
     */
    public void setParent(GameObject newParent) {parent = newParent;}

    /**
     * Default getter for parent
     * @return parent
     */
    public GameObject getParent() {return parent;}

    /**
     * Start converting objects in the scene to sprites
     */
    public void startRender()
    {
        convertObjRefToObj();
    }

    /**
     * Converts all render-able scene objects to sprites
     */
    private void convertObjRefToObj(){
        sprites = new Sprite[scene.getObjects().length];
        int i = 0;
        for (GameObject objRef : scene.getObjects()) {
            if(objRef==null || objRef.isQueuedForDeletion())
                continue;
            if(objRef instanceof Sprite sprite){
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
     * Returns A group of JImages converted into ImageViews in correct zOrder
     */
    private Group RenderObjects()
    {
        Group gameObjects = new Group();
        for (Sprite sprite : sprites) {
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

            } catch (Exception e) {
                LogDebug("Didn't add object: " + sprite.getIdentity().getName() + " to render queue: " + e.getMessage());
            }
        }
        return gameObjects;
    }

    /**
     * Start the render process
     */
    private void Render()
    {
        LogDebug("Start Object Render");
        window.refreshWindow(RenderObjects());
        LogDebug("Rendered Objects");

    }

    /**
     * Set the scene for the camera to render
     * @param activeScene The scene to start rendering
     */
    public void setActiveScene(GameScene activeScene){
        scene = activeScene;
        LogInfo("Changed active scene");
    }

    /**
     * Get the scene that the camera is rendering
     * @return The scene that the camera is rendering
     */
    public GameScene getScene() {
        return scene;
    }

    /**
     * Get the window that this camera is using
     * @return The window that this camera is using
     */
    public GameWindow getWindow() {
        return window;
    }

    /**
     * Get the camera's parent object
     * @return the camera's parent object
     */
    public GameObject getParentObject() {
        return parent;
    }
    @Override
    public void Update(){
        if(parent !=null)
        {
            Vector2 offset = new Vector2(0,0);
            offset.x = 1280*window.getScaleMultiplier()/2;
            offset.y = 720*window.getScaleMultiplier()/2;
            if(parent instanceof Sprite sprite)
            {
                offset.x = offset.x-(sprite.getSprite().getWidth() * sprite.getTransform().getScale().x)/2;
                offset.y = offset.y-(sprite.getSprite().getHeight() * sprite.getTransform().getScale().y)/2;
            }


            this.getTransform().setPosition(new Vector3(parent.getTransform().getPosition().x - offset.x, parent.getTransform().getPosition().y - offset.y, getTransform().position.z));
        }
    }
}
