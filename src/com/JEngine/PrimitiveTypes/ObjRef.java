package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

/** JEngine.ObjRef (c) Noah Freelove
 * Brief Explanation:
 * A ObjRef used exclusively for JScene and JViewport to provide an easy reference to an object with its position
 *
 * Usage:
 * Really just provides an easier way to get an object's position. Object.Transform.position works as well.
 * **/

public class ObjRef extends Thing {
    public Object objRef;
    public Vector3 position;

    public ObjRef(Object objRef, Vector3 position) {
        super(true);
        this.objRef = objRef;
        this.position = position;
    }
}