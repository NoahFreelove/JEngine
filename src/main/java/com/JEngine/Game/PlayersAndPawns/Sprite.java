package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** JEngine.Sprite (c) Noah Freelove
 * Brief Explanation:
 * A sprite is an object with an image
 *
 * Usage:
 * A sprite is handy for creating images on screen at a precise location
 * Like an object, a sprite cannot be moved directly but can be updated with the parent's transform functions
 * **/

public class Sprite extends JObject {
    private JImage sprite;
    public JBoxCollider collider;

    public JImage getSprite() {return sprite;}
    public void setSprite(JImage newSprite) {sprite = newSprite;}

    public Sprite(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform, JIdentity);
        collider = new JBoxCollider(transform, JIdentity, false, newSprite.getXSize(), newSprite.getYSize());
        setSprite(newSprite);
    }
}
