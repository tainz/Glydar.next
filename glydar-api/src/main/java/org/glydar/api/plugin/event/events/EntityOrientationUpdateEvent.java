package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.geom.Orientation;

public class EntityOrientationUpdateEvent extends EntityUpdateEvent {
	Orientation orientation;
	
	public EntityOrientationUpdateEvent(Entity entity, Orientation orientation) {
		super(entity);
		this.orientation = orientation;
	}

	public Orientation getOrientation () {
		return orientation;
	}
	
	public void setOrientation (Orientation orientation) {
		this.orientation = orientation;
	}
}
