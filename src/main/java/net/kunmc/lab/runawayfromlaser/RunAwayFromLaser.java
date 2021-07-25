package net.kunmc.lab.runawayfromlaser;

import net.kunmc.lab.runawayfromlaser.command.CommandHandler;
import net.kunmc.lab.runawayfromlaser.config.Config;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class RunAwayFromLaser extends JavaPlugin {
    private static RunAwayFromLaser instance;

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
    }

    @Override
    public void onDisable() {
        Config.getInstance().saveConfig();
    }
}
