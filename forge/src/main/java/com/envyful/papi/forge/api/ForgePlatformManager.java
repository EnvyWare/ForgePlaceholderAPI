package com.envyful.papi.forge.api;

import com.envyful.papi.api.PlatformManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class ForgePlatformManager implements PlatformManager<EntityPlayerMP> {
    @Override
    public EntityPlayerMP convert(UUID uuid) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(uuid);
    }
}
