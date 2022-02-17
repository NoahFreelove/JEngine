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
        // output = (1-t) * Input + t * Input2
        vA = vA.multiply(1-t);
        Vector3 vC = vB.multiply(t);
        return vA.add(vC);
    }

}
