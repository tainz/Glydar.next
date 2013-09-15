package org.glydar.server;

import org.glydar.api.Glydar;

public class GlydarServerMain {

    public static void main(String[] args) {
        Glydar.getLogger().info("Starting server {0} v{1}", Glydar.getName(), Glydar.getVersion());
    }
}
