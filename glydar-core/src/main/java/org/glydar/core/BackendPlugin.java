package org.glydar.core;

import java.util.Collections;
import java.util.List;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.Plugin;
import org.glydar.api.plugin.PluginDescriptor;

/**
 * Adapter which allows the backend to be used as a plugin
 */
public class BackendPlugin implements Plugin {

    private final CoreBackend backend;

    public BackendPlugin(CoreBackend backend) {
        this.backend = backend;
    }

    @Override
    public PluginDescriptor getDescriptor() {
        return new BackendDescriptor();
    }

    @Override
    public GlydarLogger getLogger() {
        return backend.getLogger();
    }

    @Override
    public void enable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void disable() {
        throw new UnsupportedOperationException();
    }

    private class BackendDescriptor implements PluginDescriptor {

        private static final String AUTHOR = "Glydar Team";
        private static final String WEBSITE = "http://glydar.org";
        private static final String DESCRIPTION = "Glydar";

        @Override
        public String getName() {
            return backend.getName();
        }

        @Override
        public String getVersion() {
            return backend.getVersion();
        }

        @Override
        public String getAuthor() {
            return AUTHOR;
        }

        @Override
        public String getWebsite() {
            return WEBSITE;
        }

        @Override
        public String getDescription() {
            return DESCRIPTION;
        }

        @Override
        public List<PluginDependency> getDependencies() {
            return Collections.emptyList();
        }

        @Override
        public List<PluginDependency> getSoftDependencies() {
            return Collections.emptyList();
        }
    }
}
