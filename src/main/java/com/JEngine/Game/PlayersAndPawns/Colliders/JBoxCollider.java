package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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

    public boolean collides(JBoxCollider otherObject)
    {
        //System.out.println(transform.position.toString() + " : " + otherObject.transform.position.toString());
        boolean in1 =inBetween(transform.position.x, transform.position.x + sizeX, otherObject.transform.position.x);
        boolean in2 =inBetween(transform.position.x, transform.position.x + sizeX, otherObject.transform.position.x + otherObject.sizeX);
        boolean in3 =inBetween(transform.position.y, transform.position.y + sizeY, otherObject.transform.position.y);
        boolean in4 =inBetween(transform.position.y, transform.position.y + sizeY, otherObject.transform.position.y + otherObject.sizeY);

        System.out.println("in1: " + in1);
        System.out.println("in2: " + in2);
        System.out.println("in3: " + in3);
        System.out.println("in4: " + in4);

        if((in1 || in2) && (in3 || in4))
        {
            System.out.println("IN RANGE");
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
}
