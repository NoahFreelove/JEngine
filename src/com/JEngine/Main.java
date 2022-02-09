package com.JEngine;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.Behavior;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;

/*
    Scene holds all objects
    Camera creates a sprite using JWindow at desired location
*/

//TODO: UI classes
//TODO: Networking?

public class Main {
    static Vector3 position = new Vector3(0,50,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1,1,1);

    static Transform transform = new Transform(position, rotation, scale);

    public static String[] savedArgs;

    public static void main(String[] args) {
        savedArgs = args;

        //JAudioPlayer ap = new JAudioPlayer("bin\\\\piano2.wav");
        //ap.startClip();

        // create a new window
        JWindow window = new JWindow(800,800,"JEngine", true, 1);

        // create a new scene
        JScene scene = new JScene(window, 2);

        // create a pawn object
        Pawn pawn = new Pawn(transform, new JImage(true, "bin\\\\image.png", 128,128), new Identity("Pawn 1", "pawn"));

        // create camera
        JCamera camera = new JCamera(new Vector3(400,400,1),window,scene, new Object(null, null), 350, new Identity("camera","MainCamera"));

        // set main camera
        window.setCamera(camera);
        Object o = new Object(transform, new Identity("Obj","tag"));
        // add pawn to scene
        scene.add(pawn);
        scene.add(o);

        pawn.setActive(true);

        // set FPS
        window.setTargetFPS(30);

        Behavior behavior = (int totalFrameCount) -> {
            pawn.Move(Direction.DownRight, 10);
        };

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
        window.AddUpdateBehavior(behavior);
    }
}
