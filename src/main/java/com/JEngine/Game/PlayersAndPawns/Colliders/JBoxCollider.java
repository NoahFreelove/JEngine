package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

/**
 * JBoxCollider - A simple box collider for JObjects
 *
 * Has built in functionality to detect if another collider is in range, and to detect if it's a trigger or a hard one
 *
 * @author Noah Freelove
 * @version 1
 */
public class JBoxCollider extends JObject {
    private final JPawn parent;
    public int sizeX;
    public int sizeY;
    //GenericMethodCall onCollisionExit;

    private int calls = 0;

    private boolean trigger = true;

    /**
     * @param transform - The transform of the object
     * @param JIdentity - The JIdentity of the object
     * @param sizeX - The size of the object in the x direction
     * @param sizeY - The size of the object in the y direction
     * @param parent - The parent of the object
     */
    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JPawn parent) {
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
    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JPawn parent, boolean trigger) {
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
     * @param otherObj - The other object to check
     * @return
     */
    public boolean isCollidingWith(JBoxCollider otherObject)
    {
        if(otherObject == null)
        {
            return false;
        }

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

    public JPawn getParent() {
        return parent;
    }

    public void checkAllCollision()
    {
        int i = 0;
        for (ObjRef o :
                JSceneManager.getActiveScene().getObjects()) {
            if (o == null) continue;
            JBoxCollider pawnCollider;

            try {
                pawnCollider = ((JPawn) o.objRef).getCollider();
            }
            catch (Exception e)
            {
                i++;
                continue;
            }
            if(pawnCollider == null || pawnCollider == this || !pawnCollider.trigger || parent == pawnCollider.parent)
            {
                i++;
                continue;
            }
            if(isCollidingWith(pawnCollider))
            {
                pawnCollider.onCollision(this);
                this.onCollision(pawnCollider);
                //System.out.println(getParent().JIdentity.getName() + " Colliding with " + pawnRef.getParent().JIdentity.getName());
                i++;
            }
        }
    }

    public boolean isCollidingWithHard()
    {
        for (ObjRef o :
                JSceneManager.getActiveScene().getObjects()) {
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

            if(isCollidingWith(pawnCollider))
            {
                return true;
            }
        }
        return false;
    }

    public void onCollision(JBoxCollider otherObj){
        calls++;
        if(calls == 1)
            return;
        parent.onCollisionEnter(otherObj.getParent());
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    /*public void onCollisionExit(JBoxCollider otherObj)
    {
        onCollisionExit.call(new Object[]{otherObj});
    }*/
}
