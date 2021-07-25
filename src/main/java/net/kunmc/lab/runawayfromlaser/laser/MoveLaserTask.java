package net.kunmc.lab.runawayfromlaser.laser;

import org.bukkit.scheduler.BukkitRunnable;

class MoveLaserTask extends BukkitRunnable {
    private final Laser laser;
    private final double baseSpeed = 0.2806;

    MoveLaserTask(Laser laser) {
        this.laser = laser;
    }

    @Override
    public void run() {
        if (!laser.isPaused) {
            laser.origin.add(0, baseSpeed * laser.speedRatio, baseSpeed * laser.speedRatio);
        }
    }
}
