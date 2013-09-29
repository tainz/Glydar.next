package org.glydar.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.glydar.api.model.world.World;
import org.glydar.api.plugin.configuration.ConfigurationSection;
import org.glydar.api.plugin.configuration.file.YamlConfiguration;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class GlydarServerConfig {

    private static final String       DEBUG_KEY           = "settings.debug";
    private static final boolean      DEBUG_DEFAULT       = false;
    private static final String       PORT_KEY            = "settings.port";
    private static final int          PORT_DEFAULT        = 12345;
    private static final String       FPS_KEY             = "settings.fps";
    private static final int          FPS_DEFAULT         = 50;

    private static final String       MAX_PLAYERS_KEY     = "server.max-players";
    private static final int          MAX_PLAYERS_DEFAULT = 4;

    private static final String       ADMINS_KEY          = "admins";
    private static final List<String> ADMINS_DEFAULT      = new ArrayList<>();

    private static final String       WORLDS_SECTION_KEY  = "worlds";
    private static final String       DEFAULT_WORLD_KEY   = "default";
    private static final String       WORLD_NAME_KEY      = "worlds.default.name";
    private static final String       WORLD_NAME_DEFAULT  = "Default";
    private static final String       WORLD_SEED_KEY      = "worlds.default.seed";
    private static final int          WORLD_SEED_DEFAULT  = 111;
    private static final String       WORLD_PVP_KEY       = "worlds.default.pvp";
    private static final boolean      WORLD_PVP_DEFAULT   = false;

    private final GlydarServer        server;
    private final YamlConfiguration   config;
    private final boolean             debug;
    private final int                 port;
    private final int                 fps;

    public GlydarServerConfig(GlydarServer server) {
        this.server = server;
        this.config = YamlConfiguration.loadConfiguration(server.getConfigFile().toFile());

        config.options().copyDefaults(true);

        config.addDefault(DEBUG_KEY, DEBUG_DEFAULT);
        config.addDefault(PORT_KEY, PORT_DEFAULT);
        config.addDefault(FPS_KEY, FPS_DEFAULT);
        config.addDefault(MAX_PLAYERS_KEY, MAX_PLAYERS_DEFAULT);
        config.addDefault(ADMINS_KEY, ADMINS_DEFAULT);
        config.addDefault(WORLD_NAME_KEY, WORLD_NAME_DEFAULT);
        config.addDefault(WORLD_SEED_KEY, WORLD_SEED_DEFAULT);
        config.addDefault(WORLD_PVP_KEY, WORLD_PVP_DEFAULT);

        save();

        String debugProperty = System.getProperty("glydar.debug");
        if (debugProperty == null) {
            this.debug = config.getBoolean(DEBUG_KEY);
        }
        else {
            this.debug = debugProperty.equals("true");
        }

        String portProperty = System.getProperty("glydar.port");
        if (portProperty == null) {
            this.port = config.getInt(PORT_KEY);
        }
        else {
            this.port = Integer.parseInt(portProperty, config.getInt(PORT_KEY));
        }

        String fpsProperty = System.getProperty("glydar.fps");
        if (fpsProperty == null) {
            this.fps = config.getInt(FPS_KEY);
        }
        else {
            this.fps = tryParseInt(fpsProperty, config.getInt(FPS_KEY));
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

    public boolean isDebug() {
        return debug;
    }

    public int getPort() {
        return port;
    }

    public int getFPS() {
        return fps;
    }

    public int getMaxPlayers() {
        return config.getInt(MAX_PLAYERS_KEY);
    }

    public void setMaxPlayers(int maxPlayers) {
        config.set(MAX_PLAYERS_KEY, maxPlayers);
        save();
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

    public WorldConfig getDefaultWorldConfig() {
        ConfigurationSection worldsSection = config.getConfigurationSection(WORLDS_SECTION_KEY);
        ConfigurationSection defaultSection = worldsSection.getConfigurationSection(DEFAULT_WORLD_KEY);
        return new WorldConfig(defaultSection);
    }

    public List<WorldConfig> getAllWorldsConfigs() {
        ImmutableList.Builder<WorldConfig> builder = ImmutableList.builder();
        builder.add(getDefaultWorldConfig());
        addOtherWorlds(builder);
        return builder.build();
    }

    public List<WorldConfig> getOtherWorldsConfigs() {
        ImmutableList.Builder<WorldConfig> builder = ImmutableList.builder();
        addOtherWorlds(builder);
        return builder.build();
    }

    private void addOtherWorlds(ImmutableList.Builder<WorldConfig> builder) {
        ConfigurationSection worldsSection = config.getConfigurationSection(WORLDS_SECTION_KEY);
        for (String worldKey : worldsSection.getKeys(false)) {
            if (worldKey.equals(DEFAULT_WORLD_KEY)) {
                continue;
            }
            WorldConfig worldConfig = new WorldConfig(worldsSection.getConfigurationSection(worldKey));
            builder.add(worldConfig);
        }
    }

    public void persistWorld(World world) {
        addWorldConfig(new WorldConfig(world));
    }

    private void addWorldConfig(WorldConfig worldConfig) {
        doAddWorldConfig(worldConfig);
        save();
    }

    private void doAddWorldConfig(WorldConfig worldConfig) {
        ConfigurationSection worldsSection = config.getConfigurationSection(WORLDS_SECTION_KEY);
        if (worldsSection.contains(worldConfig.getName())) {
            throw new IllegalArgumentException("World already exists in config");
        }

        ConfigurationSection section = worldsSection.createSection(worldConfig.getName());
        section.set(WORLD_NAME_KEY, worldConfig.getName());
        section.set(WORLD_SEED_KEY, worldConfig.getSeed());
        section.set(WORLD_PVP_KEY, worldConfig.isPvpAllowed());
    }

    class WorldConfig {

        private final String  name;
        private final int     seed;
        private final boolean pvpAllowed;

        public WorldConfig(ConfigurationSection section) {
            this.name = section.getString(WORLD_NAME_KEY);
            this.seed = section.getInt(WORLD_SEED_KEY);
            this.pvpAllowed = section.getBoolean(WORLD_SEED_KEY);
        }

        public WorldConfig(World world) {
            this.name = world.getName();
            this.seed = world.getSeed();
            this.pvpAllowed = world.isPvpAllowed();
        }

        public String getName() {
            return name;
        }

        public int getSeed() {
            return seed;
        }

        public boolean isPvpAllowed() {
            return pvpAllowed;
        }
    }
}
