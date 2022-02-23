package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

public class CustomPlayer extends JPlayer {
    public boolean move;

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity JIdentity, boolean move) {
        super(transform, newSprite, JIdentity);
        this.move = move;
    }

    @Override
    public void Start() {

    }


    @Override
    public void Update()
    {
        collider.checkAllCollision();
    }
}
