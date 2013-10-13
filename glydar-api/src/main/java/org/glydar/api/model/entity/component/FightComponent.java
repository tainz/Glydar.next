package org.glydar.api.model.entity.component;

import org.glydar.api.model.geom.FloatVector3;

public class FightComponent implements Component {

    private byte hostileType;
    private long lastShootTime; // Uint
    private long hitCounter; // Uint
    private long lastHitTime; // Uint
    private int stunTime;
    private FloatVector3 rayHit;

    public byte getHostileType() {
        return hostileType;
    }

    public void setHostileType(byte hostileType) {
        this.hostileType = hostileType;
    }

    public long getLastShootTime() {
        return lastShootTime;
    }

    public void setLastShootTime(long lastShootTime) {
        this.lastShootTime = lastShootTime;
    }

    public long getHitCounter() {
        return hitCounter;
    }

    public void setHitCounter(long hitCounter) {
        this.hitCounter = hitCounter;
    }

    public long getLastHitTime() {
        return lastHitTime;
    }

    public void setLastHitTime(long lastHitTime) {
        this.lastHitTime = lastHitTime;
    }

    public int getStunTime() {
        return stunTime;
    }

    public void setStunTime(int stunTime) {
        this.stunTime = stunTime;
    }

    public FloatVector3 getRayHit() {
        return rayHit;
    }

    public void setRayHit(FloatVector3 rayHit) {
        this.rayHit = rayHit;
    }
}
