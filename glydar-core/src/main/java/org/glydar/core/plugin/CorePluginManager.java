package org.glydar.core.plugin;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Level;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.Plugin;
import org.glydar.api.plugin.PluginDescriptor;
import org.glydar.api.plugin.PluginManager;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class CorePluginManager implements PluginManager {

    private static final String LOGGER_PREFIX = "Plugin Manager";

    private final GlydarLogger logger;
    private final Path folder;
    private final List<PluginLoader> pluginLoaders;
    private List<Plugin> plugins;

    public CorePluginManager(Backend backend) {
        this.logger = backend.getLogger(getClass(), LOGGER_PREFIX);
        this.folder = backend.getPluginsFolder();

        ServiceLoader<PluginLoader> pluginLoadersLoader = ServiceLoader.load(PluginLoader.class);
        pluginLoaders = Lists.newArrayList(pluginLoadersLoader);
    }

    @Override
    public void load() {
        logger.info("Loading plugins using loaders :");
        for (PluginLoader pluginLoader : pluginLoaders) {
            logger.info(" - " + pluginLoader.getClass().getName());
        }

        if (!folder.toFile().exists()) {
            logger.log(Level.INFO, "Unable to access `plugins` directory, creating the directory");
            folder.toFile().mkdir();
        }

        DirectoryStream<Path> pluginPaths;
        try {
            pluginPaths = Files.newDirectoryStream(folder);
        }
        catch (IOException exc) {
            logger.log(Level.SEVERE, "Unable to access `plugins` directory", exc);
            return;
        }

        ImmutableList.Builder<Plugin> pluginsBuilder = ImmutableList.builder();
        for (Path path : pluginPaths) {
            if (path.getFileName().toString().startsWith(".")) {
                continue;
            }

            try {
                Plugin plugin = loadPlugin(path);
                if (plugin == null) {
                    logger.warning("Unable to find suitable plugin loader for file " + path.getFileName());
                }
                else {
                    pluginsBuilder.add(plugin);
                    logger.info("Plugin " + pluginName(plugin) + " loaded.");
                }
            }
            catch (PluginLoaderException exc) {
                logger.log(Level.SEVERE, "Error while loading for file " + path.getFileName(), exc);
            }
        }

        this.plugins = pluginsBuilder.build();
        logger.info(plugins.size() + " plugins loaded");
    }

    private Plugin loadPlugin(Path path) {
        for (PluginLoader pluginLoader : pluginLoaders) {
            Plugin plugin = pluginLoader.load(path);
            if (plugin != null) {
                return plugin;
            }
        }

        return null;
    }

    private String pluginName(Plugin plugin) {
        PluginDescriptor descriptor = plugin.getDescriptor();
        return descriptor.getName() + " v" + descriptor.getVersion();
    }

    @Override
    public void enableAll() {
        for (Plugin plugin : plugins) {
            plugin.enable();
        }

    }

    @Override
    public void disableAll() {
        for (Plugin plugin : plugins) {
            plugin.disable();
        }
    }
}
