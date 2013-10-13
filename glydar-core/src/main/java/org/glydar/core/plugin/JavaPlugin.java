package org.glydar.core.plugin;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.Plugin;

public class JavaPlugin implements Plugin {

    private JavaPluginClassLoader classLoader;
    private JavaPluginDescriptor  descriptor;
    private GlydarLogger logger;

    public JavaPlugin() {
    }

    @SuppressWarnings("unused")
    private void initialize(GlydarLogger logger, JavaPluginClassLoader classLoader, JavaPluginDescriptor descriptor) {
        this.logger = logger;
        this.classLoader = classLoader;
        this.descriptor = descriptor;
    }

    @Override
    public final JavaPluginClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public final JavaPluginDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public final GlydarLogger getLogger() {
        return logger;
    }

    @Override
    public void enable() {
    }

    @Override
    public void disable() {
    }
}
