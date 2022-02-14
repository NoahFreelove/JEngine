package com.JEngine.Utility;

import com.JEngine.PrimitiveTypes.Position.Vector3;

public class JMath {

    public static float clamp(float min, float max, float value){
        if(value<=min)
            return min;
        return Math.min(value, max);
    }
    public static Vector3 interpolateClamped(Vector3 vA, Vector3 vB, float t)
    {
        t = JMath.clamp(0,1,t);
        return vB.multiply(1.f-t);
    }

}
