package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.Utility.Input;

public class JSceneManager {
    private static JScene activeScene;
    private static JWindow window;
    private static JCamera activeCamera;

    public static JScene getActiveScene() {
        return activeScene;
    }

    public static void init(JScene newScene, JWindow newWindow, JCamera newMainCamera)
    {
        JSceneManager.activeScene = newScene;
        JSceneManager.window = newWindow;
        JSceneManager.activeCamera = newMainCamera;
        window.setCamera(newMainCamera);
        Input.init(newWindow.scene);
    }

    public static void setActiveScene(JScene newScene) {
        activeCamera.setActiveScene(newScene);
        window.parent.getChildren().remove(activeScene.uiObjects);
        JSceneManager.activeScene = newScene;
        window.parent.getChildren().add(activeScene.uiObjects);

    }
    public static void setWindow(JWindow newWindow)
    {
        newWindow.setCamera(activeCamera);
        newWindow.setScene(window.getScene());
        JSceneManager.window = newWindow;
    }
    public static void setActiveCamera(JCamera newMainCamera)
    {
        JSceneManager.activeCamera = newMainCamera;
        window.setCamera(newMainCamera);
    }

    public static JWindow getWindow() {
        return window;
    }

    public static JCamera getActiveCamera() {
        return activeCamera;
    }
}
