package net.kunmc.lab.runawayfromlaser.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigCommand implements SubCommand {
    private final Map<String, SubCommand> subCommandMap = new HashMap<String, SubCommand>() {{
        put("show", new ConfigShowCommand());
        put("set", new ConfigSetCommand());
    }};

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "usage:\n" +
                    "/rafl config show\n" +
                    "/rafl config set <value>");
            return;
        }

        if (subCommandMap.containsKey(args[0])) {
            subCommandMap.get(args[0]).execute(sender, CommandHandler.nextArgs(args));
        } else {
            sender.sendMessage(ChatColor.RED + "不明なコマンドです.");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completion = new ArrayList<>();

        if (args.length == 1) {
            completion.addAll(subCommandMap.keySet());
        }

        if (args.length > 1 && subCommandMap.containsKey(args[0])) {
            completion.addAll(subCommandMap.get(args[0]).tabComplete(sender, CommandHandler.nextArgs(args)));
        }

        return completion;
    }
}
