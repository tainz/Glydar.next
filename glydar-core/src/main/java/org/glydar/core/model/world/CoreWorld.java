package org.glydar.core.model.world;

import org.glydar.api.model.world.World;

public class CoreWorld implements World {

    private final String name;
    private final int    seed;
    private boolean      pvpAllowed;

    public CoreWorld(String name, int seed) {
        this.name = name;
        this.seed = seed;
        this.pvpAllowed = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSeed() {
        return seed;
    }

    @Override
    public boolean isPvpAllowed() {
        return pvpAllowed;
    }

    @Override
    public void setPvpAllowed(boolean pvpAllowed) {
        this.pvpAllowed = pvpAllowed;
        // TODO: Update player false accordingly
    }
}
