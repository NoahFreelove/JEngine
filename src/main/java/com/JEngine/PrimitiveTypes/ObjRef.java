package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;

/** ObjRef (c) Noah Freelove
 * Brief Explanation:
 * A ObjRef used exclusively for JScene to provide an easy reference to an object with its position
 *
 * Usage:
 * Really just provides an easier way to get an object's position. Object.Transform.position works as well.
 * **/

public class ObjRef {
    public JObject objRef;

    public ObjRef(JObject objRef) {
        super();
        this.objRef = objRef;
    }

    /**
     * Get position of object
     * @return Vector3 position
     */
    public Vector3 getPosition() {return objRef.getTransform().position;}
}
