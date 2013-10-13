package org.glydar.api;

import java.nio.file.Path;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.PluginManager;
import org.glydar.api.plugin.command.CommandManager;
import org.glydar.api.plugin.event.EventManager;

public interface Backend {

    String getName();

    String getVersion();

    BackendType getType();

    Path getBaseFolder();

    Path getConfigFolder();

    Path getPluginsFolder();

    GlydarLogger getLogger(Class<?> clazz);

    GlydarLogger getLogger(Class<?> clazz, String prefix);

    PluginManager getPluginManager();

    CommandManager getCommandManager();

    EventManager getEventManager();

    Server getServer();

    void shutdown();
}
