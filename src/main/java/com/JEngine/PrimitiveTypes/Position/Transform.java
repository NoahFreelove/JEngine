package com.JEngine.PrimitiveTypes.Position;

/** Transform (c) Noah Freelove
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

    public Transform(Transform t)
    {
        this.position = t.position;
        this.rotation = t.rotation;
        this.scale = t.scale;
    }

    public void setTransform(Vector3 newPosition, Vector3 newRotation, Vector3 newScale) {
        position = newPosition;
        rotation = newRotation;
        scale = newScale;
    }

    // setters
    public void setPosition(Vector3 newPosition){position = newPosition;}
    public void setPosition(float x, float y, float z){position = new Vector3(x, y, z);}
    public void setRotation(Vector3 newRotation){rotation = newRotation;}
    public void setScale(Vector3 newScale){scale = newScale;}
    public void setScale(float newScale){scale = new Vector3(newScale, newScale, newScale);}
    public void setScale(float x, float y, float z){scale = new Vector3(x, y, z);}
    // getters
    public Vector3 getPosition() { return position;}
    public Vector3 getRotation() { return rotation;}
    public Vector3 getScale() { return scale;}

    // a default rotation and scale are just values that are assumed to be preferred, so it's easier to make a transform
    public static Vector3 defaultRotation() {return new Vector3(0,0,0);}
    public static Vector3 defaultScale() {return new Vector3(1,1,1);}

    // Create a transform with just a vector3
    public static Transform simpleTransform(Vector3 pos)
    {
        return new Transform(pos,defaultRotation(),defaultScale());
    }

    // Create a transform with just x,y,z values
    public static Transform simpleTransform(float x,float y,float z)
    {
        return new Transform(new Vector3(x,y,z),defaultRotation(),defaultScale());
    }

    // Create a transform with just x,y values
    public static Transform exSimpleTransform(float x, float y)
    {
        return new Transform(new Vector3(x,y,0),defaultRotation(),defaultScale());
    }
    // Create a transform with just a vector2
    public static Transform exSimpleTransform(Vector2 pos)
    {
        return new Transform(new Vector3(pos.x,pos.y,0),defaultRotation(),defaultScale());
    }

    // Make toString method output something readable
    public String toString() {
        return String.format("Position{x: %f y: %f z: %f} Rotation{x: %f y: %f z: %f} Scale{x: %f y: %f z: %f}", position.x,position.y,position.z,rotation.x,rotation.y,rotation.z,scale.x,scale.y,scale.z);
    }


}
