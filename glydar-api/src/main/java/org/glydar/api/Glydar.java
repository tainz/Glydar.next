package org.glydar.api;

import java.util.ServiceLoader;

import org.glydar.api.logging.GlydarLogger;

import com.google.common.collect.Iterables;

public final class Glydar {

    public static Backend getBackend() {
        return backend;
    }

    public static String getName() {
        return backend.getName();
    }

    public static String getVersion() {
        return backend.getVersion();
    }

    public static GlydarLogger getLogger() {
        return backend.getLogger();
    }

    private static final Backend backend;

    static {
        final ServiceLoader<Backend> loader = ServiceLoader.load(Backend.class);
        backend = Iterables.getFirst(loader, null);
    }

    private Glydar() {
    }
}
