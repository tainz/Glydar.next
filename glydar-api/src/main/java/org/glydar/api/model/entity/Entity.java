package org.glydar.api.model.entity;

import org.glydar.api.model.world.World;

public interface Entity {

	long getId();
	
    EntityData getData();

    World getWorld();
}
