package com.basicexample;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.Input;
import javafx.scene.input.KeyCode;

public class CustomPlayer extends JPlayer {
    public boolean canMove;
    public int moveSpeed;

    private boolean flipFlop;

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity identity, boolean move, int moveSpeed) {
        super(transform, newSprite, identity);
        this.moveSpeed = moveSpeed;
        setCollider(new JBoxCollider(transform, identity,128,128,this, false));
        this.canMove = move;
    }

    @Override
    public void onKeyReleased(KeyCode key) {
        if(key == KeyCode.X)
        {
            flipFlop = !flipFlop;
            JSceneManager.getWindow().setWindowScale((flipFlop)? 0.5f:1);
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
