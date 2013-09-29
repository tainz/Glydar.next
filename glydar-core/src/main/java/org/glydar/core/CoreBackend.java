package org.glydar.core;

import java.nio.file.Path;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.PluginLoader;
import org.glydar.api.plugin.command.CommandManager;
import org.glydar.api.plugin.event.EventManager;
import org.glydar.core.logging.CoreGlydarLogger;
import org.glydar.core.plugin.command.ConsoleCommandReader;
import org.glydar.core.plugin.command.CoreCommandManager;
import org.glydar.core.plugin.event.CoreEventManager;

public abstract class CoreBackend implements Backend {

    private final String           name;
    private final String           version;
    private final Path             baseFolder;
    private final Path             configFolder;
    private final Path             configFile;
    private final Path             pluginsFolder;
    private final CoreGlydarLogger logger;
    private final PluginLoader     pluginLoader;
    private final CommandManager   commandManager;
    private final ConsoleCommandReader consoleReader;
    private final EventManager     eventManager;

    public CoreBackend(String name) {
        BackendBootstrap bootstrap = new BackendBootstrap(getClass(), name);

        this.name = bootstrap.getName();
        this.version = bootstrap.getVersion();
        this.baseFolder = bootstrap.getBaseFolder();
        this.configFolder = bootstrap.getConfigFolder();
        this.configFile = bootstrap.getConfigFile();
        this.pluginsFolder = bootstrap.getPluginsFolder();
        this.logger = bootstrap.getLogger();

        this.pluginLoader = new PluginLoader(this);
        this.commandManager = new CoreCommandManager(this);
        this.consoleReader = new ConsoleCommandReader(this);
        this.eventManager = new CoreEventManager(this);
    }

    @Override
    public String getName() {
        return name;
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

    public Path getConfigFile() {
        return configFile;
    }

    @Override
    public Path getPluginsFolder() {
        return pluginsFolder;
    }

    public GlydarLogger getLogger() {
        return logger;
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

    public ConsoleCommandReader getConsoleReader() {
        return consoleReader;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
