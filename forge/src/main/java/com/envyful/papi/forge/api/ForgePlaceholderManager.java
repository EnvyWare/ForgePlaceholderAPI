package com.envyful.papi.forge.api;

import com.envyful.papi.api.manager.AbstractPlaceholderManager;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 *
 * Forge extension of the {@link AbstractPlaceholderManager} to avoid having to specify player type if desired
 *
 */
public class ForgePlaceholderManager extends AbstractPlaceholderManager<EntityPlayerMP> {

    public ForgePlaceholderManager(String identifier, String[] authors, String version, String name,
                                   String surroundingChar) {
        super(identifier, authors, version, name, surroundingChar);
    }

    public ForgePlaceholderManager(String identifier, String[] authors, String version, String name) {
        super(identifier, authors, version, name);
    }
}
