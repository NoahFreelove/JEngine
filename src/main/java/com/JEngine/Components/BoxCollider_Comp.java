package com.JEngine.Components;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;

public class BoxCollider_Comp extends Collider_Comp {

    public BoxCollider_Comp(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super(initialOffset, width, height, isTrigger, parent);
    }

    @Override
    public boolean isCollidingWith(Collider_Comp collider) {
        if(collider == null)
            return false;

        // logic to check if positions are in range (checks only x and y values, z values are irrelevant)
        float x1 = collider.getPosition().x;
        float y1 = collider.getPosition().y;
        float x2 = (collider.getPosition().x + collider.getWidth());
        float y2 = (collider.getPosition().y + collider.getHeight());
        float x3 = getPosition().x;
        float y3 = getPosition().y;
        float x4 = (getPosition().x + getWidth());
        float y4 = (getPosition().y+ getHeight());

        return (x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2);
    }

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    @Override
    public boolean canMove(float xDisplacement, float yDisplacement) {
        BoxCollider_Comp tmpCollider = new BoxCollider_Comp(getPosition(), getWidth(), getHeight(), isTrigger(), getParent());

        tmpCollider.setPosition(new Vector3(getPosition().x + xDisplacement, getPosition().y + yDisplacement, getPosition().z));
        return !tmpCollider.isCollidingWithAny(isTrigger());
    }
}
