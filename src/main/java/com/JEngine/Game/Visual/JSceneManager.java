package com.JEngine.Game.Visual;

import javafx.scene.Scene;

public class JSceneManager {
    public static JScene scene;

    public static JScene getScene() {
        return scene;
    }

    public static void setScene(JScene scene) {
        JSceneManager.scene = scene;
    }
}
