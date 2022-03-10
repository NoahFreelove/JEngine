package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Angle;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** com.JEngine.Pawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class
 * **/

public class JPawn extends JSprite {
    public JPawn(Transform transform, JImage newSprite, JIdentity jIdentity) {
        super(transform,newSprite, jIdentity);

    }

    public void Move(Direction direction, int speed)
    {
        Angle angle = new Angle(0);
        Vector3 oldPos = super.transform.position;
        float totalXMovement = 0;
        float totalYMovement = 0;
        int originalSpeed = speed;
        switch (direction)
        {
            // need to check collision on every move
            case Up -> {
                angle.angle = 0;
                while (speed>0)
                {
                    totalYMovement -= 1;
                    speed--;
                }
            }
            case UpRight -> {
                angle.angle = 45;
                while (speed>0)
                {
                    totalXMovement += 1;
                    totalYMovement -= 1;
                    speed--;
                }
            }
            case Right -> {
                angle.angle = 90;
                while (speed>0)
                {
                    totalXMovement += 1;
                    speed--;
                }
            }
            case DownRight -> {
                angle.angle = 135;
                while (speed>0)
                {
                    totalXMovement += 1;
                    totalYMovement += 1;
                    speed--;
                }
            }
            case Down ->{
                angle.angle = 180;
                while (speed>0)
                {
                    totalYMovement += 1;
                    speed--;
                }
            }
            case DownLeft -> {
                angle.angle = 225;
                totalXMovement = -speed;
                totalYMovement = -speed*-1;
                while (speed>0)
                {
                    totalXMovement -= 1;
                    totalYMovement += 1;
                    speed--;
                }
            }
            case Left -> {
                angle.angle = 270;
                while (speed>0)
                {
                    totalXMovement -= 1;
                    speed--;
                }
            }
            case UpLeft -> {
                angle.angle = 315;
                while (speed>0)
                {
                    totalXMovement -= 1;
                    totalYMovement -= -1;
                    speed--;
                }
            }
        }


        // actual logic that moves pawn
        super.transform.setPosition(new Vector3(super.transform.position.x + totalXMovement, super.transform.position.y + totalYMovement, super.transform.position.z));
        super.LogExtra(String.format("Moved pawn %.2f° %d unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", angle.angle, originalSpeed, oldPos.x, oldPos.y, oldPos.z, super.transform.position.x, super.transform.position.y, super.transform.position.z));
    }
}
