package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.Utility.JUtility;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

public class JWindow extends Thing{

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

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene newScene) {
        scene = newScene;
    }


    public void setIcon(JImage newIcon) {
        if (newIcon.getImage() != null)
        {
            window.getIcons().add(newIcon.getImage());
            return;
        }
        LogWarning("Tried to set window icon to a null image");

    }

    public JWindow(int sizeX, int sizeY, String title, Stage window) {
        super(true);
        scene = new Scene(root, sizeX,sizeY);
        root.getChildren().add(objects);
        root.getChildren().add(uiObjects);
        this.window = window;
        this.window.setTitle(title);
        this.window.setResizable(false);
        this.window.setScene(scene);
        this.window.show();
        this.window.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, JUtility::exitHandler);
    }


/*    public void AddUpdateBehavior(Behavior newBehavior) {
        for (int i = 0; i < behaviors.length; i++) {
            if (behaviors[i] == null) {
                behaviors[i] = newBehavior;
                break;
            }
        }
    }*/

    public void refreshWindow(Group gameObjects, Group newUIObjects) {
        //newPanel.setLayout(null);
        Platform.runLater(() -> {
            objects = gameObjects;
            uiObjects = newUIObjects;
            root.getChildren().clear();
            root.getChildren().add(objects);
            root.getChildren().add(uiObjects);
        });
    }

    public void setTargetFPS(float newTargetFPS) {
        targetFPS = newTargetFPS;
    }

    public void start()
    {
        if(isActive)
        {
            LogError("Window is already active! Cannot start another update thread.");
            return;
        }
        isActive = true;
        updateThread = new Thread(this::refresh);
        updateThread.start();
        LogInfo("Successfully started window");

    }

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

    // Refresh rate logic
    public void refresh() {
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


    public void setCamera(JCamera camera)
    {
        this.activeCamera = camera;
        LogInfo("Set Camera");
    }

    void update(int frameNumber) {
        LogExtra(String.format("New frame (#%d)", frameNumber));

        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }

        runUpdateBehaviors();

    }

    private void runUpdateBehaviors() {
        for (ObjRef objRef : activeCamera.getActiveScene().sceneObjects) {
            if(objRef == null)
                return;
            objRef.objRef.Update();
        }
    }
}
