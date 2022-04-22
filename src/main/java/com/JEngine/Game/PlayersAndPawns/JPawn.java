package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.JMath;

/** JPawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 * It does not listen for key inputs.
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class and can take inputs
 * **/

public class JPawn extends JSprite {
    // Every JPawn has a collider, it must be initialized though
    private JBoxCollider collider;

    public JPawn(Transform transform, JImage newSprite, JIdentity jIdentity) {
        super(transform,newSprite, jIdentity);
    }

    /**
     * onCollisionEnter event, called when the Pawn's collider hits another object
     * @param other the other object that the collider has collided with
     */
    public void onCollisionEnter(JObject other) {
    }

    /**
     * get the pawn's collider
     * @return the pawn's collider
     */
    public JBoxCollider getCollider() {
        return collider;
    }

    /**
     * set the pawn's collider
     * @param collider the new collider
     */
    public void setCollider(JBoxCollider collider) {
        this.collider = collider;
    }

    /**
     * Rotate the pawn
     * @param direction the direction to rotate the pawn (x,y)
     * @param amount amount to rotate the pawn (x>0)
     * @param clockwise true if the rotation is clockwise, false if counterclockwise
     */
    public void Rotate(Vector2 direction, float amount, boolean clockwise) {
        amount = JMath.clamp(0,Float.MAX_VALUE, amount);

        int d = clockwise ? 1 : -1;
        direction.setX(getTransform().getRotation().x + direction.getX()*amount*d);
        direction.setY(getTransform().getRotation().y + direction.getY()*amount*d);

        getTransform().setRotation(new Vector3(direction,getTransform().rotation.z));
    }

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    public boolean canMove(float xDisplacement, float yDisplacement) {
        JBoxCollider tmpCollider = new JBoxCollider(new Transform(getTransform()), new JIdentity("tmpCollider", "boxCollider"), getSprite().getWidth(), getSprite().getHeight(), this, false);

        tmpCollider.getTransform().setPosition(new Vector3(getTransform().getPosition().x + xDisplacement, getTransform().getPosition().y + yDisplacement, getTransform().getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    /**
     * Move the pawn
     * @param direction direction to move the pawn (8 cardinal directions)
     * @param speed amount to move the pawn
     */
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
        // if the collider is hard, check if you will collide with another hard collider
        if(getCollider() != null)
        {
            if(!getCollider().isTrigger())
            {
                if(canMove(totalXMovement, totalYMovement))
                {
                    super.getTransform().setPosition(new Vector3(super.getTransform().position.x + totalXMovement, super.getTransform().position.y + totalYMovement, super.getTransform().position.z));
                }
                return;
            }
        }

        // actual logic that moves pawn
        super.getTransform().setPosition(new Vector3(super.getTransform().position.x + totalXMovement, super.getTransform().position.y + totalYMovement, super.getTransform().position.z));
        LogDebug(String.format("Moved pawn %.2f° %d unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", angle.angle, originalSpeed, oldPos.x, oldPos.y, oldPos.z, super.getTransform().position.x, super.getTransform().position.y, super.getTransform().position.z));
    }
}
