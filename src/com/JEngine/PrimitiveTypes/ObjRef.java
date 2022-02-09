package com.JEngine.PrimitiveTypes;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;

/** JEngine.ObjRef (c) Noah Freelove
 * Brief Explanation:
 * A ObjRef used exclusively for JScene and JViewport to provide an easy reference to an object with its position
 *
 * Usage:
 * Really just provides an easier way to get an object's position. Object.Transform.position works as well.
 * **/

public class ObjRef {
    public Object objRef;

    public ObjRef(Object objRef) {
        super();
        this.objRef = objRef;
    }
    public Vector3 getPosition() {return objRef.transform.position;}
}
