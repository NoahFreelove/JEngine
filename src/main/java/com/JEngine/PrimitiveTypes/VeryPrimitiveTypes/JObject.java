package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** com.JEngine.JObject (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for (nearly) everything that exists.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.getTransform().setTransform
 * **/

public class JObject extends Thing {
    private Transform transform;
    public JIdentity JIdentity;
    private boolean queuedForDeletion;

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public JObject(Transform transform, JIdentity jIdentity) {
        super(true);
        this.transform = transform;
        this.JIdentity = jIdentity;
    }

    // Called upon object's creation
    public void Start(){}

    // Called once every frame
    public void Update(){}

    public boolean isQueuedForDeletion() {
        return queuedForDeletion;
    }

    public void setQueuedForDeletion(boolean queuedForDeletion) {
        this.queuedForDeletion = queuedForDeletion;
    }
}
