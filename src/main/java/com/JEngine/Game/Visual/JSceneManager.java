package com.JEngine.Game.Visual;

import com.JEngine.Utility.Input;

public class JSceneManager {
    public static JScene activeScene;
    public static JWindow window;
    public static JCamera mainCamera;

    public static JScene getActiveScene() {
        return activeScene;
    }

    public static void init(JScene newScene, JWindow newWindow, JCamera newMainCamera)
    {
        JSceneManager.activeScene = newScene;
        JSceneManager.window = newWindow;
        JSceneManager.mainCamera = newMainCamera;
        Input.init(activeScene.window.scene);
    }

    public static void setActiveScene(JScene newScene) {
        JSceneManager.activeScene = newScene;
        mainCamera.setActiveScene(newScene);
    }
    public static void setWindow(JWindow newWindow)
    {
        newWindow.setCamera(mainCamera);
        newWindow.setScene(window.getScene());
        JSceneManager.window = newWindow;
    }
    public static void setMainCamera(JCamera newMainCamera)
    {
        JSceneManager.mainCamera = newMainCamera;
        window.setCamera(newMainCamera);
    }
}
