package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.GameObject;

/** Sprite (c) Noah Freelove
 * Brief Explanation:
 * A sprite is an object with an image
 *
 * Usage:
 * A sprite is handy for creating images on screen at a precise location
 * Like an object, a sprite cannot be moved directly but can be updated with the parent's transform functions
 * **/

public class Sprite extends GameObject {
    private GameImage sprite;

    /**
     * Default getter for the sprite
     * @return the sprite
     */
    public GameImage getSprite() {return sprite;}

    /**
     * Default setter for the sprite
     * @param newSprite the new sprite
     */
    public void setSprite(GameImage newSprite) {sprite = newSprite;}

    // Default constructor
    public Sprite(Transform transform, GameImage newSprite, Identity identity) {
        super(transform, identity);
        setSprite(newSprite);
    }
}
