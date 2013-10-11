package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.plugin.event.Event;

public abstract class EntityUpdateEvent extends Event {
	private final Entity entity;
	
	public EntityUpdateEvent (Entity entity) {
		this.entity = entity;
	}
	
	public Entity getEntity () {
		return entity;
	}
}
