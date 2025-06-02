package com.envyful.papi.forge;

import com.envyful.api.neoforge.chat.ComponentTextFormatter;
import com.envyful.api.neoforge.command.ForgeCommandFactory;
import com.envyful.api.neoforge.command.parser.ForgeAnnotationCommandParser;
import com.envyful.api.neoforge.platform.ForgePlatformHandler;
import com.envyful.api.platform.PlatformProxy;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.api.util.UtilPlaceholder;
import com.envyful.papi.forge.api.ForgePlatformManager;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ForgePlaceholderAPI.MOD_ID)
public class ForgePlaceholderAPI {

    public static final String MOD_ID = "forgeplaceholderapi";

    public ForgePlaceholderAPI() {
        PlatformProxy.setTextFormatter(ComponentTextFormatter.getInstance());
        PlatformProxy.setHandler(ForgePlatformHandler.getInstance());
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");
    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        var forgeCommandFactory = new ForgeCommandFactory(ForgeAnnotationCommandParser::new, null);

        forgeCommandFactory.registerCommand(event.getDispatcher(), forgeCommandFactory.parseCommand(new PlaceholderCommand()));
        UtilPlaceholder.setPlatformManager(new ForgePlatformManager());
    }
}
