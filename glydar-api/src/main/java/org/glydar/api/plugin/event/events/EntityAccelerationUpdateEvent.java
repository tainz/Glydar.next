package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.geom.FloatVector3;

public class EntityAccelerationUpdateEvent extends EntityUpdateEvent {
    FloatVector3 acceleration;

    public EntityAccelerationUpdateEvent(Entity entity, FloatVector3 acceleration) {
        super(entity);
        this.acceleration = acceleration;
    }

    public FloatVector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(FloatVector3 acceleration) {
        this.acceleration = acceleration;
    }

}
