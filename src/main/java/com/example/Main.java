package com.example;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.*;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.UserInterface.JPointer;
import com.JEngine.Utility.About.JAppInfo;
import com.JEngine.Utility.GenericMethodCall;
import com.JEngine.Utility.JDo;
import com.JEngine.Utility.JUtility;
import com.JEngine.Utility.Settings.EnginePrefs;
import com.JEngine.Utility.WaitForSecondsEvent;
import javafx.application.Application;
import javafx.stage.Stage;

//TODO: UI classes
//TODO: Networking?

public class Main extends Application {
    Vector3 position = new Vector3(0,0,0);
    Vector3 rotation = new Vector3(0,0,0);
    Vector3 scale = new Vector3(1f,1,1);

    Transform transform = new Transform(position, rotation, scale);

    public static String[] savedArgs;
    static JAppInfo appInfo = new JAppInfo("JEngine Example", "Noah Freelove", 2022, 0,2, false);

    public void start(Stage stage) {
        EnginePrefs.log = true;
        EnginePrefs.logExtra = false;
        EnginePrefs.logAnnoyance = false;
        appInfo.logInfo();

        String binFolder = System.getProperty("user.home") + "\\Documents\\JEngine\\bin\\";
        String fp =  binFolder + "player2.png";
        String fp2 =  binFolder + "jengineicon.png";
        String jScenePath =  binFolder + "scene1.JScene";

        /*WaitForSecondsEvent event = () -> System.out.println("Done waiting");
        JUtility.waitForSeconds(3, event);*/

        //JSettingDropdown js = (JSettingDropdown) settingManager.getSpecificSetting("General Settings", "DropdownExample");
        //JAudioPlayer ap = new JAudioPlayer(binFolder + "piano2.wav");
        //ap.startClip();

        // create a new window
        JWindow window = new JWindow(800,800,"JEngine", stage);

        // create a new scene
        JScene scene = new JScene(window, 15, "Scene 1");
        JSceneManager.setScene(scene);

        //scene.loadFromFile(jScenePath);
        /*CustomBoolSetting cbs = (CustomBoolSetting) settingManager.getSpecificSetting("General Settings", "Bool Set");

        ValueChangedEvent newValueChangedEvent = (Object newValue) -> System.out.println(newValue.toString());
        cbs.setEventValueChanged(newValueChangedEvent);
        cbs.setValue(false);*/

        // create a pawn object
        CustomPlayer player = new CustomPlayer(Transform.exSimpleTransform(550,100), new JImage(true, fp, 128,128), new JIdentity("Player 1", "Player"),true);
        CustomPlayer player2 = new CustomPlayer(Transform.exSimpleTransform(500,400), new JImage(true, fp, 128,128), new JIdentity("Player 2", "Player"),false);
        CustomPlayer player3 = new CustomPlayer(Transform.exSimpleTransform(100,400), new JImage(true, fp, 128,128), new JIdentity("Player 3", "Player"),false);

        // create camera
        CustomCamera camera = new CustomCamera(new Vector3(0,0,1),window,scene, new JObject(null, null), 800, new JIdentity("Main Camera","sceneObj"));
        JPointer jp = new JPointer(new JImage(true,fp2,16,16));
        jp.setWindowCursor(window.getScene());

        // set main camera
        window.setCamera(camera);

        // set window icon
        window.setIcon(new JImage(true, fp, 128,128));

        //JText text = new JText(Transform.exSimpleTransform(15,300), new JIdentity("Test Text", "Text"), "Hello World!", null, 1000, 0);
        //JUIObject uiImage = new JUIObject(Transform.exSimpleTransform(100,100),new JIdentity("Test JUIElement", "JUIElement"), (new JImage(true, fp2, 128,128).getImage()), 128, 128);
        //JUIBackgroundImage background = new JUIBackgroundImage(new JIdentity("Scene Background", "JUIElement"), new JImage(true, fp2, 800,800).getImage(), 800, 800);

        //JButton button = new JButton(Transform.exSimpleTransform(100,300), new JIdentity("Test Text", "Text"), null, 150,200, 300, 300);

        //scene.addUI(button);

        // add objects to scene
        scene.add(camera);
        scene.add(player3);
        scene.add(player2);
        scene.add(player);

        //JDo.Do(args -> System.out.println(args[0].toString()), 5, new Object[]{"hi"});

        // set FPS
        window.setTargetFPS(30);

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
    }

    public static void main(String[] args) {
        savedArgs = args;
        launch();

    }
}
