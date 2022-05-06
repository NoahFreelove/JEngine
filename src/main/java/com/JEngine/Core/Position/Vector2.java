package com.JEngine.Core.Position;

/** Vector2 (c) Noah Freelove
 * Brief Explanation:
 * Create
 * **/

public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2 multiply(double v)
    {
        return new Vector2((float)(x * v), (float)(y * v));
    }
    public Vector2 multiply(Vector2 v)
    {
        return new Vector2((x * v.x), (y * v.y));
    }
    public Vector2 add(Vector2 v)
    {
        return new Vector2(x + v.x, y + v.y);
    }
    public Vector2 subtract(Vector2 v)
    {
        return new Vector2(x - v.x, y - v.y);
    }

    public String toString()
    {
        return String.format("(x:%f y:%f)", x, y);
    }
}
