package com.basicexample;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Components.PhysicsBody_Comp;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Core.FlipFlop;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Core.Identity;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.TemplateObjects.BackgroundImage;
import com.JEngine.TemplateObjects.ScreenBorder;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Settings.EnginePrefs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static GameCamera camera;

    public static String binFolder = System.getProperty("user.home") + "\\Documents\\JEngine\\bin\\";

    public static FlipFlop cameraFlipFlop = new FlipFlop();
    public static CustomPlayer player;
    public static CustomPlayer player2;

    private static FlipFlop sceneFlop = new FlipFlop();
    static void setEnginePrefs()
    {
        //Set engine preferences
        EnginePrefs.logImportant = true;
        EnginePrefs.logInfo = true;
        EnginePrefs.logExtra = true;
        EnginePrefs.logDebug = false;
        EnginePrefs.aggressiveGC = false;

        //Set app info
        GameInfo.authors = new String[]{"Noah Freelove"};
        GameInfo.appName = "JEngine";
        GameInfo.appVersionMajor = 0;
        GameInfo.appVersionMinor = 1.9f;
        GameInfo.year = 2022;
        GameInfo.buildID = "2022.04.27.1";
        GameInfo.isCopyright = false;

        //Print app info
        GameInfo.logGameInfo(true);
    }

    public void start(Stage stage) {

        setEnginePrefs();
        //Path to player image
        String filepath =  binFolder + "player1.png";
        String filepath2 =  binFolder + "player2.png";

        // create a new scene
        GameScene scene = new GameScene(15, "Scene 1");
        GameScene scene2 = new GameScene(15, "Scene 2");

        // create a new window
        GameWindow window = new GameWindow(scene,1f,"JEngine", stage);
        // create camera

        // create player image
        GameImage image = new GameImage(filepath, 128, 128);
        GameImage image2 = new GameImage(filepath2, 128,128);

        // create a player object
        player = new CustomPlayer(Transform.exSimpleTransform(550,100), image, new Identity("Player 1", "Player"),true, 5);
        player2 = new CustomPlayer(Transform.exSimpleTransform(700,100), image2, new Identity("Player 2", "Player"),true, 10);

        camera = new GameCamera(new Vector3(0,0,1), window, scene, null, new Identity("Main Camera","camera"));

        Pointer pointer = new Pointer(null, true);
        camera.setParent(pointer);

        new ScreenBorder(new Vector3(0,0,0));
        BackgroundImage background = new BackgroundImage(new File(binFolder + "/background.png").getAbsolutePath());
        scene.add(background);
        // add objects to scene
        scene.add(camera);
        scene.add(player);
        //player2.addChild(player);
        scene.add(player2);
        camera.setParent(player);
        Text titleText = new Text(10, 50, "JEngine Moving Squares Example");
        titleText.setFont(Font.font ("arial", 25));
        titleText.setFill(Color.WHITE);
        window.parent.getChildren().add(titleText);
        // set background color to a dark purple
        window.setBackgroundColor(Color.web("#5d34a5"));

        window.setTargetFPS(60);

        GameObject go = new GameObject(Transform.exSimpleTransform(0,0), new Identity("Test", "Test"), true);
        PhysicsBody_Comp comp = new PhysicsBody_Comp(true);
        PhysicsBody_Comp comp2 = new PhysicsBody_Comp(true);

        //player.addComponent(comp);
        player.physicsComp = comp;
        player.addComponent(new DontDestroyOnLoad_Comp());
        camera.addComponent(new DontDestroyOnLoad_Comp());
        //player2.addComponent(comp2);
        //player2.physicsComp = comp2;
        scene.add(go);
        window.start();

        window.getStage().addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.C) {
                    if(!sceneFlop.getState())
                    {
                        SceneManager.switchScene(scene2);
                    }
                    else
                        SceneManager.switchScene(scene);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}
