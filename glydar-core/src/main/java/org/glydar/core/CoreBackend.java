package org.glydar.core;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.PluginLoader;
import org.glydar.api.plugin.command.CommandManager;
import org.glydar.api.plugin.event.EventManager;
import org.glydar.core.logging.CoreGlydarLogger;
import org.glydar.core.logging.CoreGlydarLoggerFormatter;
import org.glydar.core.plugin.command.CoreCommandManager;
import org.glydar.core.plugin.event.CoreEventManager;
import org.glydar.core.util.Versioning;

public abstract class CoreBackend implements Backend {

    private final String           version;
    private final Path             baseFolder;
    private final Path             configFolder;
    private final CoreGlydarLogger logger;
    private final PluginLoader     pluginLoader;
    private final CommandManager   commandManager;
    private final EventManager     eventManager;

    public CoreBackend() {
        this.version = Versioning.getGlydarVersion();
        this.baseFolder = initBaseFolder();
        this.configFolder = baseFolder.resolve("config");
        this.logger = initLogger();
        this.pluginLoader = new PluginLoader(this);
        this.commandManager = new CoreCommandManager(this);
        this.eventManager = new CoreEventManager(this);
    }

    private Path initBaseFolder() {
        try {
            URI sourceUri = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            Path path = Paths.get(sourceUri).getParent();
            return path;
        }
        catch (URISyntaxException exc) {
            return Paths.get("");
        }
    }

    private CoreGlydarLogger initLogger() {
        CoreGlydarLogger logger = CoreGlydarLogger.of(this, getName());
        logger.getJdkLogger().setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CoreGlydarLoggerFormatter(false));
        consoleHandler.setLevel(Level.ALL);
        logger.getJdkLogger().addHandler(consoleHandler);

        try {
            String folder = getClass().getProtectionDomain().getCodeSource().getLocation().getFile().toString();
            FileHandler fileHandler = new FileHandler(folder + "/../logs");
            fileHandler.setFormatter(new CoreGlydarLoggerFormatter(false));
            fileHandler.setLevel(Level.ALL);
            logger.getJdkLogger().addHandler(fileHandler);
        }
        catch (SecurityException | IOException exc) {
            logger.warning(exc, "Unable to open log file");
        }

        return logger;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public Path getBaseFolder() {
        return baseFolder;
    }

    @Override
    public Path getConfigFolder() {
        return configFolder;
    }

    @Override
    public GlydarLogger getLogger(Class<?> clazz) {
        return logger.getChildLogger(clazz, clazz.getSimpleName());
    }

    @Override
    public GlydarLogger getLogger(Class<?> clazz, String prefix) {
        return logger.getChildLogger(clazz, prefix);
    }

    @Override
    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
