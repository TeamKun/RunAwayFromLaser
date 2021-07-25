package net.kunmc.lab.runawayfromlaser.laser;

import org.bukkit.Location;

public class LaserApi {
    private final Laser laser;

    LaserApi(Laser laser) {
        this.laser = laser;
    }

    public void origin(Location origin) {
        laser.origin = origin.clone().add(0, 2.0, 0);
    }

    public Location origin() {
        return laser.origin;
    }

    public void length(int length) {
        laser.length = length;
    }

    public double length() {
        return laser.length;
    }

    public void speedRatio(double speedRatio) {
        laser.speedRatio = speedRatio;
    }

    public double speedRatio() {
        return laser.speedRatio;
    }

    public void laserGap(double gap) {
        laser.gap = gap;
    }

    public double laserGap() {
        return laser.gap;
    }

    public void laserSize(float size) {
        laser.size = size;
    }

    public double laserSize() {
        return laser.size;
    }
}
