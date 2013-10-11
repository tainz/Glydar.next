package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.geom.LongVector3;

public class EntityPositionUpdateEvent extends EntityUpdateEvent {
	private LongVector3 position;
	
	public EntityPositionUpdateEvent (Entity entity, LongVector3 position) {
		super(entity);
		this.position = position;
	}
	
	public LongVector3 getPosition (){
		return position;
	}
	
	public void setPosition (LongVector3 position) {
		this.position = position;
	}
}
