package org.glydar.api.model.world;

public interface World {

    String getName();

    int getSeed();

    boolean isPvpAllowed();

    void setPvpAllowed(boolean pvpAllowed);
}
