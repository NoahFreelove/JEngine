package com.basicexample;

import com.JEngine.Components.PhysicsBody_Comp;
import com.JEngine.Game.PlayersAndPawns.Colliders.BoxCollider;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Direction;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.GameUtility;
import javafx.scene.input.KeyCode;

public class CustomPlayer extends Player {
    public boolean canMove;
    public int moveSpeed;
    private boolean flipFlop;
    PhysicsBody_Comp physicsComp;
    private float jumpAcceleration = 130f;

    public CustomPlayer(Transform transform, GameImage newSprite, Identity identity, boolean move, int moveSpeed) {
        super(transform, newSprite, identity);
        this.moveSpeed = moveSpeed;
        setCollider(new BoxCollider(transform, identity,128,128,this, false));
        this.canMove = move;
        // create rect center screen
    }

    @Override
    public void onKeyReleased(KeyCode key) {
        if (key == KeyCode.X) {
            flipFlop = !flipFlop;
            SceneManager.getWindow().setWindowScale((flipFlop) ? 0.5f : 1);
        } else if (key == KeyCode.Z && getIdentity().compareName("Player 1")) {
            {
                if (Main.cameraFlipFlop.getState()) {
                    Main.camera.setParent(Main.player);
                } else {
                    //Main.camera.setParent(Main.player2);
                }
            }
        }
    }

    @Override
    public void Update()
    {
        if(canMove && getIdentity().compareName("Player 1")) {
            if(Input.A_Pressed)
            {
                //physicsComp.addVelocity(new Vector2(-moveSpeed,0));
                Move(Direction.Left, moveSpeed);
            }
            if(Input.D_Pressed)
            {
                //physicsComp.addVelocity(new Vector2(moveSpeed,0));
                Move(Direction.Right, moveSpeed);
            }
            if(Input.Space_Pressed && physicsComp.isOnGround())
            {
                jump();
            }
            if(Input.Escape_Pressed)
            {
                GameUtility.exitApp();
            }
        }
        else if (canMove && getIdentity().compareName("Player 2")) {
            if(Input.LArrow_Pressed)
                Move(Direction.Left, moveSpeed);
            if(Input.RArrow_Pressed)
                Move(Direction.Right, moveSpeed);
            if(Input.Enter_Pressed && physicsComp.isOnGround())
            {
                jump();
            }
        }
        getCollider().checkAllCollision();
        super.Update();

    }

    public float getJumpAcceleration() {
        return jumpAcceleration;
    }

    public void setJumpAcceleration(float jumpAcceleration) {
        this.jumpAcceleration = jumpAcceleration;
    }

    void jump(){
        physicsComp.addAcceleration(new Vector2(0, -jumpAcceleration));
    }
}
