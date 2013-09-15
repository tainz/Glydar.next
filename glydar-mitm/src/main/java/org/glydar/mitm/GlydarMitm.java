package org.glydar.mitm;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.glydar.api.Backend;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.logging.GlydarLoggerFormatter;

public class GlydarMitm implements Backend {

    private static final String NAME    = "Glydar MITM";
    private static final String VERSION = "dev";

    private final GlydarLogger  logger;

    public GlydarMitm() {
        this.logger = initLogger();
    }

    private GlydarLogger initLogger() {
        GlydarLogger logger = GlydarLogger.of(this, NAME);
        logger.getJdkLogger().setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new GlydarLoggerFormatter(false));
        consoleHandler.setLevel(Level.ALL);
        logger.getJdkLogger().addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("target/logs");
            fileHandler.setFormatter(new GlydarLoggerFormatter(false));
            fileHandler.setLevel(Level.ALL);
            logger.getJdkLogger().addHandler(fileHandler);
        }
        catch (SecurityException | IOException exc) {
            logger.warning(exc, "Unable to open log file");
        }

        return logger;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public GlydarLogger getLogger() {
        return logger;
    }
}
