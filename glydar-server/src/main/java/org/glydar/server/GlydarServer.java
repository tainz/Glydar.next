package org.glydar.server;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

public class GlydarServer extends CoreBackend implements Server {

    private static final String      NAME = "Glydar";

    private final GlydarServerConfig config;

    public GlydarServer() {
        super(NAME);

        this.config = new GlydarServerConfig(this);
    }

    @Override
    public BackendType getType() {
        return BackendType.SERVER;
    }

    public GlydarServerConfig getConfig() {
        return config;
    }

    @Override
    public GlydarServer getServer() {
        return this;
    }
}
