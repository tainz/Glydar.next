package org.glydar.api.plugin;

import java.util.List;

public interface PluginDescriptor {

    String getName();

    String getVersion();

    String getAuthor();

    String getWebsite();

    String getDescription();

    List<PluginDependency> getDependencies();

    List<PluginDependency> getSoftDependencies();

    public class PluginDependency {

        private final String name;
        private final String version;

        public PluginDependency(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }
    }
}
