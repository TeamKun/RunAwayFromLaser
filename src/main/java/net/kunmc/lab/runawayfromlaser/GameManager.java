package net.kunmc.lab.runawayfromlaser;

import net.kunmc.lab.runawayfromlaser.config.Config;
import net.kunmc.lab.runawayfromlaser.laser.Laser;
import net.kunmc.lab.runawayfromlaser.laser.LaserApi;
import net.kunmc.lab.runawayfromlaser.util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public LaserApi api;
    public boolean isStarted = false;
    public int delay = 5;
    private final List<BukkitTask> taskList = new ArrayList<>();
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
        taskList.add(new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (count[0] != 0) {
                        p.sendTitle("開始まで" + count[0] + "秒", "", 0, 40, 0);
                    } else {
                        setWall(Material.AIR);
                        p.sendTitle("スタート!",
                                ChatColor.RED + "" + delay + "秒後レーザーが後ろから追いかけてきます", 0, 40, 20);

                        taskList.add(new BukkitRunnable() {
                            @Override
                            public void run() {
                                api.setPaused(false);
                                api.setInvisible(false);
                                Bukkit.getOnlinePlayers().forEach(p -> {
                                    p.sendTitle("", ChatColor.RED + "レーザーが後ろから追いかけてきました", 0, 30, 20);
                                });
                            }
                        }.runTaskLater(RunAwayFromLaser.getInstance(), delay * 20L));

                        this.cancel();
                    }
                });
                count[0]--;
            }
        }.runTaskTimer(RunAwayFromLaser.getInstance(), 0, 20));

        taskList.add(new BukkitRunnable() {
            @Override
            public void run() {
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                Objective objective = scoreboard.getObjective(RunAwayFromLaser.objectiveName);

                Bukkit.getOnlinePlayers().forEach(p -> {
                    if (Util.isCreativeOrSpectator(p)) {
                        return;
                    }

                    Block b = p.getLocation().add(0, -0.3, 0).getBlock();
                    if (b.getType().equals(Material.QUARTZ_STAIRS)) {
                        objective.getScore(p.getName()).setScore(b.getY() + 2032);
                        Bukkit.getLogger().info("a");
                    }
                });
            }
        }.runTaskTimerAsynchronously(RunAwayFromLaser.getInstance(), 0, 0));
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
        taskList.forEach(BukkitTask::cancel);
        taskList.clear();

        Config config = Config.getInstance();
        this.api = Laser.create(config.stairInfo.origin, config.stairInfo.width);

        isStarted = false;
    }
}
