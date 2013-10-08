package org.glydar.core.model.entity;

import org.glydar.api.Glydar;
import org.glydar.api.model.entity.Entity;
import org.glydar.core.model.world.CoreWorld;

public abstract class CoreEntity implements Entity {

    private static int             NEXT_ENTITY_ID = 2;

    protected final int              id;
    protected final CoreEntityData data;
    protected CoreWorld              world;

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

    public void joinWorld(CoreWorld world) {
    	if (world != null) {
    		world.unregisterEntity(id);
    	}
        this.world = world;
        world.registerEntity(this);
        
    }
    
    public void remove(){
    	Glydar.getServer().unregisterEntity(id);
    	world.unregisterEntity(id);
    }
}
