package com.example;

import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.PrimitiveTypes.JImage;
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
    Vector3 position2 = new Vector3(100,500,0);
    boolean posSwitch = false;

    @Override
    public void Update()
    {
        time+=0.01;
        //transform.rotation.x +=1;
        if(posSwitch){
            transform.setPosition(JMath.interpolateClamped(transform.position, position, time));
        }
        else
        {
            transform.setPosition(JMath.interpolateClamped(transform.position, position2, time));
        }
        if(transform.position == position)
        {
            time = 0;
            posSwitch = true;
        }
        else if (transform.position == position2)
        {
            time = 0;
            posSwitch = false;
        }
    }
}
