package org.glydar.api;

import org.glydar.api.logging.GlydarLogger;

public interface Backend {

    String getName();

    String getVersion();

    GlydarLogger getLogger();
}
