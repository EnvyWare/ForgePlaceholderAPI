
# ForgePlaceholderAPI [![](https://jitpack.io/v/Pixelmon-Development/ForgePlaceholderAPI.svg)](https://jitpack.io/#Pixelmon-Development/ForgePlaceholderAPI) [![Downloads](https://img.shields.io/github/downloads/EnvyWare/ForgePlaceholderAPI/total.svg)](https://github.com/EnvyWare/ForgePlaceholderAPI/releases)
ForgePlaceholderAPI allows for mods/plugins to display data In things such as menus designed In [ForgeMenus](https://github.com/EnvyWare/ForgeMenus). This merely serves as the bridge to facilitate the data exchange.
# Installation Instructions
Head over to releases and grab the version that corresponds to the Minecraft server you are running:

For 1.12.2 - `ForgePlaceholderAPI-Forge-x.x.x-1.12.2.jar`
For 1.16.5 - `ForgePlaceholderAPI-Forge-x.x.x-1.16.5.jar`

Install this Into your `/mods` folder.

Once you have restarted your server, you will see a new folder In `/mods` called ForgePlaceholderAPI. This will be used In the next section.

# Configuration Instructions
ForgePlaceholderAPI **on its own** does not do much of anything. ForgePlaceholderAPI makes use of extensions to communicate with other things and facilitate the exchange of relevant data.

Head over to [ForgePlaceholderAPI-Extensions](https://github.com/EnvyWare/ForgePlaceholderAPI-Extensions) to get some addons for this!

Extensions Available:

 - CMI - 1.12.2 - Requires running Magma/Mohist/Other Spigot Hybrid
 - ForgeEconomies - 1.12.2 & 1.16.5 - Requires [ForgeEconomies](https://github.com/EnvyWare/ForgeEconomies)
 - ForgePlaceholders - 1.12.2 & 1.16.5
 - LuckPerms - 1.12.2 - Requires running Sponge or Magma/Mohist/Other Spigot Hybrid
 - PokeTracker - 1.12.2 - Requires [PokeTracker](https://github.com/EnvyWare/PokeTracker)
 - Reforged - 1.12.2 - Requires [Pixelmon Reforged](https://reforged.gg)
 - SimpleVoteRewards - 1.12.2 - Requires [SimpleVoteRewards](https://github.com/EnvyWare/SimpleVoteRewards)
 - SpigotPlaceholders - 1.12.2 & 1.16.5 - Requires running Magma/Mohist/Other Spigot Hybrid
 - UltimatePokeBuilder - 1.12.2 - Requires [UltimatePokeBuilder](https://github.com/EnvyWare/UltimatePokeBuilder)

Once you have downloaded the extensions you want, simply put them In `/mods/ForgePlaceholderAPI` and restart your server!

For more details about each extension and available placeholders, head over to [ForgePlaceholderAPI-Extensions](https://github.com/EnvyWare/ForgePlaceholderAPI-Extensions)

Want to use placeholders from ForgePlaceholderAPI In Spigot stuff like DeluxeMenus? Grab [SpigotPAPIBridge](https://github.com/EnvyWare/SpigotPAPIBridge).

# Spigot Clarification
If you want to use Spigot Placeholders in things like ForgeMenus (Spigot Placeholders in Forge stuff), you will need ForgePlaceholderAPI + SpigotPlaceholders extension

If you want to use Forge Placeholders in things like DeluxeMenus (Forge Placeholders in Spigot stuff), you will need [SpigotPAPIBridge](https://github.com/EnvyWare/SpigotPAPIBridge)
