package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;


public class ObjRef {
    public Object objRef;
    public Vector3 position;

    public ObjRef(Object objRef, Vector3 position) {
        this.objRef = objRef;
        this.position = position;
    }
}
