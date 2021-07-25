package com.envyful.papi.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.concurrency.ForgeUpdateBuilder;
import com.envyful.papi.api.local.LocalManagementLoader;
import com.envyful.papi.forge.commands.PlaceholderCommand;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.bstats.forge.Metrics;

import java.nio.file.Paths;

@Mod(
        modid = "forgeplaceholderapi",
        name = "Forge PlaceholderAPI",
        version = ForgePlaceholderAPI.VERSION,
        acceptableRemoteVersions = "*"
)
public class ForgePlaceholderAPI {

    public static final String VERSION = "0.0.1";

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        LocalManagementLoader.init("mods/ForgePlaceholderAPI/");

        Metrics metrics = new Metrics(
                Loader.instance().activeModContainer(),
                event.getModLog(),
                Paths.get("config/"),
                12198
        );

        ForgeUpdateBuilder.instance()
                .name("ForgePlaceholderAPI")
                .version(VERSION)
                .requiredPermission("papi.update.notify")
                .owner("Pixelmon-Development")
                .repo("ForgePlaceholderAPI")
                .start();
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        ForgeCommandFactory forgeCommandFactory = new ForgeCommandFactory();

        forgeCommandFactory.registerCommand(event.getServer(), new PlaceholderCommand());
    }
}
