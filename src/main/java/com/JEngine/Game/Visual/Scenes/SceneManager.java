package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JWindow;
import javafx.application.Platform;

/** SceneManager (c) Noah Freelove
 * Brief Explanation:
 * SceneManager is a class that manages the current scene, window, and main camera.
 * Lets you reference the current scene, window, and camera from anywhere.
 */

public class SceneManager {
    private static JScene activeScene;
    private static JWindow window;
    private static JCamera activeCamera;
    private static boolean hasInit;
    /**
     * Get the active scene
     * @return active scene
     */
    public static JScene getActiveScene() {
        return activeScene;
    }

    /**
     * Set the initial values
     * @param newScene initial scene
     * @param newWindow initial window
     * @param newMainCamera initial main camera
     */
    public static void init(JScene newScene, JWindow newWindow, JCamera newMainCamera)
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
    public static void switchScene(JScene newScene) {
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
    public static void setWindow(JWindow newWindow)
    {
        newWindow.setScene(window.getScene());
        SceneManager.window = newWindow;
    }

    /**
     * Set the active camera
     * @param newMainCamera new active camera
     */
    public static void setActiveCamera(JCamera newMainCamera)
    {
        SceneManager.activeCamera = newMainCamera;
    }

    /**
     * Get the active window
     * @return active window
     */
    public static JWindow getWindow() {
        return window;
    }

    /**
     * Get the active camera
     * @return the active camera
     */
    public static JCamera getActiveCamera() {
        return activeCamera;
    }
}
