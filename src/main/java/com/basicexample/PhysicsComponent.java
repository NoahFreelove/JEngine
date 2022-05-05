package com.basicexample;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.PrimitiveTypes.Component;
import com.JEngine.PrimitiveTypes.Position.Vector2;

public class PhysicsComponent extends Component {
    private float mass;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 friction;
    private Vector2 gravity = new Vector2(9.8f,9.8f);
    private boolean hasGravity = true;
    private boolean onGround = false;
    @Override
    public void Update(){
        if(getParent() !=null){
            if(hasGravity){
                float displacementY = gravity.y;
                if(getParent() instanceof Pawn pawn)
                {
                    onGround = !pawn.Move(new Vector2(0,1), displacementY);
                }
            }
        }
    }
}
