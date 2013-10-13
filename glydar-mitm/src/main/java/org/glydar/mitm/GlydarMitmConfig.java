package org.glydar.mitm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.glydar.api.plugin.configuration.file.YamlConfiguration;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class GlydarMitmConfig {

    private static final String VANILLA_AUTOMATIC_KEY = "settings.automatic-vanilla";
    private static final boolean VANILLA_AUTOMATIC_DEFAULT = false;
    private static final String VANILLA_PATH_KEY = "settings.vanilla-path";
    private static final String VANILLA_PATH_DEFAULT = "vanilla/Server.exe";
    private static final String MITM_PORT_KEY = "settings.mitm-port";
    private static final String MITM_PORT_SYSTEM_KEY = "settings.mitm-port";
    private static final int MITM_PORT_DEFAULT = 12345;
    private static final String VANILLA_HOST_KEY = "settings.vanilla-host";
    private static final String VANILLA_HOST_SYSTEM_KEY = "glydar.host.vanilla";
    private static final String VANILLA_HOST_DEFAULT = "localhost";
    private static final String VANILLA_PORT_KEY = "settings.vanilla-port";
    private static final String VANILLA_PORT_SYSTEM_KEY = "glydar.port.vanilla";
    private static final int VANILLA_PORT_DEFAULT = 12346;
    private static final String DEBUG_KEY = "settings.debug";
    private static final String DEBUG_SYSTEM_KEY = "glydar.debug";
    private static final boolean DEBUG_DEFAULT = false;

    private static final String MAX_PLAYERS_KEY = "mitm.max-players";
    private static final int MAX_PLAYERS_DEFAULT = 4;

    private static final String ADMINS_KEY = "mitm.admins";
    private static final List<String> ADMINS_DEFAULT = new ArrayList<>();

    private final GlydarMitm server;
    private final YamlConfiguration config;
    private final boolean debug;
    private final String vanillaHost;
    private final int vanillaPort;
    private final int mitmPort;

    public GlydarMitmConfig(GlydarMitm server) {
        this.server = server;
        this.config = YamlConfiguration.loadConfiguration(server.getConfigFile().toFile());

        config.options().copyDefaults(true);

        config.addDefault(VANILLA_AUTOMATIC_KEY, VANILLA_AUTOMATIC_DEFAULT);
        config.addDefault(VANILLA_PATH_KEY, VANILLA_PATH_DEFAULT);
        config.addDefault(MITM_PORT_KEY, MITM_PORT_DEFAULT);
        config.addDefault(VANILLA_HOST_KEY, VANILLA_HOST_DEFAULT);
        config.addDefault(VANILLA_PORT_KEY, VANILLA_PORT_DEFAULT);
        config.addDefault(DEBUG_KEY, DEBUG_DEFAULT);
        config.addDefault(MAX_PLAYERS_KEY, MAX_PLAYERS_DEFAULT);
        config.addDefault(ADMINS_KEY, ADMINS_DEFAULT);

        save();

        String debugProperty = System.getProperty(DEBUG_SYSTEM_KEY);
        if (debugProperty == null) {
            this.debug = config.getBoolean(DEBUG_KEY);
        }
        else {
            this.debug = debugProperty.equals("true");
        }

        String mitmPortProperty = System.getProperty(MITM_PORT_SYSTEM_KEY);
        if (mitmPortProperty == null) {
            this.mitmPort = config.getInt(MITM_PORT_KEY);
        }
        else {
            this.mitmPort = tryParseInt(mitmPortProperty, config.getInt(MITM_PORT_KEY));
        }

        String vanillaHostProperty = System.getProperty(VANILLA_HOST_SYSTEM_KEY);
        if (vanillaHostProperty == null) {
            this.vanillaHost = config.getString(VANILLA_HOST_KEY);
        }
        else {
            this.vanillaHost = vanillaHostProperty;
        }

        String vanillaPortProperty = System.getProperty(VANILLA_PORT_SYSTEM_KEY);
        if (vanillaPortProperty == null) {
            this.vanillaPort = config.getInt(VANILLA_PORT_KEY);
        }
        else {
            this.vanillaPort = tryParseInt(vanillaPortProperty, config.getInt(VANILLA_PORT_KEY));
        }
    }

    private int tryParseInt(String string, int fallback) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException exc) {
            return fallback;
        }
    }

    private void save() {
        try {
            config.save(server.getConfigFile().toFile());
        }
        catch (IOException exc) {
            server.getLogger().warning(exc, "Error while trying to save config file");
        }
    }

    public Set<String> getAdmins() {
        return ImmutableSet.copyOf(config.getStringList(ADMINS_KEY));
    }

    public boolean addAdmin(String name) {
        Set<String> admins = Sets.newHashSet(config.getStringList(ADMINS_KEY));
        boolean added = admins.add(name);
        if (added) {
            config.set(ADMINS_KEY, new ArrayList<>(admins));
            save();
        }

        return added;
    }

    public boolean removeAdmin(String name) {
        Set<String> admins = Sets.newHashSet(config.getStringList(ADMINS_KEY));
        boolean removed = admins.remove(name);
        if (removed) {
            config.set(ADMINS_KEY, new ArrayList<>(admins));
            save();
        }

        return removed;
    }

    public String getVanillaHost() {
        return vanillaHost;
    }

    public int getVanillaPort() {
        return vanillaPort;
    }

    public int getMitmPort() {
        return mitmPort;
    }

    public boolean isVanillaAutomatic() {
        return config.getBoolean(VANILLA_AUTOMATIC_KEY);
    }

    public String getVanillaPath() {
        return config.getString(VANILLA_PATH_KEY);
    }

    public boolean isDebug() {
        return debug;
    }

    public int getMaxPlayers() {
        return config.getInt(MAX_PLAYERS_KEY);
    }
}
