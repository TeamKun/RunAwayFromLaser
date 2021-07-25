package net.kunmc.lab.runawayfromlaser.task;

import org.bukkit.scheduler.BukkitRunnable;

class MoveLaserTask extends BukkitRunnable {
    private final GenerateLaserTask generateLaserTask;
    double speedRatio = 0.85;

    MoveLaserTask(GenerateLaserTask generateLaserTask) {
        this.generateLaserTask = generateLaserTask;
    }

    @Override
    public void run() {
        generateLaserTask.origin.add(0, 0.2806 * speedRatio, 0.2806 * speedRatio);
    }
}
