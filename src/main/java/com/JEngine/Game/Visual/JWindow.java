package com.JEngine.Game.Visual;

import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.JUtility;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * @author Noah Freelove
 * @version 1.0
 * Brief Explanation:
 * JWindow is a way to create a window and have it display camera content.
 *
 * Usage:
 * JWindow provides an update function and a way to set FPS
 * **/

public class JWindow extends Thing {
    public Scene scene;
    public Stage window;
    Group root = new Group();
    public JCamera activeCamera;
    public boolean isActive;
    public int totalFrames;
    private Thread updateThread;
    private float targetFPS = 5;
    Group objects = new Group();
    Group uiObjects = new Group();
    private boolean isFocused = true;
    private float scaleMultiplier = 1;
    /**
     * Default constructor
     * @param title Title of the window
     * @param window Default stage (Typically given by JavaFX public void start(Stage stage)
     */
    public JWindow(float scaleMultiplier, String title, Stage window) {
        super(true);
        scene = new Scene(root, 1280*scaleMultiplier,720*scaleMultiplier);
        root.getChildren().add(objects);
        root.getChildren().add(uiObjects);
        this.window = window;
        this.window.setTitle(title);
        this.window.setResizable(false);
        this.window.setScene(scene);
        this.window.show();
        this.window.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, JUtility::exitApp);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> isFocused = newValue.getValue());
    }

    public JWindow(float scaleMultiplier, String title, Stage window, StageStyle style) {
        super(true);
        scene = new Scene(root, 1280*scaleMultiplier,720*scaleMultiplier);
        root.getChildren().add(objects);
        root.getChildren().add(uiObjects);
        window.initStyle(style);
        this.window = window;
        this.window.setTitle(title);
        this.window.setResizable(false);
        this.window.setScene(scene);
        this.window.show();
        this.window.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, JUtility::exitApp);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> isFocused = newValue.getValue());
    }

    public float getScaleMultiplier(){return scaleMultiplier;}

    /**
     * Get the scene's name
     * @return Scene name (String)
     */
    public Scene getScene() {
        return scene;
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
    public void setIcon(JImage newIcon) {
        if (newIcon.getImage() != null)
        {
            window.getIcons().add(newIcon.getImage());
            return;
        }
        LogWarning("Tried to set window icon to a null image");
    }

    /**
     * Is called every frame. The method that actually repaints the window. Not recommend calling this manually as you
     * may end up with an inconsistent FPS
     * @param gameObjects Game object group
     * @param newUIObjects UI object group
     */
    public void refreshWindow(Group gameObjects, Group newUIObjects) {
        Platform.runLater(() -> {
            objects = gameObjects;
            uiObjects = newUIObjects;
            root.getScene().setFill(Color.BLACK);
            root.getChildren().clear();
            root.getChildren().add(objects);
            root.getChildren().add(uiObjects);
        });
    }

    /**
     * Set number of times the window is updated. (Also affects Update() functions!)
     * @param newTargetFPS new times per second to update
     */
    public void setTargetFPS(float newTargetFPS) {
        targetFPS = newTargetFPS;
    }

    /**
     * Start updating the window
     */
    public void start()
    {
        if(isActive)
        {
            LogError("Window is already active! Cannot start another update thread.");
            return;
        }
        JSceneManager.getActiveScene().runStartBehaviors();
        isActive = true;
        updateThread = new Thread(this::refresh);
        updateThread.start();
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
            isActive = false;
            LogInfo("Successfully stopped window");
        }
        catch (Exception ignored)
        {
            LogError("Could not stop window thread");
        }
    }

    /**
     * Math to update the window targetFPS times/second
     */
    private void refresh() {
        final int maxFrameSkip = 5;
        int SKIP_TICKS = (int) (1000 / targetFPS);
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (isActive) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < maxFrameSkip) {
                totalFrames++;
                update(totalFrames);
                next_game_tick += SKIP_TICKS;
                loops++;

            }
        }
    }

    /**
     * Set the camera for the window to render from
     * @param camera new camera to set
     */
    public void setCamera(JCamera camera)
    {
        this.activeCamera = camera;
        LogInfo("Set Camera");
    }

    /**
     * JWindow's update method. Called every refresh cycle to start the render and run the Update() behaviors
     * @param frameNumber Total frame number. Useful for keeping track of frames.
     */
    private void update(int frameNumber) {
        LogExtra(String.format("New frame (#%d)", frameNumber));
        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }
        runUpdateBehaviors();

        if(EnginePrefs.aggressiveGC)
            System.gc();

    }

    /**
     * Goes through every object in the scene to run their Update() functions.
     * Objects which don't override the function will not have any function
     */
    private void runUpdateBehaviors() {
        for (ObjRef objRef : JSceneManager.activeScene.sceneObjects) {
            if(objRef == null)
                return;
            if(objRef.objRef.getActive())
                objRef.objRef.Update();
        }
    }

    /**
     * Get the isFocused value
     * @return Returns if the window is currently focused or not
     */
    public boolean getIsFocused(){ return isFocused;}
}
