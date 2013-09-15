package org.glydar.api.entity;

public class Entity {

    private static int          NEXT_ENTITY_ID = 2;

    private final long          id;
    private final EntityChanges changes;
    private final EntityData    data;

    public Entity() {
        this.id = NEXT_ENTITY_ID++;
        this.changes = new EntityChanges();
        this.data = new EntityData();
    }

    public long getId() {
        return id;
    }

    public EntityChanges getChanges() {
        return changes;
    }

    public EntityData getData() {
        return data;
    }
}
