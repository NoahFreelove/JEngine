package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

import java.awt.*;

public class JBoxCollider extends JObject {
    public boolean isTrigger;
    public int sizeX;
    public int sizeY;
    public Rectangle rect;
    CollideEvent onCollisionEnter;

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
    }

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY, CollideEvent onCollisionEnter) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rect = new Rectangle((int)transform.position.x, (int)transform.position.y, sizeX, sizeY);
        this.onCollisionEnter = onCollisionEnter;
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

        if((x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2))
        {
            onCollisionEnter();
            return true;
        }
        return false;
    }

    public boolean inBetween(float a, float b, float x)
    {
        return (a<x && x<b);
    }

    @Override
    public void Update()
    {
        rect.setRect(transform.position.x, transform.position.y, sizeX, sizeY);
    }

    public void onCollisionEnter(){}

    public void onCollisionExit()
    {}
}
