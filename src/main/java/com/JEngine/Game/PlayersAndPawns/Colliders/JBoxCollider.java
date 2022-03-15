package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;
import com.JEngine.Utility.Misc.GenericMethodCall;


public class JBoxCollider extends JObject {
    private JObject parent;
    public int sizeX;
    public int sizeY;
    GenericMethodCall onCollision;
    //GenericMethodCall onCollisionExit;

    private int calls = 0;


    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JObject parent) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.onCollision = args -> {};
        this.parent = parent;
    }

    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JObject parent, GenericMethodCall onCollision) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.onCollision = onCollision;
        this.parent = parent;
    }
    public void setCollisionEvent(GenericMethodCall c)
    {
        onCollision = c;
    }
    /*public void setOnCollisionExitEvent(GenericMethodCall c)
    {
        onCollisionExit = c;
    }*/

    public int getTimesCollided(){return calls;}

    public boolean isCollidingWith(JBoxCollider otherObject)
    {
        if(otherObject == null)
        {
            return false;
        }

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

    public JObject getParent() {
        return parent;
    }

    public void checkAllCollision()
    {
        int i = 0;
        for (ObjRef o :
                JSceneManager.getActiveScene().sceneObjects) {
            if (o == null) continue;
            JBoxCollider pawnRef;

            try {
                pawnRef = ((JPawn) o.objRef).getCollider();
            }
                catch (Exception e)
                {
                    i++;
                    continue;
                }
                if(pawnRef == null)
                {
                    i++;
                    continue;
                }

                if(pawnRef == this)
                {
                    i++;
                    continue;
                }
                    if(isCollidingWith(pawnRef))
                    {
                        pawnRef.onCollision(this);
                        i++;
                    }
                }
        }


    public void onCollision(JBoxCollider otherObj){
        calls++;
        if(calls == 1)
            return;
        onCollision.call(new Object[]{otherObj});
    }

    /*public void onCollisionExit(JBoxCollider otherObj)
    {
        onCollisionExit.call(new Object[]{otherObj});
    }*/
}
