package com.JEngine;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Sound.JAudioPlayer;
import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.Behavior;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;

/*
    Scene holds all objects
    Camera creates a sprite using JWindow at desired location
*/

//TODO: Only have objects be visible in camera's range
//TODO: UI classes
//TODO: Networking?

public class Main {
    static Vector3 position = new Vector3(25,25,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Transform t = new Transform(position, rotation, scale);

    public static String[] savedArgs;

    public static void main(String[] args) {
        savedArgs = args;

        JAudioPlayer ap = new JAudioPlayer("bin\\\\piano2.wav");
        ap.startClip();
        // create a new window
        JWindow window = new JWindow(800,800,"JEngine", true, 1);

        // create a new scene
        JScene scene = new JScene(window, 1);

        // create a pawn object
        Pawn pawn = new Pawn(t, new JImage(true, "bin\\\\image.png", 64,64));

        // create camera
        JCamera camera = new JCamera(window,scene, pawn, 25);

        // set main camera
        window.setCamera(camera);

        // add pawn to scene
        scene.add(pawn);
        pawn.setActive(false);

        // set FPS
        window.setTargetFPS(10);

        Behavior behavior = (int totalFrameCount) -> {
            pawn.Move(Direction.Right, 20);
        };

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        new Thread(window::refresh).start();

        window.AddUpdateBehavior(behavior);
    }
}
