package com.envyful.papi.forge.commands;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.neoforge.chat.PageBuilder;
import com.envyful.api.type.UtilParse;
import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;
import net.minecraft.commands.CommandSource;

import java.util.ArrayList;

@Command(
        value = {
                "placeholderapi",
                "forgeplaceholderapi",
                "papi",
                "fpapi"
        }
)
@Permissible("papi.command")
public class PlaceholderCommand {

    @CommandProcessor
    public void onCommand(@Sender CommandSource sender, String[] args) {
        int page = 0;

        if (args.length > 0) {
            page = UtilParse.parseInt(args[0]).orElse(0);
        }

        PageBuilder.instance(PlaceholderManager.class)
                .pageSize(5)
                .mainColor("§e")
                .offColor("§b")
                .values(new ArrayList<>(PlaceholderFactory.getAllPlaceholderManagers()))
                .display(placeholderManager -> String.join(System.lineSeparator(), placeholderManager.getDescription()))
                .send(sender, page);
    }
}
