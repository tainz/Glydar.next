package org.glydar.api.plugin;

import org.glydar.api.logging.GlydarLogger;

public interface Plugin {

    PluginDescriptor getDescriptor();

    GlydarLogger getLogger();

    void enable();

    void disable();
}
