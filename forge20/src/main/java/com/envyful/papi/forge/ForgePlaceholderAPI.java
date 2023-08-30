package com.envyful.papi.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.api.util.UtilPlaceholder;
import com.envyful.papi.forge.api.ForgePlatformManager;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ForgePlaceholderAPI.MOD_ID)
@Mod.EventBusSubscriber
public class ForgePlaceholderAPI {

    public static final String MOD_ID = "forgeplaceholderapi";

    public ForgePlaceholderAPI() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");
    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        ForgeCommandFactory forgeCommandFactory = new ForgeCommandFactory();

        forgeCommandFactory.registerCommand(event.getDispatcher(), new PlaceholderCommand());
        UtilPlaceholder.setPlatformManager(new ForgePlatformManager());
    }
}
