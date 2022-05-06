package com.JEngine.Core.Position;

import java.io.Serializable;

/** Vector3 (c) Noah Freelove
 * A Vector 3 is a simple structure that has an x y and z value.
 *
 * Usage:
 * Vector3's are the foundation for all movement, rotation, and scale.
 * Vector3 values can also be handy for holding simple points or structures with 3 float values
 * **/

public class Vector3 implements Serializable {
    public float x;
    public float y;
    public float z;

    /**
     * Create a new Vector 3
     * @param _x the x Value
     * @param _y the y Value
     * @param _z the z Value
     */
    public Vector3(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }
    public Vector3(Vector2 xy, float z) {
        this.x = xy.x;
        this.y = xy.y;
        this.z = z;
    }

    // region Vector3 Operations
    // Vector 3 Operations
    public Vector3 multiply(Vector3 a){
        if(a == null)
        {
            return new Vector3(0,0,0);
        }
        return new Vector3(x*a.x, y*a.y, z*a.z);
    }
    public Vector3 multiply(float a)
    {
        return new Vector3(x*a, y*a, z*a);
    }

    public Vector3 divide(Vector3 a)
    {
        if(a == null)
        {
            return new Vector3(0,0,0);
        }
        return new Vector3(x/a.x, y/a.y, z/a.z);
    }
    public Vector3 divide(float a)
    {
        return new Vector3(x/a, y/a, z/a);
    }

    public Vector3 add(Vector3 a)
    {
        if(a == null)
        {
            return new Vector3(0,0,0);
        }
        return new Vector3(x+a.x, y+a.y, z+a.z);
    }
    public Vector3 add(float a)
    {
        return new Vector3(x+a, y+a, z+a);
    }

    public Vector3 subtract(Vector3 a)
    {
        if(a == null)
        {
            return new Vector3(0,0,0);
        }
        return new Vector3(x-a.x, y-a.y, z-a.z);
    }
    public Vector3 subtract(float a)
    {
        return new Vector3(x-a, y-a, z-a);
    }
    //endregion

    public static float getMaxValue(Vector3 input)
    {
        return Math.max(Math.max(Math.abs(input.x), Math.abs(input.y)), Math.abs(input.z));
    }

    // Make toString() readable
    public String toString() {return String.format("{x:%f y:%f z:%f}", x,y,z);}
}