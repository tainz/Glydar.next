package org.glydar.core.plugin;

public class PluginLoaderException extends RuntimeException {

    private static final long serialVersionUID = -6635414671160175556L;

    public PluginLoaderException(String message) {
        super(message);
    }

    public PluginLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
