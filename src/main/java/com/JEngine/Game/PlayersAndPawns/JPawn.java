package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** com.JEngine.Pawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 * It does not listen for key inputs, unlike JPlayer
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class
 * **/

public class JPawn extends JSprite {
    private JBoxCollider collider;
    public JPawn(Transform transform, JImage newSprite, JIdentity jIdentity) {
        super(transform,newSprite, jIdentity);
    }

    public void onCollisionEnter(JPawn other) {

    }

    public JBoxCollider getCollider() {
        return collider;
    }

    public void setCollider(JBoxCollider collider) {
        this.collider = collider;
    }

    public void Rotate(Vector2 direction, float amount, boolean clockwise) {
        int d = clockwise ? 1 : -1;
        direction.setX(getTransform().getRotation().x + direction.getX()*amount*d);
        direction.setY(getTransform().getRotation().y + direction.getY()*amount*d);
        getTransform().setRotation(new Vector3(direction,getTransform().rotation.z));
    }

    public void Move(Direction direction, int speed)
    {
        Angle angle = new Angle(0);
        Vector3 oldPos = super.getTransform().position;
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
        super.getTransform().setPosition(new Vector3(super.getTransform().position.x + totalXMovement, super.getTransform().position.y + totalYMovement, super.getTransform().position.z));
        LogAnnoyance(String.format("Moved pawn %.2f° %d unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", angle.angle, originalSpeed, oldPos.x, oldPos.y, oldPos.z, super.getTransform().position.x, super.getTransform().position.y, super.getTransform().position.z));
    }
}
