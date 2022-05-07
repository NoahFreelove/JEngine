package com.JEngine.Components;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GenericMethod;

public class Collider_Comp extends Component {
    private Vector3 offsetFromParent = new Vector3(0, 0, 0);
    private Vector3 position;
    public Collider_Comp player2;

    private boolean isTrigger;

    private float width;
    private float height;

    private GenericMethod onCollision;

    public Collider_Comp(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super("Collider");
        this.height = height;
        this.width = width;
        this.isTrigger = isTrigger;
        this.offsetFromParent = initialOffset;
        this.setParent(parent);
        this.position = getParent().getPosition().add(initialOffset);
    }

    @Override
    public void Update(){
        this.position = getParent().getPosition().add(offsetFromParent);
    }

    public boolean isCollidingWith(Collider_Comp otherObject)
    {
        return false;
    }

    public boolean isCollidingWithAny(boolean checkHardOnly)
    {
        // go through every scene object check for hard collider
        for (GameObject o :
                SceneManager.getActiveScene().getObjects()) {
            if (o == null || o == getParent()) continue;
            for (Component c : o.getComponents()
            ) {
                if (c != null) {
                    if (c instanceof Collider_Comp collider) {
                        if (collider.isCollidingWith(this)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * called when a collision occurs
     * @param otherObj  - the other object that collided with this object
     */
    public void onCollision(Collider_Comp otherObj){
        if(otherObj != null)
        {
            otherObj.getOnCollisionEvent().call(new Object[]{otherObj});
        }
    }

    public boolean isColliding(Collider_Comp other) {
        return false;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }


    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Vector3 getOffsetFromParent() {
        return offsetFromParent;
    }

    public void setOffsetFromParent(Vector3 offsetFromParent) {
        this.offsetFromParent = offsetFromParent;
    }
    public void setOnCollisionEvent(GenericMethod onCollision)
    {
        this.onCollision = onCollision;
    }
    public GenericMethod getOnCollisionEvent()
    {
        return onCollision;
    }

    public boolean canMove(float totalXMovement, float totalYMovement) {
        return true;
    }
}
