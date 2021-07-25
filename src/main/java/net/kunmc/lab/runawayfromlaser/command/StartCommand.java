package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class StartCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        GameManager manager = GameManager.getInstance();

        if (manager.isStarted) {
            sender.sendMessage(ChatColor.RED + "すでに開始しています.");
            return;
        }

        manager.start();
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
