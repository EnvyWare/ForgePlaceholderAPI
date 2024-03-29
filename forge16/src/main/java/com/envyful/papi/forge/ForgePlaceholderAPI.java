package com.envyful.papi.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.api.util.UtilPlaceholder;
import com.envyful.papi.forge.api.ForgePlatformManager;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

@Mod(ForgePlaceholderAPI.MOD_ID)
@Mod.EventBusSubscriber
public class ForgePlaceholderAPI {

    public static final String MOD_ID = "forgeplaceholderapi";
    public static final String VERSION = "1.0.0";

    public ForgePlaceholderAPI() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");
    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        ForgeCommandFactory forgeCommandFactory = new ForgeCommandFactory();

        forgeCommandFactory.registerCommand(event.getDispatcher(), new PlaceholderCommand());
        UtilPlaceholder.setPlatformManager(new ForgePlatformManager());
    }
}
