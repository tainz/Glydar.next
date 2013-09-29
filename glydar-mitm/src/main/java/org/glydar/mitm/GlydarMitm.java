package org.glydar.mitm;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

public class GlydarMitm extends CoreBackend {

    private static final String NAME    = "Glydar MITM";
    private static final String VERSION = "dev";

    public GlydarMitm() {
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
    public BackendType getType() {
        return BackendType.MITM;
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException();
    }
}
