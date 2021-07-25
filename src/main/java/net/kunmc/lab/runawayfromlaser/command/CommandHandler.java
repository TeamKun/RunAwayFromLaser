package net.kunmc.lab.runawayfromlaser.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CommandHandler implements TabExecutor {
    private final Map<String, SubCommand> subCmdMap = new HashMap<String, SubCommand>() {{
    }};

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }

    public static String[] nextArgs(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }
}
