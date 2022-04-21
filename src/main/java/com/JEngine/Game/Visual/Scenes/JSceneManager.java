package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.Utility.Input;

/** JSceneManager (c) Noah Freelove
 * Brief Explanation:
 * JSceneManager is a class that manages the current scene, window, and main camera.
 * Lets you reference the current scene, window, and camera from anywhere.
 */

public class JSceneManager {
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
        JSceneManager.activeScene = newScene;
        JSceneManager.window = newWindow;
        JSceneManager.activeCamera = newMainCamera;
        JSceneManager.activeCamera.setActive(true);
        JSceneManager.window.start();
        window.setCamera(newMainCamera);
        Input.init(newWindow.scene);
    }

    /**
     * Set the active scene
     * @param newScene new active scene
     */
    public static void setActiveScene(JScene newScene) {
        activeCamera.setActiveScene(newScene);
        window.parent.getChildren().remove(activeScene.uiObjects);
        JSceneManager.activeScene = newScene;
        window.parent.getChildren().add(activeScene.uiObjects);

    }

    /**
     * Set the active window
     * @param newWindow new active window
     */
    public static void setWindow(JWindow newWindow)
    {
        newWindow.setCamera(activeCamera);
        newWindow.setScene(window.getScene());
        JSceneManager.window = newWindow;
    }

    /**
     * Set the active camera
     * @param newMainCamera new active camera
     */
    public static void setActiveCamera(JCamera newMainCamera)
    {
        JSceneManager.activeCamera = newMainCamera;
        window.setCamera(newMainCamera);
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
