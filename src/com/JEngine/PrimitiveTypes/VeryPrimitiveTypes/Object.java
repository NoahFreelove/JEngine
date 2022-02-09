package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

/** JEngine.Object (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for (nearly) everything that exists.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.transform.setTransform
 * **/

public class Object extends Thing {
    public Transform transform;
    public Identity identity;

    public Object(Transform transform, Identity identity) {
        super(true);
        this.transform = transform;
        this.identity = identity;
    }
}
