package com.JEngine.Game.Visual;

import com.JEngine.Utility.Input;
import javafx.scene.Scene;

public class JSceneManager {
    public static JScene scene;
    public static JWindow window;
    public static JCamera mainCamera;

    public static JScene getScene() {
        return scene;
    }

    public static void init(JScene newScene, JWindow newWindow, JCamera newMainCamera)
    {
        JSceneManager.scene = newScene;
        JSceneManager.window = newWindow;
        JSceneManager.mainCamera = newMainCamera;
        Input.init(scene.window.scene);
    }

    public static void setScene(JScene newScene) {
        JSceneManager.scene = newScene;
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
