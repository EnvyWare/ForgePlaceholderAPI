package com.envyful.papi.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = "forgeplaceholderapi",
        name = "Forge PlaceholderAPI",
        version = ForgePlaceholderAPI.VERSION,
        acceptableRemoteVersions = "*",
        updateJSON = "https://ogn.pixelmonmod.com/update/sm-fp/update.json"
)
public class ForgePlaceholderAPI {

    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        ForgeCommandFactory forgeCommandFactory = new ForgeCommandFactory();

        forgeCommandFactory.registerCommand(event.getServer(), new PlaceholderCommand());
    }
}
