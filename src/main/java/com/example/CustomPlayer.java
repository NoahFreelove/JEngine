package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

public class CustomPlayer extends JPlayer {
    public boolean move;
    public CustomPlayer(Transform transform, JImage newSprite, JIdentity JIdentity, boolean move) {
        super(transform, newSprite, JIdentity);
        collider.setOnCollisionEnterEvent(otherObject -> System.out.println( JIdentity.getName() +  " colliding with: " + otherObject.JIdentity.getName()));
        collider.setOnCollisionExitEvent(otherObject -> System.out.println( JIdentity.getName() +  " stopped colliding with: " + otherObject.JIdentity.getName()));

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
            Move(Direction.Right, 6);
        collider.checkAllCollision();
    }
}
