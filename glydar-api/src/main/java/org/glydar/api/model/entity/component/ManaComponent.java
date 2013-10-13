package org.glydar.api.model.entity.component;

public class ManaComponent {

    private float mp;
    private float chargedMp;

    public ManaComponent(float mp, float chargedMp) {
        this.mp = mp;
        this.chargedMp = chargedMp;
    }

    public float getMp() {
        return mp;
    }

    public void setMp(float mp) {
        this.mp = mp;
    }

    public float getChargedMp() {
        return chargedMp;
    }

    public void setChargedMp(float chargedMp) {
        this.chargedMp = chargedMp;
    }
}
