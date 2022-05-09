package com.Examples.MovingSquare;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Components.PhysicsBody_Comp;
import com.JEngine.Core.FlipFlop;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Input;
import javafx.scene.input.KeyCode;

import java.io.File;

public class MovingSquare extends Player {
    private PhysicsBody_Comp physicsBody;
    private BoxCollider_Comp collider;
    private float moveSpeed = 5f;
    private final int playerNum;
    private FlipFlop sceneFlip = new FlipFlop();
    public MovingSquare(Vector3 initPos, int playerNum) {
        super(new Transform(initPos, new Vector3(0,0,0), new Vector3(0,0,0)), new GameImage(new File("bin/player"+ playerNum + ".png").getAbsolutePath(), 128, 128), new Identity("Player: " + playerNum, "player"));
        this.playerNum = GameMath.clamp(1,2,playerNum);

        physicsBody = new PhysicsBody_Comp(true, new Vector2(0,4));
        collider = new BoxCollider_Comp(new Vector3(0,0,0), getSprite().getWidth(), getSprite().getWidth(), false, this);
        addComponent(physicsBody);
        addComponent(collider);

        if(playerNum == 1)
        {
            addComponent(new DontDestroyOnLoad_Comp());
        }
    }

    @Override
    public void Update(){
        if(playerNum == 1)
        {
            player1Controls();
        }
        else if (playerNum == 2){
            player2Controls();
        }
        super.Update();
    }

    private void player1Controls(){
        if(Input.A_Pressed)
        {
            physicsBody.addVelocity(new Vector2(-moveSpeed,0));
        }
        if(Input.D_Pressed)
        {
            physicsBody.addVelocity(new Vector2(moveSpeed,0));
        }
        if(Input.W_Pressed)
        {
            if(physicsBody.isOnGround())
            {
                physicsBody.addVelocity(new Vector2(0,-15));
            }
        }
    }

    private void player2Controls(){
        if(Input.LArrow_Pressed)
        {
            physicsBody.addVelocity(new Vector2(-moveSpeed,0));
        }
        if(Input.RArrow_Pressed)
        {
            physicsBody.addVelocity(new Vector2(moveSpeed,0));
        }
        if(Input.UArrow_Pressed)
        {
            if(physicsBody.isOnGround())
            {
                physicsBody.addVelocity(new Vector2(0,-15));
            }
        }
    }

    @Override
    public void onKeyPressed(KeyCode key){
        if(playerNum == 2)
            return;
        if(key == KeyCode.C)
        {
            if(!sceneFlip.getState())
            {
                SceneManager.switchScene(MovingSquareExample.scene2);
            }
            else {
                SceneManager.switchScene(MovingSquareExample.scene);
            }
        }
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
