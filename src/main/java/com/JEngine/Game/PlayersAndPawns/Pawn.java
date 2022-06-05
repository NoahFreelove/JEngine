package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.*;
import com.JEngine.Core.Identity;
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

    public Pawn(Transform transform, GameImage newSprite, Identity identity) {
        super(transform,newSprite, identity);
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
     * Move the pawn
     * @param direction direction to move the pawn (8 cardinal directions)
     * @param speed amount to move the pawn
     */
    public int Move(Direction direction, float speed)
    {
        Vector3 oldPos = super.getTransform().position;
        float totalXMovement = 0;
        float totalYMovement = 0;
        float originalSpeed = speed;
        switch (direction)
        {
            // need to check collision on every move
            case Up -> {
                while (speed>0)
                {
                    totalYMovement -= 1;
                    speed--;
                }
            }
            case UpRight -> {
                while (speed>0)
                {
                    totalXMovement += 1;
                    totalYMovement -= 1;
                    speed--;
                }
            }
            case Right -> {
                while (speed>0)
                {
                    totalXMovement += 1;
                    speed--;
                }
            }
            case DownRight -> {
                while (speed>0)
                {
                    totalXMovement += 1;
                    totalYMovement += 1;
                    speed--;
                }
            }
            case Down ->{
                while (speed>0)
                {
                    totalYMovement += 1;
                    speed--;
                }
            }
            case DownLeft -> {
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
                while (speed>0)
                {
                    totalXMovement -= 1;
                    speed--;
                }
            }
            case UpLeft -> {
                while (speed>0)
                {
                    totalXMovement -= 1;
                    totalYMovement -= -1;
                    speed--;
                }
            }
        }
        LogDebug(String.format("Moved pawn %f unit(s) | OLD POS {%.2f,%.2f,%.2f} | NEW POS {%.2f,%.2f,%.2f}", originalSpeed, oldPos.x, oldPos.y, oldPos.z, super.getTransform().position.x, super.getTransform().position.y, super.getTransform().position.z));
        return Move(totalXMovement,totalYMovement);
    }
    public int Move(Vector2 direction, float speed)
    {
        return Move(direction,new Vector2(speed,speed));
    }

    public int Move(Vector2 direction, Vector2 magnitude)
    {
        direction = new Vector2(GameMath.clamp(-1,1,direction.x), GameMath.clamp(-1,1,direction.y));
        direction = direction.multiply(magnitude);
        float totalXMovement = direction.x;
        float totalYMovement = direction.y;
        // if the collider is hard, check if you will collide with another hard collider
        return Move(totalXMovement,totalYMovement);
    }

    // 0 - could not move
    // 1 - moved
    // 2 - hit ceiling
    private int Move(float totalXMovement, float totalYMovement){
        boolean foundCollider = false;

        for (Collider_Comp collider: getColliders())
        {
            if(collider == null)
                continue;
             foundCollider = true;
                if(collider.isTrigger())
                {
                   setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
                   return 1;
                }
                else if(collider.canMove(totalXMovement, totalYMovement))
                {
                    setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
                    //System.out.println("Moved: " + totalXMovement + "," + totalYMovement);
                    return 1;
                }
        }
        // if a collider was found, but was in the way, it couldn't move.
        if(foundCollider)
        {
            // if you hit ceiling, don't move, and return 2
            if(totalYMovement<=0){
                return 2;
            }
            return 0;
        }

        // if there is no collider anywhere, just move
        setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
        return 1;
    }

    public boolean isCollidingWithAny(){
        for (Component component :
                getColliders()) {
            if(component instanceof Collider_Comp collider)
            {
                return collider.isTrigger()? collider.isCollidingWithAny() : collider.isCollidingWithHard();
            }
        }
        return false;
    }
}
