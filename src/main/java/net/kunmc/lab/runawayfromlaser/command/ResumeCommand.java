package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.GameManager;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ResumeCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        LaserApi api = GameManager.getInstance().api;

        if (!api.isPaused()) {
            sender.sendMessage(ChatColor.RED + "レーザーはすでに作動中です.");
            return;
        }

        api.setPaused(false);
        sender.sendMessage(ChatColor.GREEN + "レーザーの作動を再開させました.");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
