package com.envyful.papi.api.manager.extensions;

import java.util.List;

/**
 *
 * A placeholder extension used in a {@link com.envyful.papi.api.PlaceholderManager}
 *
 * @param <T> The player class
 */
public interface PlaceholderExtension<T> {

    /**
     *
     * The extension's name
     *
     * @return The name
     */
    String getName();

    /**
     *
     * The priority of the extension
     * Higher priority = first in queue
     *
     * @return The priority
     */
    int getPriority();

    /**
     *
     * Gets a short description of the extension
     *
     * @return Info about the extension
     */
    List<String> getDescription();

    /**
     *
     * Gets example usage of the extension
     *
     * @return Example usage
     */
    List<String> getExamples();

    /**
     *
     * Checks if the data and the player are matching for this extension
     *
     * @param player The player
     * @param placeholder The placeholder text
     * @return The
     */
    boolean matches(T player, String placeholder);

    /**
     *
     * Parses the placeholder and replaces the text
     *
     * @param player The player
     * @param placeholder The text in the placeholder
     * @return The text replacing the entire placeholder with
     */
    String parse(T player, String placeholder);

}
