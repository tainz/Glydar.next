package org.glydar.core;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.logging.CoreGlydarLogger;
import org.glydar.core.logging.CoreGlydarLoggerFormatter;


public abstract class CoreBackend implements Backend {

    private final CoreGlydarLogger logger;

    public CoreBackend() {
        this.logger = initLogger();
    }

    private CoreGlydarLogger initLogger() {
        CoreGlydarLogger logger = CoreGlydarLogger.of(this, getName());
        logger.getJdkLogger().setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CoreGlydarLoggerFormatter(false));
        consoleHandler.setLevel(Level.ALL);
        logger.getJdkLogger().addHandler(consoleHandler);

        try {
            String folder = getClass().getProtectionDomain().getCodeSource().getLocation().getFile().toString();
            FileHandler fileHandler = new FileHandler(folder + "/../logs");
            fileHandler.setFormatter(new CoreGlydarLoggerFormatter(false));
            fileHandler.setLevel(Level.ALL);
            logger.getJdkLogger().addHandler(fileHandler);
        }
        catch (SecurityException | IOException exc) {
            logger.warning(exc, "Unable to open log file");
        }

        return logger;
    }

    @Override
    public GlydarLogger getLogger(Class<?> clazz) {
        return logger.getChildLogger(clazz, clazz.getSimpleName());
    }

    @Override
    public GlydarLogger getLogger(Class<?> clazz, String prefix) {
        return logger.getChildLogger(clazz, prefix);
    }
}
