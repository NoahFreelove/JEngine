package com.basicexample;

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
        collider.setOnCollisionEnterEvent(otherObject -> LogInfo(identity.getName() +  " colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> LogInfo(identity.getName() +  " stopped colliding with: " + ((JObject)otherObject[0]).JIdentity.getName()));

        this.canMove = move;
    }

    @Override
    public void onKeyReleased(KeyCode key) {
        if(key == KeyCode.X)
        {
            flipFlop = !flipFlop;
            JSceneManager.window.setWindowScale((flipFlop)? 0.5f:1);
        }
    }

    @Override
    public void Update()
    {
        if(canMove) {
            if (Input.Up)
                Move(Direction.Up, moveSpeed);
            if(Input.Down)
                Move(Direction.Down, moveSpeed);
            if(Input.Left)
                Move(Direction.Left, moveSpeed);
            if(Input.Right)
                Move(Direction.Right, moveSpeed);
        }
        collider.checkAllCollision();
    }
}
