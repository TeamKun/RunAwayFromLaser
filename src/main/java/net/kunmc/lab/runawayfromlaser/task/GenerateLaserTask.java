package net.kunmc.lab.runawayfromlaser.task;

import net.kunmc.lab.runawayfromlaser.RunAwayFromLaser;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GenerateLaserTask extends BukkitRunnable {
    final Location origin;
    final int length;

    public GenerateLaserTask(Location origin, int length) {
        this.origin = origin.clone().add(0, 2.0, 0);
        this.length = length;
    }

    public void execute() {
        JavaPlugin plugin = RunAwayFromLaser.getInstance();
        this.runTaskTimerAsynchronously(plugin, 0, 0);
        new MoveLaserTask(this).runTaskTimerAsynchronously(plugin, 0, 0);
        new DetectHitPlayerTask(this).runTaskTimer(plugin, 0, 4);
    }

    @Override
    public void run() {
        for (double x = -2; x < length + 2; x += 0.0625) {
            origin.getWorld().spawnParticle(Particle.REDSTONE, origin.getX() + x, origin.getY(), origin.getZ(),
                    1, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1.0F));
        }
    }
}
