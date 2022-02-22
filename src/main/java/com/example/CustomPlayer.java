package com.example;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.Game.PlayersAndPawns.JPlayer;
import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.Game.Visual.SearchType;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.JMath;


public class CustomPlayer extends JPlayer {
    public boolean move;
    float time = 0f;
    Vector3 position = new Vector3(700,400,0);
    Vector3 position2 = new Vector3(-100,250,0);
    JBoxCollider collider;
    boolean posSwitch = false;
    CustomPlayer player1ref;

    public CustomPlayer(Transform transform, JImage newSprite, JIdentity JIdentity, boolean move) {
        super(transform, newSprite, JIdentity);
        if(JIdentity.compareName("Player 1"))
        {
            collider = new JBoxCollider(transform, JIdentity, false, 128, 128);
        }
        else {
            collider = new JBoxCollider(transform, JIdentity, false, 100, 100);
        }
        this.move = move;
    }


    @Override
    public void Start() {
        if(JIdentity.getName().equals("Player 1"))
            return;
        for (JObject obj :
                JSceneManager.getScene().findObjectsByIdentity("Player 1",null, SearchType.SearchByName)) {
            if (obj != null)
            {
                player1ref = (CustomPlayer) obj;
            }
        }
    }


    @Override
    public void Update()
    {
        if(!move)
            return;

        time+=0.01;
        if(player1ref !=null)
        {
            System.out.println(collider.isCollidingWith(player1ref.collider));
        }

        //transform.rotation.x +=1;
        if(posSwitch){
            transform.setPosition(JMath.interpolateClamped(transform.position, position, time));
        }
        else
        {
            transform.setPosition(JMath.interpolateClamped(position, position2, time));
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
