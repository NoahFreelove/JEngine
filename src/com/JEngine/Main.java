package com.JEngine;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JViewport;
import com.JEngine.Game.Visual.JWindow;
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

        JWindow window = new JWindow(800,800,"Suuuup", false);
        JScene scene = new JScene(window, 10);
        JViewport viewport = new JViewport(scene);
        JCamera camera = new JCamera(window,scene,viewport);
        Pawn pawn = new Pawn(t, new JImage(true, "bin\\\\image.png"));

        scene.add(pawn);

        //pawn.Move(Direction.UpRight, 1);
    }
}
