package org.glydar.core.plugin;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.glydar.api.plugin.PluginDescriptor;

public class JavaPluginDescriptor implements PluginDescriptor {

    private static final String PROPERTY_MAIN_CLASS  = "main-class";
    private static final String PROPERTY_NAME        = "name";
    private static final String PROPERTY_VERSION     = "version";
    private static final String PROPERTY_AUTHOR      = "author";
    private static final String PROPERTY_WEBSITE     = "website";
    private static final String PROPERTY_DESCRIPTION = "description";

    private final String        mainClass;
    private final String        name;
    private final String        version;
    private final String        author;
    private final String        website;
    private final String        description;

    public JavaPluginDescriptor(Properties properties) {
        this.mainClass = requiredProperty(properties, PROPERTY_MAIN_CLASS);
        this.name = requiredProperty(properties, PROPERTY_NAME);
        this.version = requiredProperty(properties, PROPERTY_VERSION);
        this.author = properties.getProperty(PROPERTY_AUTHOR);
        this.website = properties.getProperty(PROPERTY_WEBSITE);
        this.description = properties.getProperty(PROPERTY_DESCRIPTION);
    }

    private String requiredProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new PluginLoaderException("Missing key " + key);
        }

        return value;
    }

    public String getMainClass() {
        return mainClass;
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
    public String getAuthor() {
        return author;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public String getDescription() {
        return description;
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
