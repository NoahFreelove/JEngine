package com.Examples.MovingSquare;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Components.PhysicsBody_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Sound.AudioPlayer;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.TemplateObjects.ScreenBorder;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.ImageProcessingAndEffects.GameLight;
import javafx.application.Application;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class MovingSquareExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static GameScene scene;
    public static GameScene scene2;
    @Override
    public void start(Stage stage) throws Exception {
        scene = new GameScene(5, "Moving Square Example");
        scene2 = new GameScene(5, "Second Scene");

        GameWindow window = new GameWindow(scene, 1f, "Moving Squares", stage);
        GameCamera camera = new GameCamera(new Vector3(0,0,0), window, scene,null, new Identity("MainCamera", "camera"));
        scene.add(camera);

        createSceneObjects(scene);

        window.setTargetFPS(60);
        window.setBackgroundColor(Color.MEDIUMPURPLE);
        window.start();

        scene.disableLighting();
        scene2.enableLighting();
        scene2.addLight(new GameLight(new Lighting(new Light.Distant(50,50,Color.WHITE)), false));
        window.setUseSceneName(true);
    }

    private static void createSceneObjects(GameScene scene){
        new ScreenBorder(new Vector3(0,0,0), true);

        MovingSquare player1 = new MovingSquare(new Vector3(300,200,0), 1);
        MovingSquare player2 = new MovingSquare(new Vector3(500,200,0), 2);

        Pawn ai = new Pawn(Transform.exSimpleTransform(1000,100), new GameImage(new File("")), new Identity("AI", "ai"));
        ai.addComponents(new PhysicsBody_Comp(true, new Vector2(0,1)), new BoxCollider_Comp(new Vector3(0,0,0), 128, 128, false, ai), new Pathfinding_Comp(player1));

        Floor floor1 = new Floor(2, new Vector3(0,400,0));
        Floor floor2 = new Floor(1, new Vector3(700,480,0));


        Text text = new Text("JEngine - Moving Square Example - Press C to Toggle Other Objects");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 40px;");
        text.setX(10);
        text.setY(50);
        scene.addUI(text);

        scene.add(floor1, floor2, player1, player2, ai);
    }
}
