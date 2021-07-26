package net.kunmc.lab.runawayfromlaser;

import net.kunmc.lab.runawayfromlaser.command.CommandHandler;
import net.kunmc.lab.runawayfromlaser.config.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public final class RunAwayFromLaser extends JavaPlugin {
    private static RunAwayFromLaser instance;
    public static final String teamName = "RAFLTeam";
    public static final String objectiveName = "Steps";

    public static RunAwayFromLaser getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        TabExecutor tabExecutor = new CommandHandler();
        getServer().getPluginCommand("rafl").setExecutor(tabExecutor);
        getServer().getPluginCommand("rafl").setTabCompleter(tabExecutor);

        Config.getInstance().loadConfig();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        scoreboard.registerNewObjective(objectiveName, "dummy", Component.text("step"))
                .setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    @Override
    public void onDisable() {
        Config.getInstance().saveConfig();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        scoreboard.getObjective(objectiveName).unregister();
    }
}
