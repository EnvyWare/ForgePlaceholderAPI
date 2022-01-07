package com.envyful.papi.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod(ForgePlaceholderAPI.MOD_ID)
@Mod.EventBusSubscriber
public class ForgePlaceholderAPI {

    public static final String MOD_ID = "forgeplaceholderapi";
    public static final String VERSION = "1.0.0";

    public ForgePlaceholderAPI() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartedEvent event) {
        ForgeCommandFactory forgeCommandFactory = new ForgeCommandFactory();

        forgeCommandFactory.registerCommand(event.getServer(), new PlaceholderCommand());
    }
}
