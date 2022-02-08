package com.JEngine;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Transform t = new Transform(position, rotation, scale);

    public static String[] savedArgs;

    public static void main(String[] args) {
        savedArgs = args;
        /*try {
            File wavFile = new File("Sounds\\\\piano2.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wavFile));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
        Scanner s = new Scanner(System.in);
        s.next();*/
        Pawn p = new Pawn(t, new JImage());
        p.Move(Direction.UpRight, 1);
    }
}
