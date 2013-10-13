package org.glydar.api.plugin;

import org.glydar.api.logging.GlydarLogger;

public interface Plugin {

    ClassLoader getClassLoader();

    PluginDescriptor getDescriptor();

    GlydarLogger getLogger();

    void enable();

    void disable();
}
