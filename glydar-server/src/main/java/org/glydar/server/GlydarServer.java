package org.glydar.server;

import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

public class GlydarServer extends CoreBackend implements Server {

    private static final String NAME    = "Glydar";
    private static final String VERSION = "dev";

    public GlydarServer() {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public Server getServer() {
        return this;
    }
}
