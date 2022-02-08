package com.JEngine.Game;

import com.JEngine.PrimitiveTypes.Position.Angle;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;

import java.awt.*;

/** JEngine.Pawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class
 * **/

public class Pawn extends Sprite {
    public Pawn(Transform transform, Image newSprite) {
        super(transform, newSprite);
        Move(Direction.none, new Angle(0), 5f);
    }

    public void Move(Direction direction, Angle angle, float magnitude)
    {

    }
}
