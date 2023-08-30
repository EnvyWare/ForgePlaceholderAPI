package com.envyful.papi.api.util;

import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;
import com.envyful.papi.api.PlatformManager;

import java.util.UUID;

/**
 *
 * Static utility class for handling replacing placeholders in the given text
 *
 * To replace a placeholder using this class do the following:
 * <code>
 *     String text = "%forge_name%";
 *     text = UtilPlaceholder.replaceIdentifiers(player, text);
 * </code>
 */
public class UtilPlaceholder {

    private static PlatformManager<?> platformManager;

    public static void setPlatformManager(PlatformManager<?> platformManager) {
        UtilPlaceholder.platformManager = platformManager;
    }

    public static String replaceIdentifiers(UUID player, String text) {
        return replaceIdentifiers(platformManager.convert(player), text);
    }

    /**
     * Replaces all placeholders for player in the text using the PlaceholderFactory and all
     * registered placeholders
     *
     * @param player The player replacing in respect to
     * @param text   The text being manipulated
     * @param <T>    The player type
     * @return The updated text
     */
    @SuppressWarnings("unchecked")
    public static <T> String replaceIdentifiers(T player, String text) {
        for (PlaceholderManager<?> allPlaceholderManager : PlaceholderFactory.getAllPlaceholderManagers()) {
            PlaceholderManager<T> castManager = (PlaceholderManager<T>) allPlaceholderManager;
            String newText = castManager.onPlaceholderRequest(player, text);

            if (newText != null) {
                text = newText;
            }
        }

        return text;
    }
}
