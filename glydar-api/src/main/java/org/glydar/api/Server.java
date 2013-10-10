package org.glydar.api;

import org.glydar.api.model.entity.Entity;

public interface Server {
	
	public Entity getEntityById(long id);
	
	//TODO: Better place to put these!!
	public void unregisterEntity(long entityId);
	
	public void registerEntity(Entity e);
}
