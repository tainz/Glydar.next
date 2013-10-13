package org.glydar.api;

import java.util.List;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.world.World;

public interface Server {

    List<World> getWorlds();

    public Entity getEntityById(long id);

    // TODO: Better place to put these!!
    public void unregisterEntity(long entityId);

    public void registerEntity(Entity e);
}
