package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Animation.AnimFrame;
import com.JEngine.Game.Visual.Animation.AnimState;
import com.JEngine.Game.Visual.Animation.JAnimationTimeline;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.ImageProcessing.BufferedImageBlur;
import com.JEngine.Utility.Input;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import static com.example.Main.binFolder;

public class CustomPlayer extends JPlayer {
    public boolean canMove;
    private boolean camFlip;
    private boolean flipFlop;

    private final Image origImage;
    private final Image blurredImage;
    public int moveSpeed;

    String fp =  binFolder + "player2.png";
    String fp3 =  binFolder + "player1.png";
    String fp2 =  binFolder + "cursor.png";

    JImage player1Img = new JImage(true, fp3, 128,128);
    JImage player2Img = new JImage(true, fp, 128,128);
    JImage player3Img = new JImage(true, fp2, 128,128);

    AnimFrame[][] animFrames = new AnimFrame[][]{new AnimFrame[]{new AnimFrame(AnimState.IDLE, player1Img, 30), new AnimFrame(AnimState.IDLE, player2Img, 30)},
            new AnimFrame[]{new AnimFrame(AnimState.LEFT, player3Img, 30), new AnimFrame(AnimState.LEFT, player2Img, 30)}
    };

    JAnimationTimeline jAt = new JAnimationTimeline(animFrames, AnimState.IDLE, 60);

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity identity, boolean move, int moveSpeed) {
        super(transform, newSprite, identity);
        this.moveSpeed = moveSpeed;
        collider.setOnCollisionEnterEvent(otherObject -> LogInfo(identity.getName() +  " colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> LogInfo(identity.getName() +  " stopped colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));

        Input.addKeyUpEvent(args -> {
            if(args[0] == KeyCode.X)
            {
                flipFlop = !flipFlop;
                if(flipFlop)
                {
                    jAt.switchState(AnimState.LEFT);
                }
                else
                    jAt.switchState(AnimState.IDLE);
                //switchCamera();
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
        Sprite s = new Sprite(Transform.exSimpleTransform(20,400), new JImage(true, binFolder + "player2.png", 128, 128), new JIdentity("New Player", "Sprite"));
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

            // update the sprite every frame to correct sprite in animation
            setSprite(jAt.getCurrentFrame());


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
