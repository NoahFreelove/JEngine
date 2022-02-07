package com.company;

import com.company.PrimitiveTypes.Position.Transform;
import com.company.PrimitiveTypes.Position.Vector3;
import com.company.PrimitiveTypes.Object;

public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Object obj = new Object(new Transform(position, rotation, scale));

    public static void main(String[] args) {
        obj.transform.setPosition(new Vector3(2,1,1));

        System.out.println(obj.transform.position.x);
    }
}
