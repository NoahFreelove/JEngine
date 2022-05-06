package com.Tests;

import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transform Operations")

class TransformTest {
    public Transform transform;

    @BeforeEach
    void setUp()
    {
        transform = new Transform(new Vector3(5,5,5),new Vector3(2,2,2),new Vector3(4,4,4));
    }

    @Test
    @DisplayName("Set Transform")
    void setTransform()
    {
        transform.setTransform(new Vector3(1,1,1),new Vector3(2,2,2),new Vector3(3,3,3));
        assertEquals(new Transform(new Vector3(1,1,1),new Vector3(2,2,2),new Vector3(3,3,3)).toString(), transform.toString());
    }

    @Test
    @DisplayName("Get Transform")
    void getTransform()
    {
        assertEquals(new Transform(new Vector3(5,5,5),new Vector3(2,2,2),new Vector3(4,4,4)).toString(), transform.toString());
    }

    @Test
    @DisplayName("Set Position")
    void setPosition()
    {
        transform.setPosition(new Vector3(3,3,3));
        assertEquals(new Transform(new Vector3(3,3,3),new Vector3(2,2,2),new Vector3(4,4,4)).toString(), transform.toString());
    }

    @Test
    @DisplayName("Set Rotation")
    void setRotation()
    {
        transform.setRotation(new Vector3(3,3,3));
        assertEquals(new Transform(new Vector3(5,5,5),new Vector3(3,3,3),new Vector3(4,4,4)).toString(), transform.toString());
    }

    @Test
    @DisplayName("Set Scale")
    void setScale()
    {
        transform.setScale(new Vector3(3,3,3));
        assertEquals(new Transform(new Vector3(5,5,5),new Vector3(2,2,2),new Vector3(3,3,3)).toString(), transform.toString());
    }

    @Test
    @DisplayName("Get Position")
    void getPosition()
    {
        assertEquals(new Vector3(5,5,5).toString(), transform.getPosition().toString());
    }

    @Test
    @DisplayName("Get Rotation")
    void getRotation()
    {
        assertEquals(new Vector3(2,2,2).toString(), transform.getRotation().toString());
    }

    @Test
    @DisplayName("Get Scale")
    void getScale()
    {
        assertEquals(new Vector3(4,4,4).toString(), transform.getScale().toString());
    }

}