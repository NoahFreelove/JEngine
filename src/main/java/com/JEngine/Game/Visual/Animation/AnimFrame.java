package com.JEngine.Game.Visual.Animation;

import com.JEngine.PrimitiveTypes.JImage;

/** AnimFrame (c) Noah Freelove
 * Brief Explanation:
 * A frame for JAnimationTimeline.
 * Contains the necessary information to render a frame of an animation.
 * **/

public class AnimFrame {
    AnimState state; // state of the anim it belongs to
    JImage image;
    int duration; // in frames

    public AnimFrame(AnimState state, JImage image, int duration) {
        this.state = state;
        this.image = image;
        this.duration = duration;
    }
}
