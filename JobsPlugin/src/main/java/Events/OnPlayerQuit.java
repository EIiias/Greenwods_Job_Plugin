package Events;

import Commands.BackpackCommand;
import PlayerData.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        PlayerStats.getPlayerStats(p).saveStatsYAML();
        PlayerStats.removePlayerStats(p);
    }
}
