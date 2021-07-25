package net.kunmc.lab.runawayfromlaser.config;

import net.kunmc.lab.runawayfromlaser.RunAwayFromLaser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    public StairInfo stairInfo;

    private static final Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    private Config() {
    }

    public void loadConfig() {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        instance.stairInfo = new StairInfo();
        instance.stairInfo.origin = config.getLocation("stairInfo.origin");
        instance.stairInfo.width = config.getInt("stairInfo.width");
    }

    public void saveConfig() {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();
        FileConfiguration config = plugin.getConfig();

        config.set("stairInfo.origin", stairInfo.origin);
        config.set("stairInfo.width", stairInfo.width);

        plugin.saveConfig();
    }
}
