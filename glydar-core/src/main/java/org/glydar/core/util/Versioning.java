package org.glydar.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.glydar.api.Glydar;

public final class Versioning {

    private static final String GLYDAR_POM_PROPERTIES = "/META-INF/maven/org.glydar/glydar-api/pom.properties";
    private static final String UNKNOW_VERSION        = "Unknown-Version";

    public static String getGlydarVersion() {
        InputStream stream = Glydar.class.getResourceAsStream(GLYDAR_POM_PROPERTIES);
        Properties properties = new Properties();

        if (stream != null) {
            try {
                properties.load(stream);

                return properties.getProperty("version");
            }
            catch (IOException ex) {
                // Too bad
            }
        }

        return UNKNOW_VERSION;
    }
}
