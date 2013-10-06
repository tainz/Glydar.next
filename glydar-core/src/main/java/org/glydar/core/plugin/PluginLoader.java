package org.glydar.core.plugin;

import java.nio.file.Path;

import org.glydar.api.plugin.Plugin;

public interface PluginLoader {

    Plugin load(Path path);
}
