package com.envyful.papi.api;

/**
 *
 * An interface representing a plugin's placeholder manager.
 *
 * @param <T> The player class for the platform
 */
public interface PlaceholderManager<T> {

    /**
     *
     * The identifying string in a placeholder
     * I.e. placeholders are always in the format:
     * $<identifier>_<placeholder>%
     *
     * @return The manager's identifier
     */
    String getIdentifier();

    /**
     *
     * Gets the authors of the identifier
     *
     * @return The authors
     */
    String[] getAuthors();

    /**
     *
     * Gets the current version of the placeholder manager
     *
     * @return The version
     */
    String getVersion();

    /**
     *
     * Gets the name of the placeholder manager
     *
     * @return The name
     */
    String getName();

    /**
     *
     * Converts a placeholder to it's value based on the passed player
     *
     * @param player The player to replace the placeholder with
     * @param placeholder The placeholder to replace
     * @return The data as a string
     */
    String onPlaceholderRequest(T player, String placeholder);

}
