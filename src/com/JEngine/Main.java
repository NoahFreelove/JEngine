package com.JEngine;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.Worker;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;

/*
    Scene holds all objects
    Viewport gets all objects which should be visible
    Camera creates a sprite using JWindow at desired location
*/
public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Transform t = new Transform(position, rotation, scale);

    public static String[] savedArgs;

    public static void main(String[] args) throws InterruptedException {
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

        // create a new window
        JWindow window = new JWindow(800,800,"Suuuup", true);

        // create a new scene
        JScene scene = new JScene(window, 1);

        // create a pawn object
        Pawn pawn = new Pawn(t, new JImage(true, "bin\\\\image.png"));

        // create camera
        JCamera camera = new JCamera(window,scene, pawn, 25);

        // set main camera
        window.setCamera(camera);

        // add pawn to scene
        scene.add(pawn);

        // set FPS
        window.setDesiredFPS(1f);

        Worker w = () -> {
            pawn.Move(Direction.Up, 10);
        };
        window.AddUpdateBehavior(w);
        window.start();

    }

}

