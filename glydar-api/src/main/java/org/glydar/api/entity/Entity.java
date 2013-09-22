package org.glydar.api.entity;

public class Entity {

    private static int       NEXT_ENTITY_ID = 2;

    private final long       id;
    private final EntityData data;

    public Entity() {
        this.id = NEXT_ENTITY_ID++;
        this.data = new EntityData();
    }

    public long getId() {
        return id;
    }

    public EntityData getData() {
        return data;
    }
}
