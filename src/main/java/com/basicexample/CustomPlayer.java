package com.basicexample;

import com.JEngine.Game.PlayersAndPawns.Colliders.BoxCollider;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.Utility.Input;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class CustomPlayer extends Player {
    public boolean canMove;
    public int moveSpeed;
    private boolean flipFlop;

    Rectangle rect;

    public CustomPlayer(Transform transform, GameImage newSprite, JIdentity identity, boolean move, int moveSpeed) {
        super(transform, newSprite, identity);
        this.moveSpeed = moveSpeed;
        setCollider(new BoxCollider(transform, identity,128,128,this, false));
        this.canMove = move;
        // create rect center screen
    }

    @Override
    public void onKeyReleased(KeyCode key) {
        if(key == KeyCode.X)
        {
            flipFlop = !flipFlop;
            SceneManager.getWindow().setWindowScale((flipFlop)? 0.5f:1);
        }
    }

    @Override
    public void Update()
    {
        if(canMove && getJIdentity().compareName("Player 1")) {
            if (Input.W_Pressed)
                Move(Direction.Up, moveSpeed);
            if(Input.S_Pressed)
                Move(Direction.Down, moveSpeed);
            if(Input.A_Pressed)
                Move(Direction.Left, moveSpeed);
            if(Input.D_Pressed)
                Move(Direction.Right, moveSpeed);
        }
        else if (canMove && getJIdentity().compareName("Player 2")) {
            if (Input.UArrow_Pressed)
                Move(Direction.Up, moveSpeed);
            if(Input.DArrow_Pressed)
                Move(Direction.Down, moveSpeed);
            if(Input.LArrow_Pressed)
                Move(Direction.Left, moveSpeed);
            if(Input.RArrow_Pressed)
                Move(Direction.Right, moveSpeed);
        }
        getCollider().checkAllCollision();
    }
}
