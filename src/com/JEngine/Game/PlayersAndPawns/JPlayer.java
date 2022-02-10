package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JIcon;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** JEngine.Player (c) Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn which takes keyboard input from the user
 *
 * Usage:
 * A player is the main pawn. When placed it will take user input.
 * **/

public class JPlayer extends JPawn {

    public JPlayer(Transform transform, JIcon newSprite, JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }
}
