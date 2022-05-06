package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.PlayersAndPawns.Colliders.BoxCollider;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.Identity;
import com.JEngine.PrimitiveTypes.GameObject;
import com.JEngine.Utility.GameMath;

/** JPawn (c) Noah Freelove
 * Brief Explanation:
 * A Pawn is a sprite which can be directly moved and manipulated by a player or bot
 * It does not listen for key inputs.
 *
 * Usage:
 * A pawn can be used for an object that can be moved by the player or bot, but that is not the player.
 * The player class extends off the pawn class and can take inputs
 * **/

public class Pawn extends Sprite {
    // Every JPawn has a collider, it must be initialized though
    private BoxCollider collider;

    public Pawn(Transform transform, GameImage newSprite, Identity identity) {
        super(transform,newSprite, identity);
    }

    /**
     * onCollisionEnter event, called when the Pawn's collider hits another object
     * @param other the other object that the collider has collided with
     */
    public void onCollisionEnter(GameObject other) {
    }

    /**
     * get the pawn's collider
     * @return the pawn's collider
     */
    public BoxCollider getCollider() {
        return collider;
    }

    /**
     * set the pawn's collider
     * @param collider the new collider
     */
    public void setCollider(BoxCollider collider) {
        this.collider = collider;
    }

    /**
     * Rotate the pawn
     * @param direction the direction to rotate the pawn (x,y)
     * @param amount amount to rotate the pawn (x>0)
     * @param clockwise true if the rotation is clockwise, false if counterclockwise
     */
    public void Rotate(Vector2 direction, float amount, boolean clockwise) {
        amount = GameMath.clamp(0,Float.MAX_VALUE, amount);

        int d = clockwise ? 1 : -1;
        direction.setX(getTransform().getRotation().x + direction.x*amount*d);
        direction.setY(getTransform().getRotation().y + direction.x*amount*d);

        getTransform().setRotation(new Vector3(direction,getTransform().rotation.z));
    }

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    public boolean canMove(float xDisplacement, float yDisplacement) {
        BoxCollider tmpCollider = new BoxCollider(new Transform(getTransform()), new Identity("tmpCollider", "boxCollider"), (getSprite().getWidth()), (getSprite().getHeight()), this, false);

        tmpCollider.getTransform().setPosition(new Vector3(getTransform().getPosition().x + xDisplacement, getTransform().getPosition().y + yDisplacement, getTransform().getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    /**
     * Move the pawn
     * @param direction direction to move the pawn (8 cardinal directions)
     * @param speed amount to move the pawn
     */
    public boolean Move(Direction direction, int speed)
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
        LogDebug(String.format("Moved pawn %.2f° %d unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", angle.angle, originalSpeed, oldPos.x, oldPos.y, oldPos.z, super.getTransform().position.x, super.getTransform().position.y, super.getTransform().position.z));
        return Move(totalXMovement,totalYMovement);
    }
    public boolean Move(Vector2 direction, float speed)
    {
        direction = new Vector2(GameMath.clamp(-1,1,direction.x), GameMath.clamp(-1,1,direction.y));
        direction = direction.multiply(speed);
        float totalXMovement = direction.x;
        float totalYMovement = direction.y;
        // if the collider is hard, check if you will collide with another hard collider
        return Move(totalXMovement,totalYMovement);
    }

    public boolean Move(Vector2 direction, Vector2 magnitude)
    {
        direction = new Vector2(GameMath.clamp(-1,1,direction.x), GameMath.clamp(-1,1,direction.y));
        direction = direction.multiply(magnitude);
        float totalXMovement = direction.x;
        float totalYMovement = direction.y;
        // if the collider is hard, check if you will collide with another hard collider
        return Move(totalXMovement,totalYMovement);
    }

    private boolean Move(float totalXMovement, float totalYMovement){
        // if the collider is hard, check if you will collide with another hard collider
        if(getCollider() != null)
        {
            if(getCollider().isTrigger())
            {
                // if the collider is soft, you can move wherever
                super.getTransform().setPosition(new Vector3(super.getTransform().position.x + totalXMovement, super.getTransform().position.y + totalYMovement, super.getTransform().position.z));
                return true;
            }
            else if(canMove(totalXMovement, totalYMovement))
            {
                super.getTransform().setPosition(new Vector3(super.getTransform().position.x + totalXMovement, super.getTransform().position.y + totalYMovement, super.getTransform().position.z));

                return true;
            }
            // return false if you can't move
            return false;
        }
        // actual logic that moves pawn
        return false;
    }
}
