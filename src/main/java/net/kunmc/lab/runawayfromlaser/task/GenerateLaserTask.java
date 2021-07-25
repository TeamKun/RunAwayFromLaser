package net.kunmc.lab.runawayfromlaser.task;

import net.kunmc.lab.runawayfromlaser.RunAwayFromLaser;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GenerateLaserTask extends BukkitRunnable {
    Location origin;
    int length;
    double gap = 0.0625;
    float laserSize = 1.0F;
    MoveLaserTask moveLaserTask;
    DetectHitPlayerTask detectHitPlayerTask;

    public GenerateLaserTask(Location origin, int length) {
        this.origin = origin.clone().add(0, 2.0, 0);
        this.length = length;
    }

    public LaserApi laserApi() {
        return new LaserApi(this);
    }

    public LaserApi execute() {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();

        this.runTaskTimerAsynchronously(plugin, 0, 0);

        moveLaserTask = new MoveLaserTask(this);
        moveLaserTask.runTaskTimerAsynchronously(plugin, 0, 0);

        detectHitPlayerTask = new DetectHitPlayerTask(this);
        detectHitPlayerTask.runTaskTimer(plugin, 0, 4);

        return new LaserApi(this);
    }

    @Override
    public void run() {
        for (double x = -2; x < length + 2; x += gap) {
            origin.getWorld().spawnParticle(Particle.REDSTONE, origin.getX() + x, origin.getY(), origin.getZ(),
                    1, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, laserSize));
        }
    }
}
