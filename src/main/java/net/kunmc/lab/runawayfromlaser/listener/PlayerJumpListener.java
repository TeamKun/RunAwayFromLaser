package net.kunmc.lab.runawayfromlaser.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kunmc.lab.runawayfromlaser.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerJumpListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJump(PlayerJumpEvent e) {
        if (GameManager.getInstance().isStarted) {
            e.setCancelled(true);
        }
    }
}
