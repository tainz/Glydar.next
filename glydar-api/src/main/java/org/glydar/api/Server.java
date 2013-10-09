package org.glydar.api;

import org.glydar.api.model.entity.Entity;

public interface Server {
	
	//TODO: Better place to put these!!
	public void unregisterEntity(long entityId);
	
	public void registerEntity(Entity e);
}
