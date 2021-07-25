package net.kunmc.lab.runawayfromlaser.laser;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

class GenerateLaserTask extends BukkitRunnable {
    private final Laser laser;

    GenerateLaserTask(Laser laser) {
        this.laser = laser;
    }

    @Override
    public void run() {
        for (double x = -2; x < laser.length + 2; x += laser.gap) {
            laser.origin.getWorld().spawnParticle(Particle.REDSTONE,
                    laser.origin.getX() + x, laser.origin.getY(), laser.origin.getZ(),
                    1, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, laser.size));
        }
    }
}
