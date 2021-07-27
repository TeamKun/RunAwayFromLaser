package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.GameManager;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ConfigShowCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        GameManager manager = GameManager.getInstance();
        LaserApi api = manager.api;
        Location origin = api.origin();

        sender.sendMessage(format("stairInfo:{origin{x:%d, y:%d, z:%d}, width:%d}", origin.getBlockX(), origin.getBlockY(), origin.getBlockZ(), api.length()));
        sender.sendMessage(format("speedRatio:%f", api.speedRatio()));
        sender.sendMessage(format("gap:%f", api.laserGap()));
        sender.sendMessage(format("laserSize:%f", api.laserSize()));
        sender.sendMessage(format("paused:%b", api.isPaused()));
        sender.sendMessage(format("invisible:%b", api.isInvisible()));
        sender.sendMessage(format("shouldMobSpawn:%b", manager.shouldMobSpawn));
        sender.sendMessage(format("laserDelay:%d", manager.delay));
        sender.sendMessage(format("mobSpawnProbability:%f", manager.mobSpawnProbability));
        sender.sendMessage(format("shouldShowLaserPos:%b", manager.shouldShowLaserPos));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    private String format(String format, Object... args) {
        return ChatColor.GREEN + String.format(format, args);
    }
}
