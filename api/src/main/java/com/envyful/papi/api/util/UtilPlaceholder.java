package com.envyful.papi.api.util;

import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;

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

    /**
     *
     * Replaces all placeholders for {@param player} in the {@param text} using the {@link PlaceholderFactory} and all
     * registered placeholders
     *
     * @param player The player replacing in respect to
     * @param text The text being manipulated
     * @param <T> The player type
     * @return The updated text
     */
    @SuppressWarnings("unchecked")
    public static <T> String replaceIdentifiers(T player, String text) {
        for (PlaceholderManager<?> allPlaceholderManager : PlaceholderFactory.getAllPlaceholderManagers()) {
            PlaceholderManager<T> castManager = (PlaceholderManager<T>)  allPlaceholderManager;
            String newText = castManager.onPlaceholderRequest(player, text);

            if (newText != null) {
                text = newText;
            }
        }

        return text;
    }
}
