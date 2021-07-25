package net.kunmc.lab.runawayfromlaser.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

class DetectHitPlayerTask extends BukkitRunnable {
    private final GenerateLaserTask generateLaserTask;

    DetectHitPlayerTask(GenerateLaserTask generateLaserTask) {
        this.generateLaserTask = generateLaserTask;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> !(p.getGameMode().equals(GameMode.CREATIVE)))
                .filter(p -> !(p.getGameMode().equals(GameMode.SPECTATOR)))
                .filter(p -> !p.isDead())
                .forEach(p -> {
                    double z = p.getLocation().getZ();
                    if (z < generateLaserTask.origin.getZ()) {
                        p.setHealth(0.0);
                    }
                });
    }
}
