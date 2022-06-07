package com.JEngine.Utility.ImageProcessingEffects;

import javafx.scene.effect.Lighting;

public class GameLight {
    public Lighting light;
    public boolean keepOnSceneSwitch;

    public GameLight(Lighting light, boolean keepOnSceneSwitch) {
        this.light = light;
        this.keepOnSceneSwitch = keepOnSceneSwitch;
    }
}
