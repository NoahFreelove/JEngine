package com.Tests;

import com.JEngine.Core.Position.Vector3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Vector3 Math")
class Vector3Test {
    public Vector3 vector3;

    @BeforeEach
    void setUp()
    {
        vector3 = new Vector3(5,5,5);
    }

    @Test
    @DisplayName("Multiply By Float")
    void multiplyByFloat()
    {
        assertEquals(new Vector3(10,10,10).toString(), vector3.multiply(2).toString());
    }

    @Test
    @DisplayName("Multiply By Vector")
    void multiplyByVector()
    {
        assertEquals(new Vector3(10,15,10).toString(), vector3.multiply(new Vector3(2,3,2)).toString());
    }

    @Test
    @DisplayName("Divide By Float")
    void divideByFloat()
    {
        assertEquals(new Vector3(2.5f,2.5f,2.5f).toString(), vector3.divide(2).toString());
    }

    @Test
    @DisplayName("Divide By Vector3")
    void divideByVector()
    {
        assertEquals(new Vector3(2.5f,10f,2.5f).toString(), vector3.divide(new Vector3(2,0.5f,2)).toString());
    }

    @Test
    @DisplayName("Add Float")
    void addFloat()
    {
        assertEquals(new Vector3(7f,7f,7f).toString(), vector3.add(2).toString());
    }

    @Test
    @DisplayName("Add Vector")
    void addVector()
    {
        assertEquals(new Vector3(7f,5.5f,10f).toString(), vector3.add(new Vector3(2,0.5f,5)).toString());
    }

    @Test
    @DisplayName("Subtract Float")
    void subtractFloat()
    {
        assertEquals(new Vector3(3f,3f,3f).toString(), vector3.subtract(2).toString());
    }

    @Test
    @DisplayName("Subtract Vector")
    void subtractVector()
    {
        assertEquals(new Vector3(4,4.5f,2).toString(), vector3.subtract(new Vector3(1,0.5f,3)).toString());
    }
}