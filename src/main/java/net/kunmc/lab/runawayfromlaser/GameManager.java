package net.kunmc.lab.runawayfromlaser;

import net.kunmc.lab.runawayfromlaser.config.Config;
import net.kunmc.lab.runawayfromlaser.laser.Laser;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameManager {
    public LaserApi api;
    public boolean isStarted = false;
    public int delay = 100;
    private BukkitTask countTask;
    private BukkitTask laserStartTask;
    private static final GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    private GameManager() {
        Config config = Config.getInstance();
        this.api = Laser.create(config.stairInfo.origin, config.stairInfo.width);
    }

    public void start() {
        Bukkit.getWorlds().forEach(w -> {
            w.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 0);
        });

        setWall(Material.CHISELED_QUARTZ_BLOCK);

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.teleport(api.origin().add(api.length() / 2, 1, -8));
        });

        isStarted = true;

        final int[] count = {10};
        countTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (count[0] != 0) {
                        p.sendTitle("開始まで" + count[0] + "秒", "", 0, 40, 0);
                    } else {
                        setWall(Material.AIR);
                        p.sendTitle("スタート!",
                                ChatColor.RED + "" + delay / 20 + "秒後レーザーが後ろから追いかけてきます", 0, 40, 20);

                        this.cancel();
                    }
                });
                count[0]--;
            }
        }.runTaskTimer(RunAwayFromLaser.getInstance(), 0, 20);

        laserStartTask = new BukkitRunnable() {
            @Override
            public void run() {
                api.setPaused(false);
                api.setInvisible(false);
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.sendTitle("", ChatColor.RED + "レーザーが後ろから追いかけてきました", 0, 30, 20);
                });
            }
        }.runTaskLater(RunAwayFromLaser.getInstance(), count[0] * 20L + delay);
    }

    private void setWall(Material material) {
        Location origin = api.origin();
        for (int y = 0; y < 2; y++) {
            for (int z = 0; z < 4; z++) {
                origin.clone().add(-1, y, z).getBlock().setType(material);
                origin.clone().add(api.length(), y, z).getBlock().setType(material);
            }

            for (int x = -1; x < api.length(); x++) {
                origin.clone().add(x, y, 0).getBlock().setType(material);
            }
        }
    }

    public void pause() {
        api.setPaused(true);
    }

    public void resume() {
        api.setPaused(false);
    }

    public void stop() {
        api.cancel();
        countTask.cancel();
        laserStartTask.cancel();

        Config config = Config.getInstance();
        this.api = Laser.create(config.stairInfo.origin, config.stairInfo.width);

        isStarted = false;
    }
}
