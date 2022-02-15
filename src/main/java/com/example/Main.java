package com.example;

import com.JEngine.Game.Visual.*;
import com.JEngine.PrimitiveTypes.*;
import com.JEngine.PrimitiveTypes.Position.*;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.*;
import com.JEngine.UserInterface.*;
import com.JEngine.Utility.About.JAppInfo;
import com.JEngine.Utility.JUtility;
import com.JEngine.Utility.Settings.EnginePrefs;
import com.JEngine.Utility.Settings.ValueChangedEvent;
import com.JEngine.Utility.WaitForSecondsEvent;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

import static com.example.Settings.settingManager;

//TODO: UI classes
//TODO: Networking?

public class Main extends Application {
    static Vector3 position = new Vector3(0,0,0);
    static Vector3 rotation = new Vector3(0,0,0);
    static Vector3 scale = new Vector3(1f,1,1);

    static Transform transform = new Transform(position, rotation, scale);

    public static String[] savedArgs;
    static JAppInfo appInfo = new JAppInfo("JEngine Example", "Noah Freelove", 2022, 0,2, true);

    public void start(Stage stage) {
        EnginePrefs.log = true;
        EnginePrefs.logExtra = false;
        EnginePrefs.logAnnoyance = false;
        appInfo.logInfo();

        WaitForSecondsEvent event = () -> System.out.println("Done waiting");

        JUtility.waitForSeconds(3, event);

        //JSettingDropdown js = (JSettingDropdown) settingManager.getSpecificSetting("General Settings", "DropdownExample");
        //JAudioPlayer ap = new JAudioPlayer("C:\\Users\\noahf\\IdeaProjects\\JEngine\\bin\\piano2.wav");
        //ap.startClip();

        // create a new window
        JWindow window = new JWindow(800,800,"JEngine", stage);

        // create a new scene
        JScene scene = new JScene(window, 3, "Scene 1");
        String  binFolder = System.getProperty("user.home") + "\\Documents\\JEngine\\bin\\";

        String fp =  binFolder + "player2.png";
        String fp2 =  binFolder + "jengineicon.png";
        String jScenePath =  binFolder + "scene1.JScene";

        //scene.loadFromFile(jScenePath);
        /*CustomBoolSetting cbs = (CustomBoolSetting) settingManager.getSpecificSetting("General Settings", "Bool Set");

        ValueChangedEvent newValueChangedEvent = (Object newValue) -> System.out.println(newValue.toString());
        cbs.setEventValueChanged(newValueChangedEvent);
        cbs.setValue(false);*/


        // create a pawn object
        CustomPlayer player = new CustomPlayer(transform, new JImage(true, fp, 128,128), new JIdentity("Player 1", "Player"));
        //CustomPlayer player2 = new CustomPlayer(new Transform(new Vector3(300,50,0), rotation, scale), new JImage(true, fp, 128,128), new JIdentity("Player 2", "Player"));

        // create camera
        CustomCamera camera = new CustomCamera(new Vector3(0,0,1),window,scene, new JObject(null, null), 800, new JIdentity("Main Camera","sceneObj"));

        // set main camera
        window.setCamera(camera);

        // set window icon
        window.setIcon(new JImage(true, fp, 128,128));

        JText text = new JText(new Transform(new Vector3(15,300,1),new Vector3(0,0,0),new Vector3(1,1,1)), new JIdentity("Test Text", "Text"), "Hello World!", null, 1000, 0);
        JUIObject uiImage = new JUIObject(new Transform(new Vector3(100,100,1),new Vector3(0,0,0),new Vector3(1,1,1)),new JIdentity("Test JUIElement", "JUIElement"), (new JImage(true, fp2, 128,128).getImage()), 128, 128);
        JUIBackgroundImage background = new JUIBackgroundImage(new JIdentity("Scene Background", "JUIElement"), new JImage(true, fp2, 800,800).getImage(), 800, 800);
        System.out.println(Arrays.toString(JUtility.readFile(jScenePath)));
        // add objects to scene
        scene.add(camera);
        scene.add(player);
        //scene.add(player2);
        //scene.addUI(text);
        //scene.addUI(background);
        //scene.addUI(uiImage);

        // set FPS
        window.setTargetFPS(30);

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
    }
    public static void main(String[] args) {
        savedArgs = args;

        launch();
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
