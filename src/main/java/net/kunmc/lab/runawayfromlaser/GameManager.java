package net.kunmc.lab.runawayfromlaser;

import net.kunmc.lab.runawayfromlaser.config.Config;
import net.kunmc.lab.runawayfromlaser.laser.Laser;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {
    public LaserApi api;
    public boolean isStarted = false;
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

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.teleport(api.origin().clone().add(api.length() / 2, 1, -3));
        });

        final int[] count = {3};
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (count[0] != 0) {
                        p.sendTitle("開始まで" + count[0] + "秒", "", 0, 40, 0);
                    } else {
                        p.sendTitle("スタート!", "", 0, 20, 0);
                        api.setPaused(false);
                        isStarted = true;
                        this.cancel();
                    }
                });
                count[0]--;
            }
        }.runTaskTimer(RunAwayFromLaser.getInstance(), 0, 20);
    }

    public void pause() {
        api.setPaused(true);
    }

    public void resume() {
        api.setPaused(false);
    }

    public void stop() {
        api.cancel();

        Config config = Config.getInstance();
        this.api = Laser.create(config.stairInfo.origin, config.stairInfo.width);

        isStarted = false;
    }
}
