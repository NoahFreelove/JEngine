package com.JEngine.Components;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.Component;
import com.JEngine.PrimitiveTypes.Position.Vector2;

public class PhysicsComponent extends Component {
    private float mass;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 friction;
    private final Vector2 gravity;
    private boolean hasGravity = true;
    private boolean onGround = false;

    @Override
    public void Update(){
        // multiply by delta time for smooth movement and not instant teleporting
        double deltaTime = SceneManager.getWindow().getDeltaTime()/1000d;
        // Speed up based on acceleration
        velocity = velocity.add(acceleration.multiply(deltaTime));

        // Slow down based on friction
        velocity.multiply(new Vector2(1,1).subtract(friction.multiply(deltaTime)));

        if(getParent() ==null) return;

        if(getParent() instanceof Pawn pawn)
        {
            // move in directions separately by delta time
            System.out.println(velocity);
            onGround = !pawn.Move(new Vector2(0,1), velocity.y);

            // These statements make it so if you run into a wall you don't infinitely accelerate
            if(onGround) velocity.y = 0;
            if(!pawn.Move(new Vector2(1,0),velocity.x)) velocity.x = 0;
        }

    }
    public PhysicsComponent(boolean hasGravity){
        super(true, "PhysicsComponent");
        this.hasGravity = hasGravity;
        this.gravity =  new Vector2(0f,5f);
        this.velocity = new Vector2(0,0);
        this.acceleration = new Vector2(5,0);
        this.friction = new Vector2(0,0);
        if(hasGravity)
            velocity = new Vector2(gravity.x, gravity.y);
    }
    public PhysicsComponent(Vector2 gravity)
    {
        super(true, "PhysicsComponent");
        this.gravity = gravity;
    }


    public float getMass() {
        return mass;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Vector2 getFriction() {
        return friction;
    }

    public Vector2 getGravity() {
        return gravity;
    }

    public boolean isHasGravity() {
        return hasGravity;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setHasGravity(boolean hasGravity) {
        if(hasGravity == this.hasGravity)
            return;
        if(hasGravity)
            velocity = velocity.add(gravity);
        else
            velocity = velocity.subtract(gravity);

        this.hasGravity = hasGravity;
    }
}
