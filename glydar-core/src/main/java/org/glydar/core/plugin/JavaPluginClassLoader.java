package org.glydar.core.plugin;

import java.net.URL;
import java.net.URLClassLoader;

public class JavaPluginClassLoader extends URLClassLoader {

    public JavaPluginClassLoader(ClassLoader parent, URL... urls) {
        super(urls, parent);
    }
}
