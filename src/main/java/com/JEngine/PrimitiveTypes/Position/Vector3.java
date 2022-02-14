package com.JEngine.PrimitiveTypes.Position;

/** JEngine.Vector3 (c) Noah Freelove
 * Brief Explanation:
 * A Vector 3 is a simple structure that has an x y and z value.
 *
 * Usage:
 * Vector3's are the foundation for all movement, rotation, and scale values.
 * Vector3 values can also be handy for holding simple points or structures with 3 float values
 * **/

public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public Vector3 multiply(Vector3 a){
        return new Vector3(x*a.x, y*a.y, z*a.z);
    }
    public Vector3 multiply(float a)
    {
        return new Vector3(x*a, y*a, z*a);
    }
    public Vector3 divide(Vector3 a)
    {
        return new Vector3(x/a.x, y/a.y, z/a.z);
    }
    public Vector3 divide(float a)
    {
        return new Vector3(x/a, y/a, z/a);
    }
    public Vector3 add(Vector3 a)
    {
        return new Vector3(x+a.x, y+a.y, z+a.z);
    }
    public Vector3 add(float a)
    {
        return new Vector3(x+a, y+a, z+a);
    }
    public Vector3 subtract(Vector3 a)
    {
        return new Vector3(x-a.x, y-a.y, z-a.z);
    }
    public Vector3 subtract(float a)
    {
        return new Vector3(x-a, y-a, z-a);
    }

    public String toString() {return String.format("{x: %f y: %f z: %f}", x,y,z);}
}