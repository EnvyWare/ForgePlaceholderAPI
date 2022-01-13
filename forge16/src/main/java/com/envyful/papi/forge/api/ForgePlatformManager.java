package com.envyful.papi.forge.api;

import com.envyful.papi.api.PlatformManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.UUID;

public class ForgePlatformManager implements PlatformManager<ServerPlayerEntity> {
    @Override
    public ServerPlayerEntity convert(UUID uuid) {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByUUID(uuid);
    }
}
