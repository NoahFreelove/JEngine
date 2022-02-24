package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

public class CustomPlayer extends JPlayer {
    public boolean move;
    public CustomPlayer(Transform transform, JImage newSprite, JIdentity identity, boolean move) {
        super(transform, newSprite, identity);
        collider.setOnCollisionEnterEvent(otherObject -> System.out.println( identity.getName() +  " colliding with: " + otherObject.JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> System.out.println( identity.getName() +  " stopped colliding with: " + otherObject.JIdentity.getName()));

        this.move = move;
    }

    @Override
    public void Start() {
        collider.initializeCollider();
    }


    @Override
    public void Update()
    {
        if(move)
        {
            if(JIdentity.getName().equals("Player 3"))
            {
                Move(Direction.Right, 6);
            }
            else
                Move(Direction.Down, 4);
        }
        collider.checkAllCollision();
    }
}
