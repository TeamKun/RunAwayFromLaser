package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetStairInfoCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤーから実行してください.");
            return;
        }
        Player p = ((Player) sender);

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "usage: /rafl setStairInfo <width>");
            return;
        }

        int width;
        try {
            width = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "<width>には正の整数を入力してください.");
            return;
        }

        if (width < 1) {
            sender.sendMessage(ChatColor.RED + "<width>には正の整数を入力してください.");
            return;
        }

        Block targetBlock = p.getTargetBlock(30);
        if (targetBlock == null || !(targetBlock.getBlockData() instanceof Stairs)) {
            sender.sendMessage(ChatColor.RED + "階段の右下の階段ブロックをターゲットしてください.");
            return;
        }
        Location origin = targetBlock.getLocation().toBlockLocation();

        Config.getInstance().stairInfo.origin = origin;
        Config.getInstance().stairInfo.width = width;

        sender.sendMessage(String.format(ChatColor.GREEN + "StairInfoの設定をorigin={x=%d, y=%d, z=%d}, width=%dに設定しました.",
                origin.getBlockX(), origin.getBlockY(), origin.getBlockZ(), width));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completion = new ArrayList<>();

        if (args.length == 1) {
            completion.add("<width>");
        }

        return completion;
    }
}
