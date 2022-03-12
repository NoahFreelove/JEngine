package com.JEngine.Game.Visual;

import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.JUtility;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

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
    public boolean isActive;

    public Stage stage;
    public Scene scene;
    public JScene jscene;
    public JCamera activeCamera;

    private Thread updateThread;

    private float targetFPS = 30;
    public int totalFrames = 1;

    private Color backgroundColor = Color.WHITE;

    public Group parent = new Group();
    public Group sceneObjects = new Group();

    private boolean isFocused = true;
    private float scaleMultiplier = 1;
    private Group prevObj;

    /**
     * Default constructor
     * @param title Title of the window
     * @param window Default stage (Typically given by JavaFX public void start(Stage stage)
     */
    public JWindow(JScene scene, float scaleMultiplier, String title, Stage window) {
        super(true);
        try
        {
            parent.getChildren().add(sceneObjects);
            parent.getChildren().add(scene.uiObjects);
            this.scene = new Scene(parent,1280*scaleMultiplier,720*scaleMultiplier);
            this.jscene = scene;
        }
        catch (Exception e){
            System.out.println(e);
            this.scene = new Scene(parent, 1280*scaleMultiplier,720*scaleMultiplier);
            prevObj = sceneObjects;

        }

        this.stage = window;
        this.stage.setTitle(title);
        this.stage.setResizable(false);
        this.stage.setScene(this.scene);
        this.stage.show();
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, JUtility::exitWindow);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> isFocused = newValue.getValue());
        this.scaleMultiplier = scaleMultiplier;
    }

    public JWindow(float scaleMultiplier, String title, Stage window, StageStyle style) {
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
        this.stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, JUtility::exitWindow);
        window.focusedProperty().addListener((newValue, onHidden, onShown) -> isFocused = newValue.getValue());
        this.scaleMultiplier = scaleMultiplier;
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
    }
    public void setBackgroundColor(Color newColor){backgroundColor = newColor;}
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
     * Logic to update the window targetFPS times/second
     */
    private void refresh() {
        double SKIP_TICKS = (double) (1000 / targetFPS);
        double next_game_tick = System.currentTimeMillis();

        while (isActive) {
            while (System.currentTimeMillis() > next_game_tick) {
                update(totalFrames);
                totalFrames++;
                next_game_tick += SKIP_TICKS;

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
        runUpdateBehaviors();

        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }

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
        for (ObjRef objRef : JSceneManager.activeScene.sceneObjects) {
            if(objRef == null || objRef.objRef == null || objRef.objRef.JIdentity.compareTag("deleted"))
                continue;
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
