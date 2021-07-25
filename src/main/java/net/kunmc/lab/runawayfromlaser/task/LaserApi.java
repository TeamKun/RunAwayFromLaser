package net.kunmc.lab.runawayfromlaser.task;

import org.bukkit.Location;

public class LaserApi {
    private final GenerateLaserTask task;

    LaserApi(GenerateLaserTask task) {
        this.task = task;
    }

    public void origin(Location origin) {
        task.origin = origin.clone().add(0, 2.0, 0);
    }

    public Location origin() {
        return task.origin;
    }

    public void length(int length) {
        task.length = length;
    }

    public double length() {
        return task.length;
    }

    public void speedRatio(double ratio) {
        task.moveLaserTask.speedRatio = ratio;
    }

    public double speedRatio() {
        return task.moveLaserTask.speedRatio;
    }

    public void laserGap(double gap) {
        task.gap = gap;
    }

    public double laserGap() {
        return task.gap;
    }

    public void laserSize(float size) {
        task.laserSize = size;
    }

    public double laserSize() {
        return task.laserSize;
    }
}
