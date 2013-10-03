package org.glydar.mitm;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

public class GlydarMitm extends CoreBackend {

    private static final String NAME = "Glydar MITM";
    
    private final GlydarMitmConfig config;

    public GlydarMitm() {
        super(NAME);
        this.config = new GlydarMitmConfig(this);
    }

    @Override
    public BackendType getType() {
        return BackendType.MITM;
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException();
    }
    
    public GlydarMitmConfig getConfig() {
    	return config;
    }
}
