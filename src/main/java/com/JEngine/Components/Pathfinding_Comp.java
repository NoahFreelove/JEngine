package com.JEngine.Components;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Utility.GameMath;

public class Pathfinding_Comp extends Component {
    private GameObject target;
    private float moveSpeed = 5;

    public Pathfinding_Comp(GameObject target) {
        super("Pathfinding");
        this.target = target;
    }

    public GameObject getTarget() {
        return target;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void GenerateMoveDirection(){
        Vector3 delta = target.getPosition().subtract(getParent().getPosition());
        if(getParent() instanceof Pawn pawn)
        {
            if(delta.x <5 && delta.x > -5)
            {
                delta.x = 0;
            }
            if (delta.y < 5 && delta.y > -5)
            {
                delta.y = 0;
            }
            for (Component c: getParent().getComponentByType(PhysicsBody_Comp.class)){
                ((PhysicsBody_Comp)c).addVelocity(new Vector2(GameMath.clamp(-1,1,delta.x*moveSpeed), GameMath.clamp(-1,1,delta.y*moveSpeed)));
                return;
            }
            pawn.Move(new Vector2(delta.x, delta.y), moveSpeed);
        }
    }

    @Override
    public void Update(){
        GenerateMoveDirection();
    }
}
