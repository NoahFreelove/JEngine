package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;

/**
 * JBoxCollider (c) Noah Freelove
 *
 * Brief Explanation:
 * A simple box collider for JObjects
 *
 * Usage:
 * Detect if a collider is in range of another collider
 *
 */
public class JBoxCollider extends JObject {

    // the JPawn that this collider belongs to
    private final JObject parent;

    public int sizeX;
    public int sizeY;

    // amount of times a collision occurred with this collider
    private int calls = 0;

    private boolean trigger = true;

    /**
     * @param transform - The transform of the object
     * @param JIdentity - The JIdentity of the object
     * @param sizeX - The size of the object in the x direction
     * @param sizeY - The size of the object in the y direction
     * @param parent - The parent of the object
     */
    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JObject parent) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.parent = parent;
    }

    /**
     * @param transform - The transform of the object
     * @param JIdentity - The JIdentity of the object
     * @param sizeX - The size of the object in the x direction
     * @param sizeY - The size of the object in the y direction
     * @param parent - The parent of the object
     * @param trigger - Whether the collider is a trigger
     */
    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JObject parent, boolean trigger) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.parent = parent;
        this.trigger = trigger;
    }

    /**
     * @return how many times the collider has collided
     */
    public int getTimesCollided(){return calls;}

    /**
     * @param otherObject - The other object to check
     * @return if the collider is colliding with the other object
     */
    public boolean isCollidingWith(JBoxCollider otherObject)
    {
        if(otherObject == null)
        {
            return false;
        }
        // logic to check if positions are in range (checks only x and y values, z values are irrelevant)
        int x1 = (int)otherObject.getTransform().position.x;
        int y1 = (int)otherObject.getTransform().position.y;
        int x2 = (int)otherObject.getTransform().position.x + otherObject.sizeX;
        int y2 = (int)otherObject.getTransform().position.y+ otherObject.sizeY;
        int x3 = (int)getTransform().position.x;
        int y3 = (int)getTransform().position.y;
        int x4 = (int)getTransform().position.x + sizeX;
        int y4 = (int)getTransform().position.y+ sizeY;

        return (x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2);
    }

    /**
     * get the parent of the collider
     * @return the parent of the collider
     */
    public JObject getParent() {
        return parent;
    }

    /**
     * Checks collision with every object in the scene
     * Should be called at update if you want it to update like a normal collider
     */
    public void checkAllCollision()
    {
        for (ObjRef o :
                SceneManager.getActiveScene().getObjects()) {
            if (o == null || o.objRef == null) continue;
            JBoxCollider pawnCollider;
            // make sure the object has a collider
            if(o.objRef instanceof JPawn p)
            {
                pawnCollider = p.getCollider();
            }
            else
            {
                continue;
            }
            // make sure we don't check the collider against itself, a hard collider, or a null collider
            if(pawnCollider == null || pawnCollider == this || !pawnCollider.trigger || parent == pawnCollider.parent)
            {
                continue;
            }
            // actually check collision
            if(isCollidingWith(pawnCollider))
            {
                // call onCollision events on both objects
                pawnCollider.onCollision(this);
                this.onCollision(pawnCollider);
            }
        }
    }

    /*
    * check if colliding with an object that is not a trigger
    * mix between isCollidingWith and checkAllCollision
    * Used exclusively for the JPawn, but can be for your own purposes
    * */
    public boolean isCollidingWithHard()
    {
        // go through every sceneobject check for hard collider
        for (ObjRef o :
                SceneManager.getActiveScene().getObjects()) {
            if (o == null) continue;
            JBoxCollider pawnCollider;

            try {
                pawnCollider = ((JPawn) o.objRef).getCollider();
            }
            catch (Exception e)
            {
                continue;
            }
            if(pawnCollider == null || pawnCollider == this || pawnCollider.trigger || parent == pawnCollider.parent)
            {
                continue;
            }
            // actually check collision
            if(isCollidingWith(pawnCollider))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * called when a collision occurs
     * @param otherObj  - the other object that collided with this object
     */
    public void onCollision(JBoxCollider otherObj){
        calls++;
        if(calls == 1)
            return;
        if(parent instanceof JPawn parentPawn && otherObj.parent instanceof JPawn otherPawn)
        {
            parentPawn.onCollisionEnter(otherPawn);
        }
    }

    /**
     * check if the collider is a trigger
     * @return if the collider is a trigger
     */
    public boolean isTrigger() {
        return trigger;
    }

    /**
     * set the collider to be a trigger
     * @param trigger - if the collider should be a trigger
     */
    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }
}
