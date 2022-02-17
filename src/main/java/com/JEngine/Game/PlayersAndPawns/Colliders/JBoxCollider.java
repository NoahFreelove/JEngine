package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

import java.awt.geom.Rectangle2D;

public class JBoxCollider extends JObject {
    public boolean isTrigger;
    public int sizeX;
    public int sizeY;
    Rectangle2D rect;
    CollideEvent onCollisionEnter;
    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.rect = new Rectangle2D.Double(transform.position.x, transform.position.y, sizeX, sizeY);
    }
    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, CollideEvent onCollisionEnter) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        rect.setRect(transform.position.x, transform.position.y, sizeX, sizeY);
        this.rect = new Rectangle2D.Double(transform.position.x, transform.position.y, sizeX, sizeY);
        this.onCollisionEnter = onCollisionEnter;
    }

    public boolean collides(JBoxCollider otherObject)
    {
        if(rect.intersects(otherObject.transform.position.x, otherObject.transform.position.y, otherObject.sizeX, otherObject.sizeY))
        {
            System.out.println("Intersecting");
        }
        return false;
    }
}
