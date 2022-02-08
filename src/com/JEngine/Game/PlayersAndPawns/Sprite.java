package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import com.JEngine.PrimitiveTypes.Position.Transform;

import java.awt.*;

/** JEngine.Sprite (c) Noah Freelove
 * Brief Explanation:
 * A sprite is an object with an image
 *
 * Usage:
 * A sprite is handy for creating images on screen at a precise location
 * Like an object, a sprite cannot be moved directly but can be updated with the parent's transform functions
 * **/

public class Sprite extends Object {
    private JImage sprite;

    public JImage getSprite() {return sprite;}
    public void setSprite(JImage newSprite) {sprite = newSprite;}

    public Sprite(Transform transform, JImage newSprite) {
        super(transform);
        setSprite(newSprite);
    }
}
