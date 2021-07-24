package com.envyful.papi.api.local;

import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;
import com.envyful.papi.api.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * Class for loading all locally stored JAR files for Placeholders
 *
 */
public class LocalManagementLoader {

    /**
     *
     * Loads all the {@link PlaceholderManager} classes found in jar files in the given directory
     *
     * @param folder The directory to look through
     */
    public static void init(String folder) {
        File folderManagement = getAndCreateFile(folder);

        if (folderManagement == null) {
            return;
        }

        File[] files = folderManagement.listFiles((dir, name) -> name.endsWith(".jar"));

        if (files == null) {
            return;
        }

        for (File file : files) {
            try {
                Class<? extends PlaceholderManager> clazz = FileUtil.findClass(file, PlaceholderManager.class);

                if (clazz == null) {
                    continue;
                }

                PlaceholderManager<?> placeholderManager = clazz.newInstance();
                PlaceholderFactory.register(placeholderManager);
            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static File getAndCreateFile(String folder) {
        File file = Paths.get(folder).toFile();

        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }
}
