package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Angle;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** JEngine.Pawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class
 * **/

public class JPawn extends Sprite {
    public JPawn(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform,newSprite, JIdentity);

    }

    public void Move(Direction direction, float speed)
    {
        Angle angle = new Angle(0);
        Vector3 oldPos = super.transform.position;
        float totalXMovement = 0;
        float totalYMovement = 0;

        switch (direction)
        {
            case Up -> {
                angle.angle = 0;
                totalYMovement = speed*-1;
            }
            case UpRight -> {
                angle.angle = 45;
                totalXMovement = speed;
                totalYMovement = speed*-1;
            }
            case Right -> {
                angle.angle = 90;
                totalXMovement = speed;
            }
            case DownRight -> {
                angle.angle = 135;
                totalXMovement = speed;
                totalYMovement = speed;
            }
            case Down ->{
                angle.angle = 180;
                totalYMovement = speed;
            }
            case DownLeft -> {
                angle.angle = 225;
                totalXMovement = -speed;
                totalYMovement = -speed*-1;
            }
            case Left -> {
                angle.angle = 270;
                totalXMovement = -speed;
            }
            case UpLeft -> {
                angle.angle = 315;
                totalXMovement = -speed;
                totalYMovement = speed*-1;
            }
        }


        // actual logic that moves pawn
        super.transform.setPosition(new Vector3(super.transform.position.x + totalXMovement, super.transform.position.y + totalYMovement, super.transform.position.z));
        super.LogExtra(String.format("Moved pawn %.2f° %.2f unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", angle.angle, speed, oldPos.x, oldPos.y, oldPos.z, super.transform.position.x, super.transform.position.y, super.transform.position.z));
    }
}