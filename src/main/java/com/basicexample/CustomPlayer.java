package com.basicexample;

import com.JEngine.Components.PhysicsComponent;
import com.JEngine.Game.PlayersAndPawns.Colliders.BoxCollider;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Identity;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector2;
import com.JEngine.Utility.Input;
import javafx.scene.input.KeyCode;

public class CustomPlayer extends Player {
    public boolean canMove;
    public int moveSpeed;
    private boolean flipFlop;
    PhysicsComponent physicsComp;
    private float jumpZAcceleration = 5;

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
                    Main.camera.setParent(Main.player2);
                }
            }
        }
    }

    @Override
    public void Update()
    {
        if(canMove && getIdentity().compareName("Player 1")) {
            if(Input.A_Pressed)
                Move(Direction.Left, moveSpeed);
            if(Input.D_Pressed)
                Move(Direction.Right, moveSpeed);

            if(Input.Shift_Pressed && physicsComp.isOnGround())
            {
                jump();
            }
        }
        else if (canMove && getIdentity().compareName("Player 2")) {
            if(Input.DArrow_Pressed)
                Move(Direction.Down, moveSpeed);
            if(Input.LArrow_Pressed)
                Move(Direction.Left, moveSpeed);
            if(Input.RArrow_Pressed)
                Move(Direction.Right, moveSpeed);
        }
        getCollider().checkAllCollision();
        super.Update();

    }

    public float getJumpZAcceleration() {
        return jumpZAcceleration;
    }

    public void setJumpZAcceleration(float jumpZAcceleration) {
        this.jumpZAcceleration = jumpZAcceleration;
    }

    void jump(){
        physicsComp.addVelocity(new Vector2(0, -jumpZAcceleration));
    }
}
