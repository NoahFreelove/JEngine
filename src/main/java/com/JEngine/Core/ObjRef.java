package com.JEngine.Core;

import com.JEngine.Core.Position.Vector3;

/** ObjRef (c) Noah Freelove
 * Brief Explanation:
 * A ObjRef used exclusively for JScene to provide an easy reference to an object with its position
 *
 * Usage:
 * Really just provides an easier way to get an object's position. Object.Transform.position works as well.
 * **/

public class ObjRef {
    public GameObject objRef;

    public ObjRef(GameObject objRef) {
        super();
        this.objRef = objRef;
    }

    /**
     * Get position of object
     * @return Vector3 position
     */
    public Vector3 getPosition() {return objRef.getTransform().position;}
}
