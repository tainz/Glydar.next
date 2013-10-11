package org.glydar.api.plugin.event.events;

import org.glydar.api.model.entity.Entity;

public class EntityFlagsUpdateEvent extends EntityUpdateEvent {
	byte flags1;
	byte flags2;
	
	public EntityFlagsUpdateEvent(Entity entity, byte flags1, byte flags2) {
		super(entity);
		this.flags1 = flags1;
		this.flags2 = flags2;
	}

	public byte getFlags1() {
		return flags1;
	}

	public void setFlags1(byte flags1) {
		this.flags1 = flags1;
	}

	public byte getFlags2() {
		return flags2;
	}

	public void setFlags2(byte flags2) {
		this.flags2 = flags2;
	}

}
