package net.kunmc.lab.runawayfromlaser.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kunmc.lab.runawayfromlaser.GameManager;
import net.kunmc.lab.runawayfromlaser.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerJumpListener implements Listener {
    public static boolean canPlayersJump = false;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJump(PlayerJumpEvent e) {
        if (Util.isCreativeOrSpectator(e.getPlayer())) {
            return;
        }

        if (!canPlayersJump && GameManager.getInstance().isStarted) {
            e.setCancelled(true);
        }
    }
}
