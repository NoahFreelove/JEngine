package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

public class JBoxCollider extends JObject {
    private final JPawn parent;
    public int sizeX;
    public int sizeY;
    //GenericMethodCall onCollisionExit;

    private int calls = 0;


    public JBoxCollider(Transform transform, JIdentity JIdentity, int sizeX, int sizeY, JPawn parent) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.parent = parent;
    }


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

    public JPawn getParent() {
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
                        //System.out.println(getParent().JIdentity.getName() + " Colliding with " + pawnRef.getParent().JIdentity.getName());
                        i++;
                    }
                }
        }

    public void onCollision(JBoxCollider otherObj){
        calls++;
        if(calls == 1)
            return;
        parent.onCollisionEnter(otherObj.getParent());
    }

    /*public void onCollisionExit(JBoxCollider otherObj)
    {
        onCollisionExit.call(new Object[]{otherObj});
    }*/
}
