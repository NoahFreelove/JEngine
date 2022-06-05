package com.JEngine.Components.Colliders;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GenericMethod;

public class Collider_Comp extends Component {
    private Vector3 offsetFromParent = new Vector3(0, 0, 0);
    private Vector3 position;
    private String ignoreTag = "";

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
    public Collider_Comp(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent, String ignoreTag) {
        super("Collider");
        this.height = height;
        this.width = width;
        this.isTrigger = isTrigger;
        this.offsetFromParent = initialOffset;
        this.setParent(parent);
        this.position = getParent().getPosition().add(initialOffset);
        this.ignoreTag = ignoreTag;
    }

    @Override
    public void Update(){
        this.position = getParent().getPosition().add(offsetFromParent);
        isCollidingWithAny();
    }

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

    public boolean isCollidingWithHard()
    {
        // go through every scene object check for hard collider
        for (GameObject o : SceneManager.getActiveScene().getObjects()) {
            if (o == null || o == getParent()) continue;
            for (Collider_Comp c : o.getColliders()) {
                if (c != null) {
                    if(!o.getIdentity().compareTag(ignoreTag))
                    {
                        if (!c.isTrigger && c.isCollidingWith(this)) {
                            if(c.getActive())
                            {
                                onHit(c);
                                c.onHit(this);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isCollidingWithAny(){
        for (GameObject o : SceneManager.getActiveScene().getObjects()) {
            if (o == null || o == getParent()) continue;
            for (Collider_Comp c : o.getColliders()) {
                if (c != null) {
                    if(!o.getIdentity().compareTag(ignoreTag))
                    {
                        if (c.isCollidingWith(this)) {
                            if(c.getActive())
                            {
                                onHit(c);
                                c.onHit(this);
                                return true;
                            }
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
        Collider_Comp tmpCollider = new Collider_Comp(getPosition(), getWidth(), getHeight(), false, getParent());
        tmpCollider.setPosition(new Vector3(getPosition().x + totalXMovement, getPosition().y + totalYMovement, getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    public void onHit(Collider_Comp other){
        if(onCollision !=null)
        {
            onCollision.call(new Object[]{other});
        }
    }

    public String getIgnoreTag() {
        return ignoreTag;
    }

    public void setIgnoreTag(String ignoreTag) {
        this.ignoreTag = ignoreTag;
    }
}
