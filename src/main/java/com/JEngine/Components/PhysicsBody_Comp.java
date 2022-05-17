package com.JEngine.Components;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Core.Component;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Utility.GameMath;

public class PhysicsBody_Comp extends Component {

    // Vectors
    private Vector2 velocity; // in m/s
    private Vector2 acceleration; // in m/s^2
    private Vector2 friction; // in that fancy u symbol
    private Vector2 gravity; // in m/s^2

    // Restrictions
    private boolean hasGravity = true; // should gravity be applied?
    private boolean onGround = false; // is the pawn on the ground?
    private boolean frictionInAir = true; // should friction be applied in air?
    private boolean allowAddAccelerationInAir = true; // should acceleration be applied in air?
    private boolean allowAddVelocityInAir = true; // should velocity be applied in air?

    // Limits
    private Vector2 maxVelocity; // in m/s
    private Vector2 maxAcceleration; // in m/s^2

    // Directions
    private final Vector2 up = new Vector2(0,-1);
    private final Vector2 right = new Vector2(1,0);
    private final Vector2 down = new Vector2(0,1);
    private final Vector2 left = new Vector2(-1,0);
    private Vector2 currDirection = new Vector2(0,0);

    private boolean removedForceBecauseOnGround = false;

    @Override
    public void Update(){
        calculatePhysics();
        applyPhysics();
    }

    public PhysicsBody_Comp(boolean hasGravity, Vector2 initGravity){
        super(true, "PhysicsComponent");
        this.hasGravity = hasGravity;
        this.gravity = initGravity;
        if(initGravity == null)
            this.gravity = new Vector2(0,5f); // 5 is a pretty good value. 9.8 is a little harsh
        this.velocity = new Vector2(0,0);
        this.acceleration = new Vector2(0,0);
        this.friction = new Vector2(0.2f,0);
        this.maxVelocity = new Vector2(1000,1000);
        this.maxAcceleration = new Vector2(1000,1000);
        if(hasGravity)
            velocity = new Vector2(gravity.x, gravity.y);
    }

    public PhysicsBody_Comp(Vector2 gravity)
    {
        super(true, "PhysicsComponent");
        this.gravity = gravity;
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
        this.hasGravity = hasGravity;
    }

    public void addVelocity(Vector2 velocity)
    {
        //System.out.println("Adding velocity:"+velocity);
        if(allowAddVelocityInAir || onGround)
            this.velocity = this.velocity.add(velocity);
        //System.out.println("New velocity:"+this.velocity);
    }

    public void addAcceleration(Vector2 acceleration)
    {
        //System.out.println("Adding acceleration:"+acceleration);
        if(allowAddAccelerationInAir || onGround)
            this.acceleration = this.acceleration.add(acceleration);
        //System.out.println("New acceleration:"+this.acceleration);
    }

    public Vector2 addFriction(Vector2 friction)
    {
        this.friction = this.friction.add(friction);
        return this.friction;
    }

    public Vector2 addGravity(Vector2 gravity)
    {
        this.gravity = this.gravity.add(gravity);
        return this.gravity;
    }

    public Vector2 removeAcceleration(Vector2 acceleration)
    {
        Vector2 subtracted = this.acceleration.subtract(acceleration);
        this.acceleration = new Vector2(GameMath.clamp(0, 1, subtracted.x), GameMath.clamp(0, 1, subtracted.y));
        return this.acceleration;
    }
    
    public Vector2 removeVelocity(Vector2 velocity)
    {
        Vector2 subtracted = this.velocity.subtract(velocity);
        this.velocity = new Vector2(subtracted.x, subtracted.y);
        return this.velocity;        
    }

    public void setFriction(Vector2 friction) {
        this.friction = friction;
    }

    public void setGravity(Vector2 gravity) {
        this.gravity = gravity;
    }

    public boolean isFrictionInAir() {
        return frictionInAir;
    }

    public boolean isAllowAddAccelerationInAir() {
        return allowAddAccelerationInAir;
    }

    public boolean isAllowAddVelocityInAir() {
        return allowAddVelocityInAir;
    }

    public Vector2 getMaxVelocity() {
        return maxVelocity;
    }

    public Vector2 getMaxAcceleration() {
        return maxAcceleration;
    }

    // Called after physics calculations are done
    private void Unstick(Pawn p){
        if(p.isCollidingWithAny())
        {
            velocity = Vector2.inverse(currDirection).multiply(10);
            acceleration = new Vector2(0,0);
        }
    }

    private void calculatePhysics(){
        // multiply by delta time for smooth movement and not instant teleporting
        double deltaTime = SceneManager.getWindow().getDeltaTime()/1000d;
        // Speed up based on acceleration

        // add gravity if applicable
        if(hasGravity)
        {
            acceleration = acceleration.add(gravity);
        }

        // Slow down based on friction
        velocity = velocity.add(acceleration.multiply(deltaTime));

        if(onGround || frictionInAir)
        {
            velocity = velocity.multiply(new Vector2(1-friction.x,1-friction.y));
        }

        velocity = GameMath.clamp(maxVelocity.multiply(-1), maxVelocity, velocity);
        acceleration = GameMath.clamp(maxAcceleration.multiply(-1), maxAcceleration, acceleration);
    }

    private void applyPhysics() {
        if(getParent() ==null) return;
        if(!getActive())
            return;

        if(getParent() instanceof Pawn pawn) {
            // move in directions separately by delta time
            int result = pawn.Move(down, velocity.y);
            onGround = !(result == 1 || result == 2);

            if(!onGround)
            {
                removedForceBecauseOnGround = false;
            }
            currDirection = new Vector2(GameMath.clamp(-1, 1, velocity.x), GameMath.clamp(-1, 1, velocity.y));

            // These statements make it so if you run into a wall you don't infinitely accelerate
            if((onGround || result == 2)) {
                acceleration.y = 0;
                if(!removedForceBecauseOnGround)
                {
                    removedForceBecauseOnGround = true;
                    velocity.y = 0;
                }
            }

            if(pawn.Move(right,velocity.x) == 0){
                velocity.x = 0;
                acceleration.x = 0;
            }

            Unstick(pawn);
        }
    }

    public void setFrictionInAir(boolean frictionInAir) {
        this.frictionInAir = frictionInAir;
    }

    public void setAllowAddAccelerationInAir(boolean allowAddAccelerationInAir) {
        this.allowAddAccelerationInAir = allowAddAccelerationInAir;
    }

    public void setAllowAddVelocityInAir(boolean allowAddVelocityInAir) {
        this.allowAddVelocityInAir = allowAddVelocityInAir;
    }

    public static Vector2 defaultGravity(){
        return new Vector2(0,5f);
    }

    public void setOnGround(boolean newValue) {
        this.onGround = newValue;
    }

    public boolean isRemovedForceBecauseOnGround() {
        return removedForceBecauseOnGround;
    }
}
