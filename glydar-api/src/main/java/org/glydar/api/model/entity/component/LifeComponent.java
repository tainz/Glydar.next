package org.glydar.api.model.entity.component;

public class LifeComponent implements Component {

    private float hp;

    public LifeComponent(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
