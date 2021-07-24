package com.envyful.papi.api.util;

import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Static utility class for handling replacing placeholders in the given text
 *
 */
public class UtilPlaceholder {

    private static final Pattern PATTERN = Pattern.compile("%([a-zA-Z0-9]+_)([a-zA-Z0-9]+)%");

    /**
     *
     * Gets all the identifiers of placeholders found in the text
     *
     * @param text The text to find placeholders in
     * @return The list of placeholder identifiers
     */
    public static List<String> getIdentifiers(String text) {
        List<String> identifiers = Lists.newArrayList();
        Matcher matcher = PATTERN.matcher(text);

        while (matcher.find()) {
            identifiers.add(matcher.group(1));
        }

        return identifiers;
    }

    /**
     *
     * Replaces all placeholders for {@param player} in the {@param text} using the {@link PlaceholderFactory} and all
     * registered palceholders
     *
     * @param player The player replacing in respect to
     * @param text The text being manipulated
     * @param <T> The player type
     * @return The updated text
     */
    public static <T> String replaceIdentifiers(T player, String text) {
        Matcher matcher = PATTERN.matcher(text);

        while (matcher.find()) {
            PlaceholderManager<T> manager = (PlaceholderManager<T>) PlaceholderFactory.getPlaceholderManager(matcher.group(1));
            Matcher internal = PATTERN.matcher(text);
            String replacement = manager.onPlaceholderRequest(player, internal.group(2));
            text = new StringBuilder(text).replace(internal.start(0), internal.end(0), replacement).toString();
        }

        return text;
    }
}
