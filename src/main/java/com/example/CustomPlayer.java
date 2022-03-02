package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JSceneLoader;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.ImageProcessing.BufferedImageBlur;
import com.JEngine.Utility.Input;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import java.awt.image.BufferedImage;

public class CustomPlayer extends JPlayer {
    public boolean canMove;
    private boolean camFlip;
    private final Image origImage;
    private final Image blurredImage;
    public int moveSpeed;

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity identity, boolean move, int moveSpeed) {
        super(transform, newSprite, identity);
        this.moveSpeed = moveSpeed;
        collider.setOnCollisionEnterEvent(otherObject -> LogInfo(identity.getName() +  " colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> LogInfo(identity.getName() +  " stopped colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));

        Input.addKeyUpEvent(args -> {
            if(args[0] == KeyCode.X)
            {
                switchCamera();
            }
        });

        Input.addKeyUpEvent(args -> {
            if(args[0] == KeyCode.Z)
            {
                if(canMove)
                {
                    switchScene();
                }
            }
        });

        origImage = newSprite.getImage();
        blurredImage = BufferedImageBlur.blurImage(origImage,3);
        this.canMove = move;
    }

    void switchScene()
    {
        JScene scene = new JScene(JSceneManager.window, 5, "Scene 2");
        Sprite s = new Sprite(Transform.exSimpleTransform(20,400), new JImage(true, Main.binFolder + "player2.png", 128, 128), new JIdentity("New Player", "Sprite"));
        JSceneManager.getActiveScene().migrateScene(scene, true, true);
        scene.add(s);
    }

    void switchCamera() {
        if(!canMove)
            return;

        camFlip = !camFlip;

        if (camFlip)
        {
            getSprite().setImage(blurredImage);
            //JSceneManager.setMainCamera(Main.camera2);
        }
        else
        {
            getSprite().setImage(origImage);
            //JSceneManager.setMainCamera(Main.camera);
        }
    }

    @Override
    public void Update()
    {
        if(canMove)
        {
            getSprite().setImage((JSceneManager.window.getIsFocused())? origImage : blurredImage);

            //System.out.println(Input.pressedKey);
            if(Input.Up)
            {
                Move(Direction.Up, moveSpeed);
            }
            if(Input.Down)
            {
                Move(Direction.Down, moveSpeed);
            }
            if(Input.Left)
            {
                Move(Direction.Left, moveSpeed);
            }
            if(Input.Right)
            {
                Move(Direction.Right, moveSpeed);
            }
        }
        collider.checkAllCollision();
    }
}
