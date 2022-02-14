package com.JEngine.Utility;

import com.JEngine.PrimitiveTypes.Position.Vector3;

public class JMath {

    public static float clamp(float min, float max, float value){
        if(value<=min)
            return min;
        return Math.min(value, max);
    }
    public static Vector3 lerp(Vector3 vA, Vector3 vB, float t)
    {
        vA.x = vB.x*(1.f-t);
        vA.y = vB.y*(1.f-t);
        vA.z = vB.z*(1.f-t);

        return  vA;
    }

}
