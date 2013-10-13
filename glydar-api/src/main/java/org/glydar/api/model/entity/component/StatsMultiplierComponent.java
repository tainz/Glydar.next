package org.glydar.api.model.entity.component;

public class StatsMultiplierComponent {

    private float maxHpMultiplier;
    private float shootSpeedMultiplier;
    private float damageMultiplier;
    private float armorMultiplier;
    private float resistanceMultiplier;

    public StatsMultiplierComponent(float maxHpMultiplier, float shootSpeedMultiplier, float damageMultiplier,
            float armorMultiplier, float resistanceMultiplier) {
        this.maxHpMultiplier = maxHpMultiplier;
        this.shootSpeedMultiplier = shootSpeedMultiplier;
        this.damageMultiplier = damageMultiplier;
        this.armorMultiplier = armorMultiplier;
        this.resistanceMultiplier = resistanceMultiplier;
    }

    public float getMaxHpMultiplier() {
        return maxHpMultiplier;
    }

    public void setMaxHpMultiplier(float maxHPMultiplier) {
        this.maxHpMultiplier = maxHPMultiplier;
    }

    public float getShootSpeedMultiplier() {
        return shootSpeedMultiplier;
    }

    public void setShootSpeedMultiplier(float shootSpeedMultiplier) {
        this.shootSpeedMultiplier = shootSpeedMultiplier;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getArmorMultiplier() {
        return armorMultiplier;
    }

    public void setArmorMultiplier(float armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
    }

    public float getResistanceMultiplier() {
        return resistanceMultiplier;
    }

    public void setResistanceMultiplier(float resistanceMultiplier) {
        this.resistanceMultiplier = resistanceMultiplier;
    }

}
