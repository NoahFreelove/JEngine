package com.JEngine;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.Object;
import javax.sound.sampled.*;

import java.io.IOException;
import java.util.Objects;

public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Object obj = new Object(new Transform(position, rotation, scale));

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                Objects.requireNonNull(Main.class.getResourceAsStream("src/Sounds/piano2.wav")));
        clip.open(inputStream);
        clip.start();

        /*obj.transform.setPosition(new Vector3(2,1,1));

        System.out.println(obj.transform.position.x);*/
    }
}
