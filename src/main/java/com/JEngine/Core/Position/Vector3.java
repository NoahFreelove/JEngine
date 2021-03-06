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
     *
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
    public Vector3(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Vector3(Vector2 vec)
    {
        this.x = vec.x;
        this.y = vec.y;
        this.z = 0;
    }
    public Vector3(Vector3 vec)
    {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    // region Vector3 Operations
    // Vector 3 Operations
    public Vector3 multiply(Vector3 a) {
        if (a == null) {
            return new Vector3(0, 0, 0);
        }
        return new Vector3(x * a.x, y * a.y, z * a.z);
    }

    public Vector3 multiply(float a) {
        return new Vector3(x * a, y * a, z * a);
    }

    public Vector3 divide(Vector3 a) {
        if (a == null) {
            return new Vector3(0, 0, 0);
        }
        return new Vector3(x / a.x, y / a.y, z / a.z);
    }

    public Vector3 divide(float a) {
        return new Vector3(x / a, y / a, z / a);
    }

    public Vector3 add(Vector3 a) {
        if (a == null) {
            return new Vector3(0, 0, 0);
        }
        return new Vector3(x + a.x, y + a.y, z + a.z);
    }

    public Vector3 add(Vector2 v)
    {
        return new Vector3(x+v.x, y+v.y, z);
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

    public boolean equals(Vector3 a)
    {
        if(a == null)
        {
            return false;
        }
        return x == a.x && y == a.y && z == a.z;
    }

    // Make toString() readable
    public String toString() {return String.format("{x:%f y:%f z:%f}", x,y,z);}

    public static Vector3 emptyVector(){
        return new Vector3(0,0,0);
    }
    public static Vector3 oneVector(){
        return new Vector3(1,1,1);
    }
}