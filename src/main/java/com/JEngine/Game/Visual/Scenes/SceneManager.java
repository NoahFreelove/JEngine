package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import javafx.application.Platform;

/** SceneManager (c) Noah Freelove
 * Brief Explanation:
 * SceneManager is a class that manages the current scene, window, and main camera.
 * Lets you reference the current scene, window, and camera from anywhere.
 */

public class SceneManager {
    private static GameScene activeScene;
    private static GameWindow window;
    private static GameCamera activeCamera;
    private static boolean hasInit;
    /**
     * Get the active scene
     * @return active scene
     */
    public static GameScene getActiveScene() {
        return activeScene;
    }

    /**
     * Set the initial values
     * @param newScene initial scene
     * @param newWindow initial window
     * @param newMainCamera initial main camera
     */
    public static void init(GameScene newScene, GameWindow newWindow, GameCamera newMainCamera)
    {
        if(hasInit)
            return;
        hasInit = true;
        SceneManager.activeScene = newScene;
        SceneManager.window = newWindow;
        SceneManager.activeCamera = newMainCamera;
        SceneManager.activeCamera.setActive(true);
        SceneManager.window.start();
    }

    /**
     * Set the active scene
     * @param newScene new active scene
     */
    public static void switchScene(GameScene newScene) {
        Platform.runLater(() -> {
            activeCamera.setActiveScene(newScene);
            window.parent.getChildren().remove(activeScene.uiObjects);
            SceneManager.activeScene = newScene;
            window.parent.getChildren().add(activeScene.uiObjects);
        } );
    }

    /**
     * Set the active window
     * @param newWindow new active window
     */
    public static void setWindow(GameWindow newWindow)
    {
        newWindow.setScene(window.getScene());
        SceneManager.window = newWindow;
    }

    /**
     * Set the active camera
     * @param newMainCamera new active camera
     */
    public static void setActiveCamera(GameCamera newMainCamera)
    {
        SceneManager.activeCamera = newMainCamera;
    }

    /**
     * Get the active window
     * @return active window
     */
    public static GameWindow getWindow() {
        return window;
    }

    /**
     * Get the active camera
     * @return the active camera
     */
    public static GameCamera getActiveCamera() {
        return activeCamera;
    }
}
