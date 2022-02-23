package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;
import javafx.util.Pair;

import java.awt.*;

public class JBoxCollider extends JObject {
    public boolean isTrigger;
    public int sizeX;
    public int sizeY;
    public Rectangle rect;
    CollideEvent onCollisionEnter;
    // JIdentity identifies objects, boolean is true if on collision enter event was activated
    public Pair[] collisionStatus;

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
        this.collisionStatus = new Pair[JSceneManager.getScene().getMaxObjects()];
        init();
    }

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY, CollideEvent onCollisionEnter) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
        this.onCollisionEnter = onCollisionEnter;
        init();
    }
    private void init()
    {
        int i = 0;
        for (ObjRef o :
                JSceneManager.getScene().sceneObjects) {
            if(o!=null)
            {
                collisionStatus[i] = new Pair(o.objRef.JIdentity, false);
            }
            i++;
        }
    }

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
            if (o == null) return;

            try
            {
                Sprite spriteRef = (Sprite) o.objRef;

                System.out.println(collisionStatus[i].getKey().toString());

                if(o.objRef.JIdentity.getName().equals(""))
                {
                    System.out.println("work");
                }

                if(isCollidingWith(spriteRef.collider))
                {
                    if(!((Boolean) collisionStatus[i].getValue()))
                    {
                        collisionStatus[i] = new Pair(collisionStatus[i].getKey(), true);
                        spriteRef.collider.onCollisionEnter(this);
                    }
                }
            }
            catch (Exception e)
            {
                //ignore
            }
            i++;

        }
    }

    public void onCollisionEnter(JBoxCollider otherObj){
        System.out.println("Colliding with: " + otherObj.JIdentity.getName());
    }

    public void onCollisionExit()
    {}
}
