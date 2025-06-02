package com.envyful.papi.forge.api;

import com.envyful.papi.api.PlatformManager;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.UUID;

public class ForgePlatformManager implements PlatformManager<ServerPlayer> {
    @Override
    public ServerPlayer convert(UUID uuid) {
        return ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(uuid);
    }
}
