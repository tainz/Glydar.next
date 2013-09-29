package org.glydar.core.model.entity;

import org.glydar.api.model.entity.Entity;
import org.glydar.core.model.world.CoreWorld;

public class CoreEntity implements Entity {

    private static int             NEXT_ENTITY_ID = 2;

    private final int              id;
    protected final CoreEntityData data;
    private CoreWorld              world;

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

    @Override
    public CoreWorld getWorld() {
        return world;
    }

    public void initWorld(CoreWorld world) {
        this.world = world;
    }
}
