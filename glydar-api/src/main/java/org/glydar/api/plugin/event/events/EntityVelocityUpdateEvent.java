package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.geom.FloatVector3;

public class EntityVelocityUpdateEvent extends EntityUpdateEvent {
	FloatVector3 velocity;

	public EntityVelocityUpdateEvent(Entity entity, FloatVector3 velocity) {
		super(entity);
		this.velocity = velocity;
	}
	
	public FloatVector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(FloatVector3 velocity) {
		this.velocity = velocity;
	}

}
