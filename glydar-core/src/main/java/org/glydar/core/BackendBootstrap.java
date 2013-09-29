package org.glydar.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glydar.api.Backend;
import org.glydar.api.plugin.i18n.I18nTarget;
import org.glydar.core.logging.CoreGlydarLogger;
import org.glydar.core.logging.CoreGlydarLoggerFormatter;
import org.glydar.core.util.Versioning;

class BackendBootstrap implements I18nTarget {

    private static final String BACKUP_SUFFIX       = ".bk";
    private static final String LOGS_FOLDER_NAME    = "logs";
    private static final String CONFIG_FOLDER_NAME  = "config";
    private static final String PLUGINS_FOLDER_NAME = "plugins";
    private static final String CONFIG_FILE_NAME    = "glydar.yml";

    private final Class<?>      clazz;
    private final String        name;
    private final String        version;
    private final Logger        logger;
    private Path                baseFolder;
    private Path                logsFolder;
    private Path                configFolder;
    private Path                pluginsFolder;
    private CoreGlydarLogger    serverLogger;
    private Path                configFile;

    public BackendBootstrap(Class<? extends Backend> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
        this.version = Versioning.getGlydarVersion();
        this.logger = Logger.getLogger(getClass().getCanonicalName());

        setupFolders();
        setupLogger();
        readConfig();
    }

    @Override
    public Iterable<URL> getI18nLocations(String filename) {
        return Collections.<URL> emptyList();
    }

    private void setupFolders() {
        try {
            URI sourceUri = clazz.getProtectionDomain().getCodeSource().getLocation().toURI();
            Path path = Paths.get(sourceUri).getParent();
            baseFolder = path;
        }
        catch (URISyntaxException exc) {
            logger.log(Level.WARNING, "Error while trying to discover base folder using code source location", exc);
            baseFolder = Paths.get("");
        }

        logsFolder = tryCreateFolder(LOGS_FOLDER_NAME);
        configFolder = tryCreateFolder(CONFIG_FOLDER_NAME);
        pluginsFolder = tryCreateFolder(PLUGINS_FOLDER_NAME);
    }

    private Path tryCreateFolder(String name) {
        Path folder = baseFolder.resolve(name);

        if (!Files.isDirectory(folder)) {
            try {
                if (Files.exists(folder)) {
                    Path backupPath = folder.resolveSibling(name + BACKUP_SUFFIX);
                    Files.move(folder, backupPath);
                }
                Files.createDirectory(folder);
            }
            catch (IOException exc) {
                logger.log(Level.WARNING, "Error while trying to create missing folder " + name, exc);
            }
        }

        return folder;
    }

    private void setupLogger() {
        serverLogger = CoreGlydarLogger.of(getClass(), name);
        serverLogger.getJdkLogger().setUseParentHandlers(false);

        CoreGlydarLoggerFormatter formatter = new CoreGlydarLoggerFormatter(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        try {
            consoleHandler.setEncoding(StandardCharsets.UTF_8.name());
        }
        catch (SecurityException | UnsupportedEncodingException exc) {
            logger.log(Level.WARNING, "Unable to set console logger handler to utf8 encoding", exc);
        }
        consoleHandler.setFormatter(formatter);
        serverLogger.getJdkLogger().addHandler(consoleHandler);

        try {
            Path logsFile = logsFolder.resolve("glydar.log");
            FileHandler fileHandler = new FileHandler(logsFile.toString(), true);
            try {
                fileHandler.setEncoding(StandardCharsets.UTF_8.name());
            }
            catch (SecurityException | UnsupportedEncodingException exc) {
                logger.log(Level.WARNING, "Unable to set file logger handler to utf8 encoding", exc);
            }
            fileHandler.setFormatter(formatter);
            serverLogger.getJdkLogger().addHandler(fileHandler);
        }
        catch (SecurityException | IOException exc) {
            logger.log(Level.WARNING, "Unable to open log file", exc);
        }
    }

    private void readConfig() {
        this.configFile = baseFolder.resolve(CONFIG_FOLDER_NAME).resolve(CONFIG_FILE_NAME);
        if (!Files.isRegularFile(configFile)) {
            try {
                if (Files.exists(configFile)) {
                    Path backupPath = configFile.resolveSibling(CONFIG_FILE_NAME + BACKUP_SUFFIX);
                    Files.move(configFile, backupPath);
                }
                Files.createFile(configFile);
            }
            catch (Exception exc) {
                logger.log(Level.WARNING, "Error while trying to create missing file " + CONFIG_FILE_NAME);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Path getBaseFolder() {
        return baseFolder;
    }

    public Path getConfigFolder() {
        return configFolder;
    }

    public Path getPluginsFolder() {
        return pluginsFolder;
    }

    public CoreGlydarLogger getLogger() {
        return serverLogger;
    }

    public Path getConfigFile() {
        return configFile;
    }
}
