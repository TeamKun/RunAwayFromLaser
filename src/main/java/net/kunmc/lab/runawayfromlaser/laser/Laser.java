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
    GenerateLaserTask generateLaserTask;
    MoveLaserTask moveLaserTask;
    DetectHitPlayerTask detectHitPlayerTask;

    public LaserApi execute() {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();

        generateLaserTask = new GenerateLaserTask(this);
        generateLaserTask.runTaskTimerAsynchronously(plugin, 0, 0);

        moveLaserTask = new MoveLaserTask(this);
        moveLaserTask.runTaskTimerAsynchronously(plugin, 0, 0);

        detectHitPlayerTask = new DetectHitPlayerTask(this);
        detectHitPlayerTask.runTaskTimer(plugin, 0, 4);

        return new LaserApi(this);
    }
}
