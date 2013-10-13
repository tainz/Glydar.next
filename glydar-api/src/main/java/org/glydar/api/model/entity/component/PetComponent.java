package org.glydar.api.model.entity.component;

public class PetComponent implements Component {

    private long owner;

    public PetComponent(long owner) {
        this.owner = owner;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }
}
