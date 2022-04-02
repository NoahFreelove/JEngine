package com.basicexample;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.TemplateObjects.BackgroundImage;
import com.JEngine.TemplateObjects.ScreenBorder;
import com.JEngine.Utility.About.JAppInfo;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    //TODO: Add children to objects

    public static JCamera camera;

    public static String binFolder = System.getProperty("user.home") + "\\Documents\\JEngine\\bin\\";

    public void start(Stage stage) {
        //Set engine preferences
        EnginePrefs.logImportant = true;
        EnginePrefs.logInfo = true;
        EnginePrefs.logExtra = false;
        EnginePrefs.logAnnoyance = false;
        EnginePrefs.aggressiveGC = false;

        //Set app info
        JAppInfo.authors = new String[]{"Noah Freelove"};
        JAppInfo.appName = "JEngine";
        JAppInfo.appVersionMajor = 0;
        JAppInfo.appVersionMinor = 1.5f;
        JAppInfo.year = 2022;
        JAppInfo.buildID = "2022.04.01.2";
        JAppInfo.isCopyright = false;

        //Print app info
        JAppInfo.logAppInfo(true);

        //Path to player image
        String filepath =  binFolder + "player1.png";
        String filepath2 =  binFolder + "player2.png";

        // create a new scene
        JScene scene = new JScene(15, "Scene 1");

        // create a new window
        JWindow window = new JWindow(scene,1f,"JEngine", stage);

        // create camera
        camera = new JCamera(new Vector3(0,0,1), window, scene, new JObject(null, null), new JIdentity("Main Camera","camera"));

        // Initialize the scene manager
        JSceneManager.init(scene,window,camera);

        // create player image
        JImage image = new JImage(filepath, 128, 128);
        JImage image2 = new JImage(filepath2, 128,128);

        // create a player object
        CustomPlayer player = new CustomPlayer(Transform.exSimpleTransform(550,100), image, new JIdentity("Player 1", "Player"),true, 10);
        CustomPlayer player2 = new CustomPlayer(Transform.exSimpleTransform(700,100), image2, new JIdentity("Player 2", "Player"),true, 10);
        new ScreenBorder(new Vector3(0,0,0));
        BackgroundImage background = new BackgroundImage(new File(binFolder + "/background.png").getAbsolutePath());
        scene.add(background);
        // add objects to scene
        scene.add(camera);
        scene.add(player);
        scene.add(player2);

        Text titleText = new Text(10, 50, "JEngine Moving Squares Example");
        titleText.setFont(Font.font ("arial", 25));
        titleText.setFill(Color.WHITE);
        window.parent.getChildren().add(titleText);
        // set background color to a dark purple
        window.setBackgroundColor(Color.web("#5d34a5"));

        window.setTargetFPS(60);

        // run Start function on other thread so the update functions doesn't stop the rest of the main function
        window.start();
    }

    public static void main(String[] args) {
        launch();
    }

}
