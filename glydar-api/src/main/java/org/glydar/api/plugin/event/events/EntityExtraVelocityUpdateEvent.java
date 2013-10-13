package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.geom.FloatVector3;

public class EntityExtraVelocityUpdateEvent extends EntityUpdateEvent {
    FloatVector3 extraVelocity;

    public EntityExtraVelocityUpdateEvent(Entity entity, FloatVector3 extraVelocity) {
        super(entity);
        this.extraVelocity = extraVelocity;
    }

    public FloatVector3 getExtraVelocity() {
        return extraVelocity;
    }

    public void setExtraVelocity(FloatVector3 extraVelocity) {
        this.extraVelocity = extraVelocity;
    }

}
