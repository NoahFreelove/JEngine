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
}
