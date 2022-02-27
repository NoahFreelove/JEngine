package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.JScene;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.GenericMethodCall;
import javafx.scene.input.KeyCode;

public class CustomPlayer extends JPlayer {
    public boolean canMove;
    private boolean camFlip;

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity identity, boolean move) {
        super(transform, newSprite, identity);
        collider.setOnCollisionEnterEvent(otherObject -> System.out.println( identity.getName() +  " colliding with: " + otherObject.JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> System.out.println( identity.getName() +  " stopped colliding with: " + otherObject.JIdentity.getName()));
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
        this.canMove = move;
    }

    @Override
    public void Start() {
        collider.initializeCollider();
    }

    void switchScene()
    {
        JScene scene = new JScene(JSceneManager.window, 1, "Scene 2");
        JSceneManager.setScene(scene);
    }

    void switchCamera() {
        if(!canMove)
            return;

        camFlip = !camFlip;

        if (camFlip)
        {
            JSceneManager.setMainCamera(Main.camera2);
        }
        else
        {
            JSceneManager.setMainCamera(Main.camera);
        }
    }

    @Override
    public void Update()
    {
        if(canMove)
        {
            if(Input.Up)
            {
                Move(Direction.Up, 4);
            }
            if(Input.Down)
            {
                Move(Direction.Down, 4);
            }
            if(Input.Left)
            {
                Move(Direction.Left, 4);
            }
            if(Input.Right)
            {
                Move(Direction.Right, 4);
            }
        }
        collider.checkAllCollision();
    }
}
