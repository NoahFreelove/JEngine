package com.company;

public class Main {
    static Object obj = new Object(new Transform(new Vector3(1,1,1), new Vector3(2,2,2), new Vector3(3,3,3)));

    public static void main(String[] args) {
        obj.transform.setPosition(new Vector3(2,1,1));

        System.out.println(obj.transform.position.x);
    }
}
