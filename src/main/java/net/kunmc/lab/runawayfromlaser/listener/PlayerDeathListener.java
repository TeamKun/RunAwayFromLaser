package net.kunmc.lab.runawayfromlaser.listener;

import net.kunmc.lab.runawayfromlaser.GameManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (GameManager.getInstance().isStarted) {
            e.getEntity().setGameMode(GameMode.SPECTATOR);
        }
    }
}
