package com.JEngine.Game.Visual;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.GameObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.FPSCounter;
import com.JEngine.Utility.Misc.GameUtility;
import com.JEngine.Utility.Misc.GenericMethod;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/** JWindow (c) Noah Freelove
 * Brief Explanation:
 * JWindow is a way to create a window and have it display camera content.
 * JWindow provides the actual update function
 * **/

public class GameWindow extends Thing {
    private boolean isActive;
    private boolean isPaused;
    private final Stage stage;
    public Scene scene;
    public GameScene jscene;

    private Thread updateThread;

    private double fpsMili = 1000/30;
    private float targetFPS = 30;
    public int totalFrames = 1;

    private Color backgroundColor = Color.WHITE;

    public Group parent = new Group();
    public Group sceneObjects = new Group();

    private boolean isFocused = true;
    private float scaleMultiplier = 1;
    private Group prevObj;


    private GenericMethod[] updateEvents= new GenericMethod[0];
    private int updateEventsIndex = 0;
    /**
     * Default constructor
     * @param title Title of the window
     * @param window Default stage (Typically given by JavaFX public void start(Stage stage)
     */
    public GameWindow(GameScene scene, float scaleMultiplier, String title, Stage window) {
        super(true);
        try
        {
            parent.getChildren().add(sceneObjects);
            parent.getChildren().add(scene.uiObjects);
            this.scene = new Scene(parent,1280*scaleMultiplier,720*scaleMultiplier);
            this.jscene = scene;
            Input.init(this.scene);
        }
        catch (Exception e){
            System.out.println(e);
            this.scene = new Scene(parent, 1280*scaleMultiplier,720*scaleMultiplier);
            prevObj = sceneObjects;
            Input.init(this.scene);

        }

        this.stage = window;
        this.stage.setTitle(title);
        this.stage.setResizable(false);
        this.stage.setScene(this.scene);
        this.stage.show();
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, GameUtility::exitWindow);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> onFocusChange(newValue.getValue()));
        this.scaleMultiplier = scaleMultiplier;
    }

    /**
     * Constructor with style!
     * @param scaleMultiplier Multiplier for the window's size
     * @param title Title of the window
     * @param window the stage given start(Stage stage)
     * @param style StageStyle
     */
    public GameWindow(float scaleMultiplier, String title, Stage window, StageStyle style) {
        super(true);
        parent.getChildren().add(sceneObjects);
        window.initStyle(style);
        scene = new Scene(sceneObjects, 1280*scaleMultiplier,720*scaleMultiplier);
        this.stage = window;
        this.stage.setTitle(title);
        prevObj = sceneObjects;

        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, GameUtility::exitWindow);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> onFocusChange(newValue.getValue()));
        this.scaleMultiplier = scaleMultiplier;
        Input.init(this.scene);

    }

    /**
     * Gets the window scale multiplier
     * @return scale multiplier
     */
    public float getScaleMultiplier(){return scaleMultiplier;}

    /**
     * Get the scene's name
     * @return Scene name (String)
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Get the stage
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Set the window's scene
     * @param newScene New scene to set
     */
    public void setScene(Scene newScene) {
        scene = newScene;
    }

    /**
     * Set the window's icon
     * @param newIcon New JImage to set as icon
     */
    public void setIcon(GameImage newIcon) {
        if (newIcon.getImage() != null)
        {
            stage.getIcons().add(newIcon.getImage());
            return;
        }
        LogWarning("Tried to set window icon to a null image");
    }

    /**
     * Set new window dimensions
     * @param newScale new multiplier of 1280x720
     */
    public void setWindowScale(float newScale)
    {
        float preValue = scaleMultiplier;

        stage.setWidth(1280*newScale);
        stage.setHeight(720*newScale);
        scaleMultiplier = newScale;
    }

    /**
     * Is called every frame. The method that actually repaints the window. Not recommend calling this manually as you
     * may end up with an inconsistent FPS
     * @param gameObjects Game object group
     */
    public void refreshWindow(Group gameObjects) {
        sceneObjects = gameObjects;
        parent.getChildren().add(sceneObjects);
        parent.getChildren().remove(prevObj);
        scene.setFill(backgroundColor);
        prevObj = sceneObjects;
        sceneObjects.toBack();
    }


    /**
     * Set number of times the window is updated. (Also affects Update() functions!)
     * @param newTargetFPS new times per second to update. 60fps or multiple of 30 Recommended
     */
    public void setTargetFPS(float newTargetFPS) {
        targetFPS = newTargetFPS;
        fpsMili = 1000 / targetFPS;
    }
    public void setBackgroundColor(Color newColor){backgroundColor = newColor;}
    /**
     * Start updating the window
     */
    public void start()
    {
        if(isActive)
        {
            LogExtra("Window is already active! Cannot start another window thread.");
            return;
        }
        SceneManager.getActiveScene().runStartBehaviors();
        isActive = true;
        updateThread = new Thread(this::refresh);
        updateThread.start();
        FPSCounter.start();
        LogInfo("Successfully started window");

    }

    /**
     * Stop updating the window.
     * I don't know when you would actually want to do this, but it's built in any way.
     */
    public void stop()
    {
        try
        {
            updateThread.interrupt();
            FPSCounter.stop();
            isActive = false;
            LogInfo("Successfully stopped window");
        }
        catch (Exception ignored)
        {
            LogError("Could not stop window thread");
        }
    }

    /**
     * Logic to update the window targetFPS times/second
     */
    private void refresh() {
        double nextTick = System.currentTimeMillis();

        while (isActive) {
            while (System.currentTimeMillis() > nextTick) {
                nextTick += fpsMili;

                if(isPaused)
                    continue;
                totalFrames++;
                update(totalFrames);
                FPSCounter.updateFrame();
            }

        }
    }

    /**
     * JWindow's update method. Called every refresh cycle to start the render and run the Update() behaviors
     * @param frameNumber Total frame number. Useful for keeping track of frames.
     */
    private void update(int frameNumber) {
        LogDebug(String.format("New frame (#%d)", frameNumber));
        runUpdateBehaviors();
        GameCamera mainCamera = SceneManager.getActiveCamera();
        if(mainCamera !=null && mainCamera.getActive())
        {
            mainCamera.startRender();
        }

        for (GenericMethod  method : updateEvents) {
            if(method!=null)
                method.call(null);
        }

        // very laggy
        if(EnginePrefs.aggressiveGC)
            System.gc();

        if(!isFocused)
            Input.resetKeys();

    }

    /**
     * Goes through every object in the scene to run their Update() functions.
     * Objects which don't override the function will not have any function
     */
    private void runUpdateBehaviors() {
        for (GameObject objRef : SceneManager.getActiveScene().getObjects()) {
            if(objRef == null || objRef.isQueuedForDeletion())
                continue;
            if(objRef.getActive())
                objRef.Update();
        }
    }

    /**
     * Get the isFocused value
     * @return Returns if the window is currently focused or not
     */
    public boolean getIsFocused(){ return isFocused;}

    /**
     * Runs when the window focus value changes. Can be overridden to do something when focus changes
     * @param isFocused New focus value
     */
    public void onFocusChange(boolean isFocused){
        this.isFocused = isFocused;
    }

    public void pause()
    {
        isPaused = true;
    }

    public void resume()
    {
        isPaused = false;
    }

    public void addUpdateEvent(GenericMethod method)
    {
        // if array is full, make it larger
        if(updateEvents.length == updateEventsIndex)
        {
            GenericMethod[] newArray = new GenericMethod[updateEvents.length +1];
            System.arraycopy(updateEvents, 0, newArray, 0, updateEvents.length);
            updateEvents = newArray;
            updateEvents[updateEventsIndex] = method;
            return;
        }
        updateEvents[updateEventsIndex++] = method;
    }
}