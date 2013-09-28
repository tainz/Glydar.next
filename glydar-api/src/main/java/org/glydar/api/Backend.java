package org.glydar.api;

import java.nio.file.Path;

import org.glydar.api.logging.GlydarLogger;

public interface Backend {

    String getName();

    String getVersion();

    Path getBaseFolder();

    Path getConfigFolder();

    GlydarLogger getLogger(Class<?> clazz);

    GlydarLogger getLogger(Class<?> clazz, String prefix);
}
