package com.JEngine.Game.Visual.Animation;

import com.JEngine.PrimitiveTypes.GameImage;

/** AnimFrame (c) Noah Freelove
 * Brief Explanation:
 * A frame for JAnimationTimeline.
 * Contains the necessary information to render a frame of an animation.
 * **/

public class AnimFrame {
    AnimState state; // state of the anim it belongs to
    GameImage image;
    int duration; // in frames

    public AnimFrame(AnimState state, GameImage image, int duration) {
        this.state = state;
        this.image = image;
        this.duration = duration;
    }
}
