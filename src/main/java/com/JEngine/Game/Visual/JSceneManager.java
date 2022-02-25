package com.JEngine.Game.Visual;

import com.JEngine.Utility.Input;
import javafx.scene.Scene;

public class JSceneManager {
    public static JScene scene;

    public static JScene getScene() {
        return scene;
    }

    public static void setScene(JScene scene) {
        JSceneManager.scene = scene;
        Input.init(scene.window.scene);
    }
}
