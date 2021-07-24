package com.envyful.papi.api;

import com.envyful.papi.api.PlaceholderManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 *
 * Static factory for managing all loaded placeholder managers
 *
 */
public class PlaceholderFactory {

    private static final Map<String, PlaceholderManager<?>> PLACEHOLDERS = Maps.newHashMap();

    /**
     *
     * Gets the placeholder manager under the given identifier
     * Returns null if not found
     *
     * @param identifer The identifier
     * @return The manager
     */
    public static PlaceholderManager<?> getPlaceholderManager(String identifer) {
        return PLACEHOLDERS.get(identifer.toLowerCase());
    }

    /**
     *
     * Registers a placeholder manager to the server
     *
     * @param placeholderManager The placeholder manager being registered
     */
    public static void register(PlaceholderManager<?> placeholderManager) {
        PLACEHOLDERS.put(placeholderManager.getIdentifier().toLowerCase(), placeholderManager);
    }

    /**
     *
     * Unregisters the given placeholder manager from the server
     *
     * @param placeholderManager The placeholder manager being unregistered
     */
    public static void unregister(PlaceholderManager<?> placeholderManager) {
        PLACEHOLDERS.remove(placeholderManager.getIdentifier().toLowerCase());
    }

    /**
     *
     * If there is a placeholder manager under the given identifier then it will be unregistered
     *
     * @param identifier The identifier being unregistered
     */
    public static void unregister(String identifier) {
        PLACEHOLDERS.remove(identifier.toLowerCase());
    }

    /**
     *
     * Gets all the loaded / registered placeholder managers
     *
     * @return All placeholder managers
     */
    public static List<PlaceholderManager<?>> getAllPlaceholderManagers() {
        return Lists.newArrayList(PLACEHOLDERS.values());
    }
}
