package org.glydar.core.model.entity;

public class Entity {

    private static int       NEXT_ENTITY_ID = 2;

    private final long       id;
    private final EntityData data;

    public Entity() {
        this.id = NEXT_ENTITY_ID++;
        this.data = new EntityData(new EntityChanges());
    }

    public long getId() {
        return id;
    }

    public EntityData getData() {
        return data;
    }
}
