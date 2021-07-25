package net.kunmc.lab.runawayfromlaser.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler implements TabExecutor {
    private final Map<String, SubCommand> subCmdMap = new HashMap<String, SubCommand>() {{
        put("createStair", new CreateStairCommand());
        put("setStairInfo", new SetStairInfoCommand());
        put("start", new StartCommand());
        put("stop", new StopCommand());
    }};

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            return false;
        }

        if (subCmdMap.containsKey(args[0])) {
            subCmdMap.get(args[0]).execute(sender, nextArgs(args));
        } else {
            sender.sendMessage(ChatColor.RED + "不明なコマンドです.");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completion = new ArrayList<>();

        if (args.length == 1) {
            completion.addAll(subCmdMap.keySet());
        }

        if (args.length > 1 && subCmdMap.containsKey(args[0])) {
            completion.addAll(subCmdMap.get(args[0]).tabComplete(sender, nextArgs(args)));
        }

        return completion.stream().filter(x -> x.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }

    public static String[] nextArgs(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }
}
