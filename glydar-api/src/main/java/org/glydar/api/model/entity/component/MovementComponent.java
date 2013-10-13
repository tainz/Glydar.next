package org.glydar.api.model.entity.component;

import org.glydar.api.model.geom.FloatVector3;

public class MovementComponent implements Component {

    private FloatVector3 velocity;
    private FloatVector3 acceleration;
    private FloatVector3 extraVelocity;
    private long physicsFlags;
    private long rollTime; // Uint
    private long slowedTime; // Uint
    private long speedUpTime; // Uint
    private float slowPatchTime;

    public MovementComponent(FloatVector3 velocity, FloatVector3 acceleration, FloatVector3 extraVelocity) {
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.extraVelocity = extraVelocity;
    }

    public FloatVector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(FloatVector3 velocity) {
        this.velocity = velocity;
    }

    public FloatVector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(FloatVector3 acceleration) {
        this.acceleration = acceleration;
    }

    public FloatVector3 getExtraVelocity() {
        return extraVelocity;
    }

    public void setExtraVelocity(FloatVector3 extraVelocity) {
        this.extraVelocity = extraVelocity;
    }

    public long getPhysicsFlags() {
        return physicsFlags;
    }

    public void setPhysicsFlags(long physicsFlags) {
        this.physicsFlags = physicsFlags;
    }

    public long getRollTime() {
        return rollTime;
    }

    public void setRollTime(long rollTime) {
        this.rollTime = rollTime;
    }

    public long getSlowedTime() {
        return slowedTime;
    }

    public void setSlowedTime(long slowedTime) {
        this.slowedTime = slowedTime;
    }

    public long getSpeedUpTime() {
        return speedUpTime;
    }

    public void setSpeedUpTime(long speedUpTime) {
        this.speedUpTime = speedUpTime;
    }

    public float getSlowPatchTime() {
        return slowPatchTime;
    }

    public void setSlowPatchTime(float slowPatchTime) {
        this.slowPatchTime = slowPatchTime;
    }
}
