package org.glydar.api;

import java.nio.file.Path;
import java.util.ServiceLoader;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.PluginLoader;
import org.glydar.api.plugin.command.CommandManager;
import org.glydar.api.plugin.event.EventManager;

public final class Glydar {

    private static final Backend backend;

    public static Backend getBackend() {
        return backend;
    }

    public static String getName() {
        return backend.getName();
    }

    public static String getVersion() {
        return backend.getVersion();
    }

    public static BackendType getBackendType() {
        return backend.getType();
    }

    public static Path getBaseFolder() {
        return backend.getBaseFolder();
    }

    public static Path getConfigFolder() {
        return backend.getConfigFolder();
    }

    public static GlydarLogger getLogger(Class<?> clazz) {
        return backend.getLogger(clazz);
    }

    public static GlydarLogger getLogger(Class<?> clazz, String prefix) {
        return backend.getLogger(clazz, prefix);
    }

    public static PluginLoader getPluginLoader() {
        return backend.getPluginLoader();
    }

    public static CommandManager getCommandManager() {
        return backend.getCommandManager();
    }

    public static EventManager getEventManager() {
        return backend.getEventManager();
    }

    public static Server getServer() {
        return backend.getServer();
    }

    static {
        final ServiceLoader<Backend> loader = ServiceLoader.load(Backend.class);
        backend = loader.iterator().next();
    }

    private Glydar() {
    }
}
