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
    private final MitmServer mitmServer;
    private final MitmClient mitmClient;
    private final VanillaServer vanillaServer;
    private final Set<Relay> relays;

    public GlydarMitm() {
        super(NAME);
        this.config = new GlydarMitmConfig(this);
        this.mitmServer = new MitmServer(this);
        this.mitmClient = new MitmClient(this);

        if (config.isVanillaAutomatic()) {
            vanillaServer = new VanillaServer(this);
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

    @Override
    public void shutdown() {
        GlydarMitmMain.shutdown();
    }

    public GlydarMitmConfig getConfig() {
        return config;
    }

    public MitmServer getMitmServer() {
        return mitmServer;
    }

    public MitmClient getMitmClient() {
        return mitmClient;
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
