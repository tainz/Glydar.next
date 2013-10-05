package org.glydar.mitm;

import java.util.Set;

import org.glydar.api.BackendType;
import org.glydar.api.Glydar;
import org.glydar.api.Server;
import org.glydar.core.CoreBackend;

import com.google.common.collect.Sets;

public class GlydarMitm extends CoreBackend {

    private static final String NAME = "Glydar MITM";
    
    private final GlydarMitmConfig config;
    private final VanillaServer vanillaServer;
    private final Set<Relay> relays;

    public GlydarMitm() {
        super(NAME);
        this.config = new GlydarMitmConfig(this);

        if (config.isVanillaAutomatic()) {
            vanillaServer = new VanillaServer(config);
            vanillaServer.startServer();
        }
        else {
            vanillaServer = null;
        }

        this.relays = Sets.newIdentityHashSet();
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

    public VanillaServer getVanillaServer() {
        return vanillaServer;
    }

    public Set<Relay> getRelays() {
        return relays;
    }

    public static GlydarMitm getInstance() {
        return (GlydarMitm) Glydar.getBackend();
    }
}
