package com.JEngine.Utility;

import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

import java.util.concurrent.ThreadLocalRandom;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogError;
/** JMath (c) Noah Freelove
 * A collection of useful math functions.
 */
public class JMath {
    /**
     * Clamp value between min and max. if x<min, return min; if x>max, return max; else return x
     * @param min minimum value
     * @param max maximum value
     * @param value value to clamp
     * @return clamped value
     */
    public static float clamp(float min, float max, float value){
        if(value<=min)
            return min;
        return Math.min(value, max);
    }
    /**
     * Clamp value between min and max. if x<min, return min; if x>max, return max; else return x
     * @param min minimum value
     * @param max maximum value
     * @param value value to clamp
     * @return clamped value
     */
    public static int clamp(int min, int max, int value){
        if(value<=min)
            return min;
        return Math.min(value, max);
    }

    /**
     * Interpolate position between two vectors clamped (0<x<1)
     * @param vA vector A
     * @param vB vector B
     * @param t interpolation value
     * @return interpolated position
     */
    public static Vector3 interpolateClamped(Vector3 vA, Vector3 vB, float t)
    {
        t = JMath.clamp(0,1,t);
        vA = vA.multiply(1-t);
        Vector3 vC = vB.multiply(t);
        return vA.add(vC);
    }

    /**
     * Interpolate position between two vectors
     * @param vA vector A
     * @param vB vector B
     * @param t interpolation value
     * @return interpolated position
     */
    public static Vector3 interpolate(Vector3 vA, Vector3 vB, float t)
    {
        vA = vA.multiply(1-t);
        Vector3 vC = vB.multiply(t);
        return vA.add(vC);
    }

    /**
     * Returns the max of the given values.
     * @param x values to compare
     * @return max value of list
     */
    public static int max (int... x)
    {
        int m = Integer.MIN_VALUE;
        for (int n : x) {
            if(n>m)
                m = n;
        }
        return m;
    }

    /**
     * Returns the max of the given values.
     * @param x values to compare
     * @return max value of list
     */
    public static float max (float... x)
    {
        float m = Float.MIN_VALUE;
        for (float n : x) {
            if(n>m)
                m = n;
        }
        return m;
    }

    /**
     * Returns the min of the given values.
     * @param x values to compare
     * @return min value of list
     */
    public static int min (int... x)
    {
        int m = Integer.MAX_VALUE;
        for (int n : x) {
            if(n<m)
                m = n;
        }
        return m;
    }

    /**
     * Returns the min of the given values.
     * @param x values to compare
     * @return min value of list
     */
    public static float min (float... x)
    {
        float m = Float.MAX_VALUE;
        for (float n : x) {
            if(n<m)
                m = n;
        }
        return m;
    }

    /**
     * Generate a *random* value in range inclusively (min<=x<=max)
     * @param lowerBound lower bound
     * @param upperBound upper bound
     * @return random value
     */
    public static int randRangeInclusive(int lowerBound, int upperBound)
    {
        if(upperBound<lowerBound)
        {
            LogError(String.format("randRangeInclusive error: Upper-bound (%d) is less than the Lower-bound (%d)", upperBound,lowerBound));
            return 0;
        }
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
    }

    /**
     * Generate a *random* float value in range inclusively (min<=x<=max)
     * @param lowerBound lower bound
     * @param upperBound upper bound
     * @return random value
     */
    public static float randRangeInclusiveF(int lowerBound, int upperBound)
    {
        if(upperBound<lowerBound)
        {
            LogError(String.format("randRangeInclusive error: Upper-bound (%d) is less than the Lower-bound (%d)", upperBound,lowerBound));
            return 0;
        }
        return ThreadLocalRandom.current().nextFloat(lowerBound, upperBound + 1);
    }

    /**
     * Get the next value in a repeating range.
     * @param v value
     * @param min minimum value
     * @param max maximum value
     * @param step step value
     * @return the next value in the step sequence.
     */
    public static float repeat(float v, float min, float max, float step)
    {
        if(v<min)
        {
            return min;
        }

        else if (v >=max)
        {
            return min;
        }
        else if(v+step>max)
            return max;

        return v+step;
    }

}
