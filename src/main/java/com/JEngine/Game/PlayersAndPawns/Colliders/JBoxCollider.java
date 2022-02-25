package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

import java.awt.*;

class CollisionPair
{
    public JIdentity i;
    public Boolean v;

    public CollisionPair(JIdentity i, Boolean v) {
        this.i = i;
        this.v = v;
    }
}

public class JBoxCollider extends JObject {
    public boolean isTrigger;
    public int sizeX;
    public int sizeY;
    public Rectangle rect;
    CollideEvent onCollisionEnter;
    CollideEvent onCollisionExit;

    private int calls = 0;

    // JIdentity identifies objects, boolean is true if on collision enter event was activated
    public CollisionPair[] collisionStatus;

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
        this.collisionStatus = new CollisionPair[JSceneManager.getScene().getMaxObjects()];
    }

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY, CollideEvent onCollisionEnter) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
        this.onCollisionEnter = onCollisionEnter;
    }
    public void setOnCollisionEnterEvent(CollideEvent c)
    {
        onCollisionEnter = c;
    }
    public void setOnCollisionExitEvent(CollideEvent c)
    {
        onCollisionExit = c;
    }

    public void initializeCollider()
    {
        int i = 0;
        for (ObjRef o :
                JSceneManager.getScene().sceneObjects) {
            if(o!=null)
            {
                collisionStatus[i] = new CollisionPair(o.objRef.JIdentity, false);
            }
            i++;
        }
    }

    public int getTimesCollided(){return calls;}

    public boolean isCollidingWith(JBoxCollider otherObject)
    {
        int x1 = (int)otherObject.transform.position.x;
        int y1 = (int)otherObject.transform.position.y;
        int x2 = (int)otherObject.transform.position.x + otherObject.sizeX;
        int y2 = (int)otherObject.transform.position.y+ otherObject.sizeY;
        int x3 = (int)transform.position.x;
        int y3 = (int)transform.position.y;
        int x4 = (int)transform.position.x + sizeX;
        int y4 = (int)transform.position.y+ sizeY;

        return (x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2);
    }


    public void checkAllCollision()
    {
        int i = 0;
        for (ObjRef o :
                JSceneManager.getScene().sceneObjects) {
            if (o == null || collisionStatus[i] == null) return;

            try
            {
                Sprite spriteRef = (Sprite) o.objRef;

                if(o.objRef.JIdentity.getName().equals(collisionStatus[i].i.getName()))
                {
                    if(isCollidingWith(spriteRef.collider))
                    {
                        if(!(collisionStatus[i].v))
                        {
                            collisionStatus[i] = new CollisionPair(collisionStatus[i].i, true);
                            spriteRef.collider.onCollisionEnter(this);
                        }
                    }
                    else if(collisionStatus[i].v)
                    {
                        if(!isCollidingWith(spriteRef.collider))
                        {
                            collisionStatus[i] = new CollisionPair(collisionStatus[i].i, false);
                            spriteRef.collider.onCollisionExit(this);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                //System.out.println(e);
                //ignore
            }
            i++;

        }
    }

    public void onCollisionEnter(JBoxCollider otherObj){
        calls++;
        if(calls == 1)
            return;
        onCollisionEnter.event(otherObj);
    }

    public void onCollisionExit(JBoxCollider otherObj)
    {
        onCollisionExit.event(otherObj);
    }
}
