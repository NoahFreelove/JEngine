package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** JEngine.Player (c) Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn
 * **/

public class JPlayer extends JPawn {
    public JPlayer(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }
}
