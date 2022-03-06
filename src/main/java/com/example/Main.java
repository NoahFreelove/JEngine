package com.example;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.UserInterface.JPointer;
import com.JEngine.UserInterface.JText;
import com.JEngine.Utility.About.JAppInfo;
import com.JEngine.Utility.Misc.GenericMethodCall;
import com.JEngine.Utility.Misc.JTimer;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.JEngine.Utility.IO.FileOperations.stringArrToFile;

//TODO: UI classes
//TODO: Update README.md

public class Main extends Application {

    Vector3 position = new Vector3(0,0,0);
    Vector3 rotation = new Vector3(0,0,0);
    Vector3 scale = new Vector3(1f,1,1);

    Transform transform = new Transform(position, rotation, scale);

    public static String[] savedArgs;

    public static JCamera camera2;
    public static JCamera camera;

    public static String binFolder = System.getProperty("user.home") + "\\Documents\\JEngine\\bin\\";

    public void start(Stage stage) {
        EnginePrefs.logImportant = true;
        EnginePrefs.logInfo = true;
        EnginePrefs.logExtra = false;
        EnginePrefs.logAnnoyance = false;

        JAppInfo.authors = new String[]{"Noah Freelove"};
        JAppInfo.appName = "JEngine";
        JAppInfo.appVersionMajor = 0;
        JAppInfo.appVersionMinor = 1.2f;
        JAppInfo.year = 2022;
        JAppInfo.isCopyright = false;

        JAppInfo.logInfo();

        String fp =  binFolder + "player2.png";
        String fp2 =  binFolder + "cursor.png";
        String fp3 =  binFolder + "player1.png";

        // create a new window
        JWindow window = new JWindow(1f,"JEngine", stage);

        // create a new scene
        JScene scene = new JScene(window, 15, "Scene 1");

        // create camera
        camera = new JCamera(new Vector3(0,0,1),window,scene, new JObject(null, null), new JIdentity("Main Camera","camera"));
        camera2 = new JCamera(new Vector3(250,0,0), window, scene, null,
                new JIdentity("Camera 2", "camera"));

        JImage player1Img = new JImage(true, fp3, 128,128);
        JImage player2Img = new JImage(false, fp, 128,128);
        JSceneManager.init(scene,window,camera);

        /*CustomBoolSetting cbs = (CustomBoolSetting) settingManager.getSpecificSetting("General Settings", "Bool Set");
        ValueChangedEvent newValueChangedEvent = (Object newValue) -> System.out.println(newValue.toString());
        cbs.setEventValueChanged(newValueChangedEvent);
        cbs.setValue(false);*/

        // create a pawn object
        CustomPlayer player = new CustomPlayer(Transform.exSimpleTransform(550,100), player1Img, new JIdentity("Player 1", "Player"),true, 10);
        CustomPlayer player2 = new CustomPlayer(Transform.exSimpleTransform(500,400), new JImage(true, fp, 128,128), new JIdentity("Player 2", "Player"),false, 0);
        CustomPlayer player3 = new CustomPlayer(Transform.exSimpleTransform(100,400), new JImage(true, fp, 128,128), new JIdentity("Player 3", "Player"),false, 0);

        JPointer jp = new JPointer(new JImage(true,fp2,16,16));
        jp.setWindowCursor(window.getScene());

        // set main camera
        window.setCamera(JSceneManager.mainCamera);

        // set window icon
        window.setIcon(new JImage(true, fp, 128,128));

        // add objects to scene
        scene.add(camera);
        scene.add(player);
        Text t = new Text(10, 50, "This is a test");
        scene.addUI(t);
        //JDo.Do(args -> System.out.println(args[0].toString()), 5, new Object[]{"hi"});

        // set FPS
        window.setTargetFPS(60);

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
    }

    public static void main(String[] args) {
        savedArgs = args;
        launch();
    }

}
