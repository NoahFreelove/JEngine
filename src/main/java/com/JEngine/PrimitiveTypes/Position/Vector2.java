package com.JEngine.PrimitiveTypes.Position;

/** Vector2 (c) Noah Freelove
 * Brief Explanation:
 * Create
 * **/

public class Vector2 {
    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString()
    {
        return String.format("(x:%f y:%f)", x, y);
    }
}
