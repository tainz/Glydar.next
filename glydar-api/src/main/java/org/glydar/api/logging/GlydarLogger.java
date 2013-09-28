package org.glydar.api.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface GlydarLogger {

    Logger getJdkLogger();

    void log(Level level, String message, Object... parameters);

    void severe(Throwable throwable, String message, Object... parameters);

    void severe(String message, Object... parameters);

    void warning(Throwable throwable, String message, Object... parameters);

    void warning(String message, Object... parameters);

    void config(String message, Object... parameters);

    void info(String message, Object... parameters);

    void fine(String message, Object... parameters);

    void finer(String message, Object... parameters);

    void finest(String message, Object... parameters);
}
