package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.RunAwayFromLaser;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateStairCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤーから実行してください.");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "usage: /rafl createStair <width>");
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

        Location loc = ((Player) sender).getLocation();
        new CreateStairTask(loc, width).runTaskAsynchronously(RunAwayFromLaser.getInstance());

        sender.sendMessage(String.format(ChatColor.GREEN + "幅%dブロックの階段の生成を開始します.", width));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completion = new ArrayList<>(Collections.emptyList());

        if (args.length == 1) {
            completion.add("<width>");
        }

        return completion;
    }

    private static class CreateStairTask extends BukkitRunnable {
        Location basePoint;
        int width;
        BlockData stair = Material.QUARTZ_STAIRS.createBlockData(blockData -> {
            Stairs stairs = ((Stairs) blockData);
            stairs.setFacing(BlockFace.SOUTH);
        });
        int count = 1;

        public CreateStairTask(Location basePoint, int width) {
            this.basePoint = basePoint.clone();
            this.width = width;
            this.basePoint.setY(0);
        }

        @Override
        public void run() {
            int z = 0;
            for (int y = -2032; y < 2032; y++) {
                new SetLineTask(y, z).runTaskLater(RunAwayFromLaser.getInstance(), count);
                z++;
                count++;
            }
        }

        private class SetLineTask extends BukkitRunnable {
            int y;
            int z;

            SetLineTask(int y, int z) {
                this.y = y;
                this.z = z;
            }

            @Override
            public void run() {
                for (int x = 0; x < width; x++) {
                    basePoint.clone().add(x, y, z).getBlock().setBlockData(stair);
                }
            }
        }

    }
}
