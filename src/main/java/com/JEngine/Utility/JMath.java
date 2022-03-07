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
        vA = vA.multiply(1-t);
        Vector3 vC = vB.multiply(t);
        return vA.add(vC);
    }

    public static Vector3 interpolate(Vector3 vA, Vector3 vB, float t)
    {
        vA = vA.multiply(1-t);
        Vector3 vC = vB.multiply(t);
        return vA.add(vC);
    }

    public static int max (int... x)
    {
        int m = Integer.MIN_VALUE;
        for (int n : x) {
            if(n>m)
                m = n;
        }
        return m;
    }

    public static float max (float... x)
    {
        float m = Float.MIN_VALUE;
        for (float n : x) {
            if(n>m)
                m = n;
        }
        return m;
    }

    public static float min (int... x)
    {
        int m = Integer.MAX_VALUE;
        for (int n : x) {
            if(n<m)
                m = n;
        }
        return m;
    }

    public static float min (float... x)
    {
        float m = Float.MAX_VALUE;
        for (float n : x) {
            if(n<m)
                m = n;
        }
        return m;
    }

}
