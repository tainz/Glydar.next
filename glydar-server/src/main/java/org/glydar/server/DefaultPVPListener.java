package org.glydar.server;

import org.glydar.api.plugin.event.EventExecutor;
import org.glydar.api.plugin.event.events.EntityFlagsUpdateEvent;

public class DefaultPVPListener implements EventExecutor<EntityFlagsUpdateEvent> {

	@Override
	public void execute(EntityFlagsUpdateEvent event) {
		if (event.getEntity().getWorld().isPvpAllowed()) {
			event.setFlags1((byte) 32);
		}
	
	}

}
