package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** Sprite (c) Noah Freelove
 * Brief Explanation:
 * A sprite is an object with an image
 *
 * Usage:
 * A sprite is handy for creating images on screen at a precise location
 * Like an object, a sprite cannot be moved directly but can be updated with the parent's transform functions
 * **/

public class JSprite extends JObject {
    private JImage sprite;

    /**
     * Default getter for the sprite
     * @return the sprite
     */
    public JImage getSprite() {return sprite;}

    /**
     * Default setter for the sprite
     * @param newSprite the new sprite
     */
    public void setSprite(JImage newSprite) {sprite = newSprite;}

    // Default constructor
    public JSprite(Transform transform, JImage newSprite, JIdentity jIdentity) {
        super(transform, jIdentity);
        setSprite(newSprite);
    }
}
