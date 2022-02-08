package com.JEngine;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import javax.sound.sampled.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Object obj = new Object(new Transform(position, rotation, scale));

    public static void main(String[] args) {
        try {
            File wavFile = new File("Sounds\\\\piano2.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wavFile));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
        Scanner s = new Scanner(System.in);
        s.next();

        /*obj.transform.setPosition(new Vector3(2,1,1));

        System.out.println(obj.transform.position.x);*/
    }
}
