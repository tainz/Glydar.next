package org.glydar.mitm;

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
}
