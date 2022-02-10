package com.JEngine.Game.PlayersAndPawns.Colliders;

import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;

public class JBoxCollider extends JUIObject {
    public boolean isTrigger;
    public int sizeX;
    public int sizeY;

    public JBoxCollider(Transform transform, JIdentity JIdentity, boolean isTrigger, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.isTrigger = isTrigger;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public boolean willCollide(Direction d, JBoxCollider otherObject)
    {

        return false;
    }
}
