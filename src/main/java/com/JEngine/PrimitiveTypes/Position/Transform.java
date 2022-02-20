package com.JEngine.PrimitiveTypes.Position;

/** JEngine.Transform (c) Noah Freelove
 * Brief Explanation:
 * A transform is made of 3 Vector3 variables that hold position, rotation, and scale.
 *
 * Usage:
 * Transform values can be updated individually or collectively.
 * All objects have a transform value as they must hold an in-game position, even if not visible.
 * **/

public class Transform {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform(Vector3 newPosition, Vector3 newRotation, Vector3 newScale) {
        position = newPosition;
        rotation = newRotation;
        scale = newScale;
    }

    public void setTransform(Vector3 newPosition, Vector3 newRotation, Vector3 newScale) {
        position = newPosition;
        rotation = newRotation;
        scale = newScale;
    }

    public void setPosition(Vector3 newPosition){position = newPosition;}
    public void setRotation(Vector3 newRotation){rotation = newRotation;}
    public void setScale(Vector3 newScale){scale = newScale;}

    public Vector3 getPosition() { return position;}
    public Vector3 getRotation() { return rotation;}
    public Vector3 getScale() { return scale;}
    public static Vector3 defaultRotation() {return new Vector3(0,0,0);}
    public static Vector3 defaultScale() {return new Vector3(1,1,1);}

    public static Transform simpleTransform(Vector3 pos)
    {
        return new Transform(pos,defaultRotation(),defaultScale());
    }
    public static Transform exSimpleTransform(float x, float y)
    {
        return new Transform(new Vector3(x,y,0),defaultRotation(),defaultScale());
    }

    public String toString() {
        return String.format("Position{x: %f y: %f z: %f} Rotation{x: %f y: %f z: %f} Scale{x: %f y: %f z: %f}", position.x,position.y,position.z,rotation.x,rotation.y,rotation.z,scale.x,scale.y,scale.z);
    }


}
