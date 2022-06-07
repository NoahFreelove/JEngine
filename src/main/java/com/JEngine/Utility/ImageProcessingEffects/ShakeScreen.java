package com.JEngine.Utility.ImageProcessingEffects;

import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class ShakeScreen {
    public static void shake(int intensity, double duration) {
        Group root = SceneManager.getWindow().parent;
        // shake the root on an animation timer
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                root.setTranslateX(Math.random() * intensity - intensity / 2f);
                root.setTranslateY(Math.random() * intensity - intensity / 2f);
            }

        };
        // set duration

        at.start();
        GameTimer gt = new GameTimer((long) (duration * 1000), args -> {
            at.stop();
            root.setTranslateX(0);
            root.setTranslateY(0);
        }, true);
        gt.start();
    }
}
