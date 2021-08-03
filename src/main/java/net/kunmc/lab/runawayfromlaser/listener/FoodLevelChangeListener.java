package net.kunmc.lab.runawayfromlaser.listener;

import net.kunmc.lab.runawayfromlaser.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (GameManager.getInstance().isStarted) {
            e.setFoodLevel(20);
        }
    }
}
