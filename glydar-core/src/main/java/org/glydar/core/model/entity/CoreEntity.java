package org.glydar.core.model.entity;

import org.glydar.api.model.entity.Entity;

public class CoreEntity implements Entity {

    private static int           NEXT_ENTITY_ID = 2;

    private final int            id;
    private final CoreEntityData data;

    public CoreEntity() {
        this.id = NEXT_ENTITY_ID++;
        this.data = new CoreEntityData(new EntityChanges());
    }

    public int getId() {
        return id;
    }

    @Override
    public CoreEntityData getData() {
        return data;
    }
}
