package com.company;

public class Transform {
    Vector3 position;
    Vector3 rotation;
    Vector3 scale;

    public void setTransform(Transform newTransform) {
        position = newTransform.position;
        rotation = newTransform.rotation;
        scale = newTransform.scale;
    }

    public void setPosition(Vector3 newPosition){position = newPosition;}
    public void setRotation(Vector3 newRotation){rotation = newRotation;}
    public void setScale(Vector3 newScale){scale = newScale;}
}
