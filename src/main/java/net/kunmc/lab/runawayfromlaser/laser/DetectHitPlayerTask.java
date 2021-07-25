package net.kunmc.lab.runawayfromlaser.laser;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

class DetectHitPlayerTask extends BukkitRunnable {
    private final Laser laser;

    DetectHitPlayerTask(Laser laser) {
        this.laser = laser;
    }

    @Override
    public void run() {
        if (laser.isPaused) {
            return;
        }
        
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> !(p.getGameMode().equals(GameMode.CREATIVE)))
                .filter(p -> !(p.getGameMode().equals(GameMode.SPECTATOR)))
                .filter(p -> !p.isDead())
                .forEach(p -> {
                    double z = p.getLocation().getZ();
                    if (z < laser.origin.getZ()) {
                        p.setHealth(0.0);
                    }
                });
    }
}
