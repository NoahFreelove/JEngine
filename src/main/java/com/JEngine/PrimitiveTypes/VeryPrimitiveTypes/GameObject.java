package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** JObject (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for (nearly) everything that exists.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.getTransform().setTransform
 * **/

public class GameObject extends Thing {
    private Transform transform;
    private final Identity identity;
    private boolean queuedForDeletion;

    public GameObject(Transform transform, Identity identity) {
        super(true);
        this.transform = transform;
        this.identity = identity;
    }

    // Called upon object's creation
    public void Start(){}

    // Called once every frame
    public void Update(){}

    // Getters
    public Identity getIdentity() {
        return identity;
    }
    public Transform getTransform() {
        return transform;
    }
    public boolean isQueuedForDeletion() {
        return queuedForDeletion;
    }

    // Setters
    public void setQueuedForDeletion(boolean queuedForDeletion) {
        this.queuedForDeletion = queuedForDeletion;
    }
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

}
