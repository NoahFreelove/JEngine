package com.company;

public class Transform {
    Vector3 position;
    Vector3 rotation;
    Vector3 scale;

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
