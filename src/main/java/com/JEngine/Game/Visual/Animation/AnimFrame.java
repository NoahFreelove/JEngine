package com.JEngine.Game.Visual.Animation;

import com.JEngine.PrimitiveTypes.JImage;

public class AnimFrame {
    AnimState state;
    JImage image;
    int duration; // in frames

    public AnimFrame(AnimState state, JImage image, int duration) {
        this.state = state;
        this.image = image;
        this.duration = duration;
    }
}
