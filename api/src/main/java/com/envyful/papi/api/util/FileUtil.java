package com.envyful.papi.api.util;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * Static utility method for finding class files inside of jars.
 * Source:
 * https://github.com/PlaceholderAPI/PlaceholderAPI/blob/master/src/main/java/me/clip/placeholderapi/util/FileUtil.java
 *
 */
public class FileUtil {

    public static <T> Class<? extends T> findClass(final File file,
                                                   final Class<T> clazz) throws IOException, ClassNotFoundException {
        if (!file.exists()) {
            return null;
        }

        final URL jar = file.toURI().toURL();
        final URLClassLoader loader = new URLClassLoader(new URL[]{jar}, clazz.getClassLoader());
        final List<String> matches = Lists.newArrayList();
        final List<Class<? extends T>> classes = Lists.newArrayList();

        try (final JarInputStream stream = new JarInputStream(jar.openStream())) {
            JarEntry entry;

            while ((entry = stream.getNextJarEntry()) != null) {
                final String name = entry.getName();

                if (name.isEmpty() || !name.endsWith(".class")) {
                    continue;
                }

                matches.add(name.substring(0, name.lastIndexOf('.')).replace('/', '.'));
            }

            for (final String match : matches) {
                try {
                    final Class<?> loaded = loader.loadClass(match);

                    if (clazz.isAssignableFrom(loaded)) {
                        classes.add(loaded.asSubclass(clazz));
                    }
                } catch (NoClassDefFoundError ignored) {}
            }
        }

        if (classes.isEmpty()) {
            loader.close();
            return null;
        }

        return classes.get(0);
    }
}
