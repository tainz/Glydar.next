package org.glydar.core.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glydar.api.logging.GlydarLogger;

public class CoreGlydarLogger implements GlydarLogger {

    public static CoreGlydarLogger of(Class<?> clazz, String prefix) {
        return new CoreGlydarLogger(Logger.getLogger(clazz.getCanonicalName()), prefix);
    }

    private final Logger jdkLogger;
    private final String prefix;

    protected CoreGlydarLogger(Logger jdkLogger, String prefix) {
        this.jdkLogger = jdkLogger;
        this.prefix = prefix;
    }

    @Override
    public Logger getJdkLogger() {
        return jdkLogger;
    }

    public CoreGlydarLogger getChildLogger(Class<?> clazz, String prefix) {
        CoreGlydarLogger childLogger = of(clazz, prefix);
        childLogger.jdkLogger.setParent(jdkLogger);
        return childLogger;
    }

    public void log(Level level, Throwable thrown, String message, Object... parameters) {
        CoreGlydarLogRecord record = new CoreGlydarLogRecord(level, message, prefix);
        record.setThrown(thrown);
        if (parameters.length > 0) {
            record.setParameters(parameters);
        }

        jdkLogger.log(record);
    }

    @Override
    public void log(Level level, String message, Object... parameters) {
        log(level, null, message, parameters);
    }

    @Override
    public void severe(Throwable throwable, String message, Object... parameters) {
        log(Level.SEVERE, throwable, message, parameters);
    }

    @Override
    public void severe(String message, Object... parameters) {
        log(Level.SEVERE, message, parameters);
    }

    @Override
    public void warning(Throwable throwable, String message, Object... parameters) {
        log(Level.WARNING, throwable, message, parameters);
    }

    @Override
    public void warning(String message, Object... parameters) {
        log(Level.WARNING, message, parameters);
    }

    @Override
    public void config(String message, Object... parameters) {
        log(Level.CONFIG, message, parameters);
    }

    @Override
    public void info(String message, Object... parameters) {
        log(Level.INFO, message, parameters);
    }

    @Override
    public void fine(String message, Object... parameters) {
        log(Level.FINE, message, parameters);
    }

    @Override
    public void finer(String message, Object... parameters) {
        log(Level.FINER, message, parameters);
    }

    @Override
    public void finest(String message, Object... parameters) {
        log(Level.FINEST, message, parameters);
    }
}
