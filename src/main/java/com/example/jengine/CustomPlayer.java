package com.example.jengine;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.Utility.JMath;

public class CustomPlayer extends JPlayer {
    public CustomPlayer(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }
    float time = 0f;
    Vector3 position = new Vector3(100,500,0);
    @Override
    public void Update()
    {
        time+=0.005;
        transform.setPosition(JMath.lerp(transform.position, position, JMath.clamp(0,1,time)));
    }
}
