package com.JEngine.PrimitiveTypes;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** JEngine.Object (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for everything that can be seen.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.transform.setTransform
 * **/

public class Object {
    public Transform transform;
    public Object(Transform transform) {
        this.transform = transform;
    }
}
