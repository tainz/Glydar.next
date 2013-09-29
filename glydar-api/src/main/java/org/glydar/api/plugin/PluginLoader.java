package org.glydar.api.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.glydar.api.Backend;
import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;

public class PluginLoader {

    private final GlydarLogger     logger;
    private final List<Plugin>     loadedPlugins;
    private final List<Plugin>     pending;
    private final Map<String, URL> jarMap;

    public PluginLoader(Backend backend) {
        this.logger = backend.getLogger(PluginLoader.class, "Plugin Loader");
        this.loadedPlugins = new ArrayList<Plugin>();
        this.pending = new ArrayList<Plugin>();
        this.jarMap = new HashMap<String, URL>();
    }

    public void loadPlugins() {
        File pluginDir = Glydar.getBaseFolder().resolve("plugins").toFile();

        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
            return;
        }
        logger.info("Loading plugins...");
        for (File file : pluginDir.listFiles()) {
            if (!file.isDirectory() && !file.getName().startsWith(".")) {
                try {
                    loadPlugin(file);
                }
                catch (PluginException exc) {
                    logger.warning(exc, "Error while loading plugin for file : {0}", file);
                }
            }
        }

        for (Plugin p : pending) {
            try {
                enablePlugin(p);
            }
            catch (Exception e) {
                logger.warning("Error initializing plugin {0}", p.getName());
                e.printStackTrace();
            }
        }

        logger.info("Loaded {0} plugins!", loadedPlugins.size());
    }

    public void unloadPlugins() {
        for (Plugin plugin : loadedPlugins) {
            logger.info("Disabling {0} v{1}", plugin.getName(), plugin.getVersion());
            disablePlugin(plugin);
        }
    }

    public void loadPlugin(File file) throws PluginException {
        if (file == null)
            throw new PluginException("Plugin cannot be null!");

        Plugin plugin = null;
        try {
            plugin = getPlugin(file);
        }
        catch (Exception e) {
            logger.warning(e, "Failed to load file {0}!", file.getName());
            e.printStackTrace();
            return;
        }

        GlydarLogger logger = Glydar.getLogger(plugin.getClass(), plugin.getName());
        plugin.initialize(this, logger);
        StringBuffer sb = new StringBuffer();
        try {
            jarMap.put(plugin.getName(), file.toURI().toURL());
        }
        catch (MalformedURLException e) {
            logger.warning(e, "Failed to load plugin {0}", plugin.getName());
            e.printStackTrace();
            return;
        }
        sb.append("Loading " + plugin.getName() + " v" + plugin.getVersion());
        if (plugin.getAuthor() != null) {
            sb.append(" by " + plugin.getAuthor());
        }
        logger.info(sb.toString());
        pending.add(plugin);
    }

    @SuppressWarnings("unchecked")
    private Plugin getPlugin(File file) throws PluginException, NoSuchMethodException, SecurityException, IOException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (!file.getName().endsWith(".jar"))
            throw new PluginException("File must be a jar file!");
        JarFile jFile = new JarFile(file.getPath());
        URL[] urls = { file.toURI().toURL() };
        URLClassLoader cl = new URLClassLoader(urls, getClass().getClassLoader());
        Enumeration<JarEntry> e = jFile.entries();
        Class<? extends Plugin> clazz = null;
        try {
            InputStream is = cl.getResourceAsStream("plugin.properties");
            Properties props = new Properties();
            props.load(is);
            String main = props.getProperty("main");
            try {
                Class<?> c = cl.loadClass(main);
                if (c.getSuperclass() == Plugin.class) {
                    clazz = (Class<? extends Plugin>) c;
                    while (e.hasMoreElements()) {
                        JarEntry entry = e.nextElement();
                        if (entry.isDirectory() || !entry.getName().endsWith(".class"))
                            continue;
                        String className = entry.getName().substring(0, entry.getName().length() - 6);
                        className = className.replace('/', '.');
                        try {
                            cl.loadClass(className);
                        }
                        catch (Exception ex) {
                            logger.warning("Error loading plugin class from {0}", className);
                        }
                    }
                }
                else {
                    throw new PluginException("File plugin.properties main class for " + file.getName()
                            + " is invalid!");
                }
            }
            catch (Exception ex) {
                logger.warning(ex, "Error !");
            }
        }
        catch (Exception ex) {
            while (e.hasMoreElements()) {
                JarEntry entry = e.nextElement();
                if (entry.isDirectory() || !entry.getName().endsWith(".class"))
                    continue;
                String className = entry.getName().substring(0, entry.getName().length() - 6);
                className = className.replace('/', '.');
                try {
                    Class<?> c = cl.loadClass(className);
                    if (c.getSuperclass() == Plugin.class) {
                        clazz = (Class<? extends Plugin>) c;
                    }
                }
                catch (Exception e1) {
                    logger.warning("Error loading plugin class from {0}", className);
                }
            }
        }
        jFile.close();
        cl.close();
        if (clazz == null)
            throw new PluginException("Plugin " + file.getName().replace(".jar", "")
                    + " does not contain a main class!");
        Constructor<? extends Plugin> constructor = clazz.asSubclass(Plugin.class).getConstructor();
        Plugin plugin = constructor.newInstance();
        return plugin;
    }

    public List<Plugin> getPlugins() {
        return loadedPlugins;
    }

    public void enablePlugin(Plugin plugin) {
        try {
            plugin.setEnabled(true);
        }
        catch (Exception e) {

        }
        loadedPlugins.add(plugin);
    }

    public void disablePlugin(Plugin plugin) {
        try {
            plugin.setEnabled(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        loadedPlugins.remove(plugin);
    }

    public URLClassLoader getClassLoader(Plugin plugin) {
        URLClassLoader cl = new URLClassLoader(new URL[] { jarMap.get(plugin.getName()) });
        return cl;
    }

}
