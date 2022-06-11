package com.JEngine.Utility.ImageProcessingAndEffects;

import javafx.scene.effect.Lighting;

public class GameLight {
    public Lighting light;
    public boolean keepOnSceneSwitch;

    public GameLight(Lighting light, boolean keepOnSceneSwitch) {
        this.light = light;
        this.keepOnSceneSwitch = keepOnSceneSwitch;
    }
}
