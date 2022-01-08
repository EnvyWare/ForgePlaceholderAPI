package com.envyful.papi.api;

import com.envyful.papi.api.manager.extensions.PlaceholderExtension;

import java.util.List;

/**
 *
 * An interface representing a plugin's placeholder manager.
 *
 * @param <T> The player class for the platform
 */
public interface PlaceholderManager<T> {

    /**
     *
     * Gets descriptive information about the {@link PlaceholderManager}
     *
     * @return A list of descriptive messages about the placeholder manager
     */
    List<String> getDescription();

    /**
     *
     * Converts a placeholder to it's value based on the passed player
     *
     * @param player The player to replace the placeholder with
     * @param placeholder The placeholder to replace
     * @return The data as a string
     */
    String onPlaceholderRequest(T player, String placeholder);

    /**
     *
     * Registers the placeholders for this specific manager
     *
     */
    <A extends PlaceholderExtension<T>> void registerPlaceholder(A extension);

}
