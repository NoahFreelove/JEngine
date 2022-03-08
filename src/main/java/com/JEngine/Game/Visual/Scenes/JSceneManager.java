package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.Utility.Input;
import javafx.scene.Group;

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
        window.setCamera(newMainCamera);
        Input.init(newWindow.scene);
    }

    public static void setActiveScene(JScene newScene) {
        mainCamera.setActiveScene(newScene);
        window.parent.getChildren().remove(activeScene.uiObjects);
        JSceneManager.activeScene = newScene;
        window.parent.getChildren().add(activeScene.uiObjects);

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
