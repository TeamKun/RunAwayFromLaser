package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.GameManager;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class PauseCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        LaserApi api = GameManager.getInstance().api;

        if (api.isPaused()) {
            sender.sendMessage(ChatColor.RED + "レーザーはすでに一時停止中です.");
            return;
        }

        api.setPaused(true);
        sender.sendMessage(ChatColor.GREEN + "レーザーを一時停止にしました.");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
