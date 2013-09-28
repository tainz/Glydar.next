package org.glydar.api.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlydarLogger {

    public static GlydarLogger of(Class<?> clazz, String prefix) {
        return new GlydarLogger(Logger.getLogger(clazz.getCanonicalName()), prefix);
    }

    public static GlydarLogger of(Object instance, String prefix) {
        return of(instance.getClass(), prefix);
    }

    private final Logger jdkLogger;
    private final String prefix;

    protected GlydarLogger(Logger jdkLogger, String prefix) {
        this.jdkLogger = jdkLogger;
        this.prefix = prefix;
    }

    public Logger getJdkLogger() {
        return jdkLogger;
    }

    public GlydarLogger getChildLogger(Class<?> clazz, String prefix) {
        GlydarLogger childLogger = of(clazz, prefix);
        childLogger.jdkLogger.setParent(jdkLogger);
        return childLogger;
    }

    public GlydarLogger getChildLogger(Object instance, String prefix) {
        GlydarLogger childLogger = of(instance, prefix);
        childLogger.jdkLogger.setParent(jdkLogger);
        return childLogger;
    }

    public void log(Level level, Throwable thrown, String message, Object... parameters) {
        GlydarLogRecord record = new GlydarLogRecord(level, message, prefix);
        record.setThrown(thrown);
        if (parameters.length > 0) {
            record.setParameters(parameters);
        }

        jdkLogger.log(record);
    }

    public void log(Level level, String message, Object... parameters) {
        log(level, null, message, parameters);
    }

    public void severe(Throwable throwable, String message, Object... parameters) {
        log(Level.SEVERE, throwable, message, parameters);
    }

    public void severe(String message, Object... parameters) {
        log(Level.SEVERE, message, parameters);
    }

    public void warning(Throwable throwable, String message, Object... parameters) {
        log(Level.WARNING, throwable, message, parameters);
    }

    public void warning(String message, Object... parameters) {
        log(Level.WARNING, message, parameters);
    }

    public void config(String message, Object... parameters) {
        log(Level.CONFIG, message, parameters);
    }

    public void info(String message, Object... parameters) {
        log(Level.INFO, message, parameters);
    }

    public void fine(String message, Object... parameters) {
        log(Level.FINE, message, parameters);
    }

    public void finer(String message, Object... parameters) {
        log(Level.FINER, message, parameters);
    }

    public void finest(String message, Object... parameters) {
        log(Level.FINEST, message, parameters);
    }
}
