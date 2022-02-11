package com.Examples;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.UserInterface.JText;
import com.JEngine.UserInterface.JUIBackgroundImage;
import com.JEngine.Utility.About.JAppInfo;
import com.JEngine.Utility.Settings.SettingTypes.JSettingDropdown;


import java.awt.*;

import static com.Examples.Settings.settingManager;

//TODO: UI classes
//TODO: Networking?

public class Main {
    static Vector3 position = new Vector3(40,300,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1f,1,1);

    static Transform transform = new Transform(position, rotation, scale);


    public static String[] savedArgs;
    public static JAppInfo appInfo = new JAppInfo("JEngine Example", "Noah Freelove", 2022, 0,2, true);

    public static void main(String[] args) {
        savedArgs = args;
        System.out.println(appInfo.getInfo());
        JSettingDropdown js = (JSettingDropdown) settingManager.getSpecificSetting("General Settings", "DropdownExample");
        //System.out.println(js.getNumberOfOptions());

        //JAudioPlayer ap = new JAudioPlayer("bin\\\\piano2.wav");
        //ap.startClip();

        // create a new window
        JWindow window = new JWindow(800,800,"JEngine", true, 1);

        // create a new scene
        JScene scene = new JScene(window, 3, "Scene 1");

        // create a pawn object
        CustomPlayer player = new CustomPlayer(transform, new JIcon(true, "bin/player2.png", 128,128), new JIdentity("Player 1", "Player"));

        CustomPlayer player2 = new CustomPlayer(new Transform(new Vector3(300,50,0), rotation, scale), new JIcon(true, "bin/player2.png", 128,128), new JIdentity("Player 2", "Player"));

        // create camera
        CustomCamera camera = new CustomCamera(new Vector3(0,0,1),window,scene, new JObject(null, null), 800, new JIdentity("Main Camera","sceneObj"));

        // set main camera
        window.setCamera(camera);

        // set window icon
        window.setIcon(new JIcon(true, "bin/jengineicon.png", 128,128));

        JText text = new JText(new Transform(new Vector3(0,200,1),new Vector3(0,0,0),new Vector3(1,1,1)), new JIdentity("Test Text", "Text"), "Hello World!", new Font("Arial", Font.PLAIN, 24), 1000, 100);
        JUIObject uiImage = new JUIObject(new Transform(new Vector3(100,100,1),new Vector3(0,0,0),new Vector3(1,1,1)),new JIdentity("Test JUIElement", "JUIElement"), (new JIcon(true, "bin/jengineicon.png", 128,128).getImage()), 128, 128);
        JUIBackgroundImage background = new JUIBackgroundImage(new JIdentity("Scene Background", "JUIElement"), (new JIcon(true, "bin/background.png", 800,800).getImage()), 800, 800);

        // add objects to scene
        scene.addUI(uiImage);

        scene.add(player);
        scene.add(camera);

        scene.add(player2);
        scene.addUI(text);
        scene.addUI(background);

        // set FPS
        window.setTargetFPS(30);

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
        /*
        // Example of how to use findObjectsByIdentity
        JObject[] searchResult = scene.findObjectsByIdentity(null, "sceneObj", SearchType.SearchByTag);
        if(searchResult.length>0)
        {
            for (JObject result :
                    searchResult) {
                System.out.println(result.JIdentity.getName());
            }
        }
        */
    }
}
