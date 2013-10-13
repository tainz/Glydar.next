package org.glydar.core.model.actions;

public class DamageAction {

    private final long damagerId;
    private final long targetId;
    private float damage;

    public DamageAction(long damagerId, long targetId) {
        this.damagerId = damagerId;
        this.targetId = targetId;
        this.damage = 0f;
    }

    public long getDamagerId() {
        return damagerId;
    }

    public long getTargetId() {
        return targetId;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
