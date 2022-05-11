package com.Examples.MovingSquare;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.TemplateObjects.ScreenBorder;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;

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
    }

    private static void createSceneObjects(GameScene scene){
        new ScreenBorder(new Vector3(0,0,0));

        MovingSquare player1 = new MovingSquare(new Vector3(300,200,0), 1);
        MovingSquare player2 = new MovingSquare(new Vector3(500,200,0), 2);

        Floor floor1 = new Floor(2, new Vector3(0,400,0));
        Floor floor2 = new Floor(1, new Vector3(700,500,0));
        scene.add(floor1);
        scene.add(floor2);

        scene.add(player1);
        scene.add(player2);

        Text text = new Text("JEngine - Moving Square Example");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 40px;");
        text.setX(50);
        text.setY(50);
        scene.addUI(text);

        try {
            File f = new File("C:/Users/s201063813/Desktop/texture.png");
            ImageIO.write(GenerateSolidTexture.generate(128,128, 0xFF0000FF), "PNG", f);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
