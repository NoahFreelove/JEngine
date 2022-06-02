package com.JEngine.Components;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GenericMethod;

public class Pathfinding_Comp extends Component {
    private GameObject target;
    private float moveSpeed = 1f;

    private GenericMethod onTargetReachedEvent;
    private long timeWhenStarted;
    // The range to the target in which the pathfinding is considered to be successful
    // Raise this value to leave more wiggle room
    private float successRange = 50f;

    private boolean moveAfterSuccess = true;

    private boolean isSuccessful = false;
    public Pathfinding_Comp(GameObject target) {
        super("Pathfinding");
        this.target = target;
    }

    public GameObject getTarget() {
        return target;
    }



    public void setTarget(GameObject target) {
        timeWhenStarted = System.currentTimeMillis();
        this.target = target;
    }
    public void setTarget(Vector3 target) {
        timeWhenStarted = System.currentTimeMillis();
        this.target = new GameObject(Transform.simpleTransform(target.x, target.y, target.z), new Identity("target"));
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void GenerateMoveDirection(){
        if(target == null)
            return;
        if(!moveAfterSuccess && isSuccessful)
        {
            CheckSuccess();
            return;
        }
        ApplyMovement(target.getPosition().subtract(getParent().getPosition()));
    }

    private void ApplyMovement(Vector3 delta){
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
                if(c instanceof PhysicsBody_Comp phy)
                {
                    phy.addVelocity(new Vector2(GameMath.clamp(-1,1,delta.x)*moveSpeed, GameMath.clamp(-1,1,delta.y)*moveSpeed));
                }
                CheckSuccess();
                return;
            }
            pawn.Move(new Vector2(delta.x, delta.y), moveSpeed);
            CheckSuccess();
        }
    }
    private void CheckSuccess(){
        // Subtract the target position and current position. if less than success range, the pathfinding was successful

        Vector3 distanceToTarget = GameMath.abs(target.getPosition().subtract(getParent().getPosition()));

        if(distanceToTarget.x<successRange && distanceToTarget.y < successRange)
        {
            if(onTargetReachedEvent !=null)
            {
                onTargetReachedEvent.call(new Object[]{System.currentTimeMillis()-timeWhenStarted});
            }
            isSuccessful = true;
        }
        else {
            isSuccessful = false;
        }
    }

    public float getSuccessRange() {
        return successRange;
    }

    public void setSuccessRange(float successRange) {
        this.successRange = successRange;
    }

    public void setOnTargetReachedEvent(GenericMethod onTargetReachedEvent) {
        this.onTargetReachedEvent = onTargetReachedEvent;
    }

    public void setMoveAfterSuccess(boolean moveAfterSuccess) {
        this.moveAfterSuccess = moveAfterSuccess;
    }

    @Override
    public void Update(){
        GenerateMoveDirection();
    }
}
