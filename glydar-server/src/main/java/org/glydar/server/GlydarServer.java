package org.glydar.server;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

public class GlydarServer extends CoreBackend implements Server {

    private static final String NAME = "Glydar";

    public GlydarServer() {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public BackendType getType() {
        return BackendType.SERVER;
    }

    @Override
    public Server getServer() {
        return this;
    }
}
