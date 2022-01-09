package com.envyful.papi.forge.commands;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.PageBuilder;
import com.envyful.api.type.UtilParse;
import com.envyful.papi.api.PlaceholderFactory;
import com.envyful.papi.api.PlaceholderManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Command(
        value = "placeholderapi",
        aliases = {
                "forgeplaceholderapi",
                "papi",
                "fpapi"
        }
)
@Permissible("papi.command")
public class PlaceholderCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSender sender, String[] args) {
        if (args.length > 0 && args[0].equals("ADMIN_DEBUG")) {
            StringBuilder builder = new StringBuilder();
            for (PlaceholderManager<?> allPlaceholderManager : PlaceholderFactory.getAllPlaceholderManagers()) {
                builder.append(String.join(System.lineSeparator(), allPlaceholderManager.getAdminDescription())).append(System.lineSeparator());
            }

            File file = new File("config/test.txt");

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(builder.toString());
                myWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            sender.sendMessage(new TextComponentString("Written to file"));
            return;
        }

        int page = 0;

        if (args.length > 0) {
            page = UtilParse.parseInteger(args[0]).orElse(0);
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
