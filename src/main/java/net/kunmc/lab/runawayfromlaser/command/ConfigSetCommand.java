package net.kunmc.lab.runawayfromlaser.command;

import net.kunmc.lab.runawayfromlaser.GameManager;
import net.kunmc.lab.runawayfromlaser.listener.PlayerJumpListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ConfigSetCommand implements SubCommand {
    private final Map<String, BiConsumer<CommandSender, String>> configItemFuncMap = new HashMap<String, BiConsumer<CommandSender, String>>() {{
        put("speedRatio", ConfigSetCommand.this::speedRatio);
        put("gap", ConfigSetCommand.this::gap);
        put("laserSize", ConfigSetCommand.this::laserSize);
        put("paused", ConfigSetCommand.this::paused);
        put("invisible", ConfigSetCommand.this::invisible);
        put("shouldMobSpawn", ConfigSetCommand.this::shouldMobSpawn);
        put("laserDelay", ConfigSetCommand.this::laserDelay);
        put("mobSpawnProbability", ConfigSetCommand.this::mobSpawnProbability);
        put("shouldShowLaserPos", ConfigSetCommand.this::shouldShowLaserPos);
        put("mobSpawnOffset", ConfigSetCommand.this::mobSpawnOffset);
        put("canPlayersJump", ConfigSetCommand.this::canPlayersJump);
    }};

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "usage: /rafl config set <configItem> <value>");
            return;
        }

        if (configItemFuncMap.containsKey(args[0])) {
            configItemFuncMap.get(args[0]).accept(sender, args[1]);
        } else {
            sender.sendMessage(ChatColor.RED + "不明な項目です.");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completion = new ArrayList<>();

        if (args.length == 1) {
            completion.addAll(configItemFuncMap.keySet());
        }

        if (args.length == 2) {
            completion.add("<value>");
        }

        return completion;
    }

    private void speedRatio(CommandSender sender, String value) {
        try {
            GameManager.getInstance().api.speedRatio(Double.parseDouble(value));
            sender.sendMessage(ChatColor.GREEN + "speedRatioの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "speedRatioの値はDouble値で入力してください..");
        }
    }

    private void gap(CommandSender sender, String value) {
        try {
            GameManager.getInstance().api.laserGap(Double.parseDouble(value));
            sender.sendMessage(ChatColor.GREEN + "gapの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "gapの値はDouble値で入力してください.");
        }
    }

    private void laserSize(CommandSender sender, String value) {
        try {
            GameManager.getInstance().api.laserSize(Float.parseFloat(value));
            sender.sendMessage(ChatColor.GREEN + "laserSizeの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "laserSizeの値はFloat値で入力してください..");
        }
    }

    private void paused(CommandSender sender, String value) {
        try {
            GameManager.getInstance().api.setPaused(Boolean.parseBoolean(value));
            sender.sendMessage(ChatColor.GREEN + "pausedの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "pausedの値はBoolean値で入力してください.");
        }
    }

    private void invisible(CommandSender sender, String value) {
        try {
            GameManager.getInstance().api.setInvisible(Boolean.parseBoolean(value));
            sender.sendMessage(ChatColor.GREEN + "invisibleの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "invisibleの値はBoolean値で入力してください.");
        }
    }

    private void shouldMobSpawn(CommandSender sender, String value) {
        try {
            GameManager.getInstance().shouldMobSpawn = Boolean.parseBoolean(value);
            sender.sendMessage(ChatColor.GREEN + "shouldMobSpawnの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "shouldMobSpawnの値はBoolean値で入力してください.");
        }
    }

    private void laserDelay(CommandSender sender, String value) {
        try {
            GameManager.getInstance().delay = Integer.parseInt(value);
            sender.sendMessage(ChatColor.GREEN + "laserDelayの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "laserDelayの値はInteger値で入力してください.");
        }
    }

    private void mobSpawnProbability(CommandSender sender, String value) {
        try {
            GameManager.getInstance().mobSpawnProbability = Double.parseDouble(value);
            sender.sendMessage(ChatColor.GREEN + "mobSpawnProbabilityの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "mobSpawnProbabilityの値はDouble値で入力してください.");
        }
    }

    private void shouldShowLaserPos(CommandSender sender, String value) {
        try {
            GameManager.getInstance().shouldShowLaserPos = Boolean.parseBoolean(value);
            sender.sendMessage(ChatColor.GREEN + "shouldShowLaserPosの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "shouldShowLaserPosの値はBoolean値で入力してください.");
        }
    }

    private void mobSpawnOffset(CommandSender sender, String value) {
        try {
            GameManager.getInstance().mobSpawnOffset = Integer.parseInt(value);
            sender.sendMessage(ChatColor.GREEN + "mobSpawnOffsetの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "mobSpawnOffsetの値はInteger値で入力してください.");
        }
    }

    private void canPlayersJump(CommandSender sender, String value) {
        try {
            PlayerJumpListener.canPlayersJump = Boolean.parseBoolean(value);
            sender.sendMessage(ChatColor.GREEN + "canPlayersJumpの値を" + value + "に設定しました.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "canPlayersJumpの値はBoolean値で入力してください.");
        }
    }
}
