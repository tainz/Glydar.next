package org.glydar.server;

import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;

public class GlydarServerMain {

    public static void main(String[] args) {
        GlydarLogger logger = Glydar.getLogger(GlydarServerMain.class, "Boot");
        logger.info("Starting server {0} v{1}", Glydar.getName(), Glydar.getVersion());
    }
}
