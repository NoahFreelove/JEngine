package com.JEngine.PrimitiveTypes;

import com.JEngine.Game.Sprite;
import com.JEngine.PrimitiveTypes.Position.Transform;

import java.awt.*;

/** JEngine.Pawn (c) Noah Freelove
 * Brief Explanation:
 * A sprite is an object with an image
 *
 * Usage:
 * A sprite is handy for creating images on screen at a precise location
 * Like an object, a sprite cannot be moved directly but can be updated with the parent's transform functions
 * **/

public class Pawn extends Sprite {
    public Pawn(Transform transform, Image newSprite) {
        super(transform, newSprite);
    }
}
