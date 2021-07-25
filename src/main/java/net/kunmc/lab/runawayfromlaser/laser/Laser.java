package net.kunmc.lab.runawayfromlaser.laser;

import net.kunmc.lab.runawayfromlaser.RunAwayFromLaser;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Laser {
    Location origin;
    int length;
    double gap = 0.0625;
    float size = 1.0F;
    double speedRatio = 0.85;
    boolean isPaused = true;
    boolean isInvisible = true;
    GenerateLaserTask generateLaserTask;
    MoveLaserTask moveLaserTask;
    DetectHitPlayerTask detectHitPlayerTask;

    private Laser(Location origin, int length) {
        this.origin = origin.clone().add(0, 2, 0);
        this.length = length;
    }

    public static LaserApi create(Location origin, int length) {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();
        Laser laser = new Laser(origin, length);

        laser.generateLaserTask = new GenerateLaserTask(laser);
        laser.generateLaserTask.runTaskTimerAsynchronously(plugin, 0, 0);

        laser.moveLaserTask = new MoveLaserTask(laser);
        laser.moveLaserTask.runTaskTimerAsynchronously(plugin, 0, 0);

        laser.detectHitPlayerTask = new DetectHitPlayerTask(laser);
        laser.detectHitPlayerTask.runTaskTimer(plugin, 0, 4);

        return new LaserApi(laser);
    }
}
