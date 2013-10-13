package org.glydar.core.model.entity;

import org.glydar.api.Glydar;
import org.glydar.api.model.entity.Entity;
import org.glydar.core.model.world.CoreWorld;
import org.glydar.core.util.IdPool;

public abstract class CoreEntity implements Entity {

    private static final IdPool ID_POOL = new IdPool();

    protected final long id;
    protected final CoreEntityData data;
    protected CoreWorld world;

    public CoreEntity() {
        this.id = ID_POOL.pop();
        this.data = new CoreEntityData(new EntityChanges());
    }

    @Override
    public long getId() {
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

    public void joinWorld(CoreWorld world) {
        if (world != null) {
            world.unregisterEntity(id);
        }
        this.world = world;
        world.registerEntity(this);

        if (world.isPvpAllowed()) {
            data.setFlags1((byte) 32);
        }
    }

    public void remove() {
        Glydar.getServer().unregisterEntity(id);
        world.unregisterEntity(id);
    }
}
