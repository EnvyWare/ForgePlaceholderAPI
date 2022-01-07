package com.envyful.papi.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 *
 * Static factory for managing all loaded placeholder managers
 *
 */
public class PlaceholderFactory {

    private static final Set<PlaceholderManager<?>> REGISTERED_MANGERS = Sets.newConcurrentHashSet();

    /**
     *
     * Registers a placeholder manager to the server
     *
     * @param placeholderManager The placeholder manager being registered
     */
    public static void register(PlaceholderManager<?> placeholderManager) {
        REGISTERED_MANGERS.add(placeholderManager);
    }

    /**
     *
     * Unregisters the given placeholder manager from the server
     *
     * @param placeholderManager The placeholder manager being unregistered
     */
    public static void unregister(PlaceholderManager<?> placeholderManager) {
        REGISTERED_MANGERS.remove(placeholderManager);
    }

    /**
     *
     * Gets all the loaded / registered placeholder managers
     *
     * @return All placeholder managers
     */
    public static List<PlaceholderManager<?>> getAllPlaceholderManagers() {
        return Lists.newArrayList(REGISTERED_MANGERS);
    }
}
