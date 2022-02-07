package com.company;

/** JEngine.Object (c) Noah Freelove
 * A object is a simple class that has a transform
 * A object cannot be controlled directly, but it's position can be updated
 * **/


public class Object {
    public Transform transform;

    public Object(Transform transform) {
        this.transform = transform;
    }

}
