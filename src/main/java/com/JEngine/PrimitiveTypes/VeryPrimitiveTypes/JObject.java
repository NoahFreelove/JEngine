package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;
import com.JEngine.PrimitiveTypes.Position.Transform;

/** com.JEngine.JObject (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for (nearly) everything that exists.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.transform.setTransform
 * **/

public class JObject extends Thing {
    public Transform transform;
    public JIdentity JIdentity;

    public JObject(Transform transform, JIdentity JIdentity) {
        super(true);
        this.transform = transform;
        this.JIdentity = JIdentity;
    }

    // Called upon object's creation
    public void Start(){}

    // Called once every frame
    public void Update(){}
}
