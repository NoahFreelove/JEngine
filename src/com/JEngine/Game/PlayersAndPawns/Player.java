package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** JEngine.Player (c) Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn which takes keyboard input from the user
 *
 * Usage:
 * A player is the main pawn. When placed it will take user input.
 * **/

public class Player extends Pawn{

    public Player(Transform transform, JImage newSprite) {
        super(transform, newSprite);
    }
}
