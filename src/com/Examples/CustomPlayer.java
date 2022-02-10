package com.Examples;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;

public class CustomPlayer extends JPlayer {
    public CustomPlayer(Transform transform, JImage newSprite, com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }

    @Override
    public void Update()
    {
        Move(Direction.DownRight, 15);
    }
}
