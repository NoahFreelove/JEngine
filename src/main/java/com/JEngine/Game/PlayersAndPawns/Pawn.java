package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Components.Collider_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.*;
import com.JEngine.Core.Identity;
import com.JEngine.Core.GameObject;
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
        boolean result = Move(totalXMovement,totalYMovement);
        //System.out.println("Result:" + result);
        return result;
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
        boolean foundCollider = false;

        for (Component component: getComponentByName("Collider"))
        {
            if(component == null)
                continue;

            if (component instanceof Collider_Comp collider) {
                foundCollider = true;
                if(collider.isTrigger())
                {
                   setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
                   return true;
                }
                else if(collider.canMove(totalXMovement, totalYMovement))
                {
                    setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
                    //System.out.println("Moved: " + totalXMovement + "," + totalYMovement);
                    return true;
                }
            }
        }
        // if a collider was found, but was in the way, it couldn't move.
        if(foundCollider)
            return false;

        // if there is no collider anywhere, just move
        setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
        return true;
    }

    public boolean isCollidingWithAny(){
        for (Component component :
                getComponents()) {
            if(component instanceof Collider_Comp collider)
            {
                return collider.isCollidingWithAny(!collider.isTrigger());
            }
        }
        return false;
    }
}
