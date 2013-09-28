package org.glydar.server;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;

public class GlydarServer implements Backend {

    private static final String NAME    = "Glydar";
    private static final String VERSION = "dev";

    private final GlydarLogger  logger;

    public GlydarServer() {
        this.logger = GlydarLogger.of(this, NAME);
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
    public GlydarLogger getLogger() {
        return logger;
    }
}
