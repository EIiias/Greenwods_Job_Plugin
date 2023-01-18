package Events;

import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        PlayerStats ps = new PlayerStats(p);

        //Create [PluginFolder]/JobsPlugin/players/[Player UUID] directory if nonexistent
        if (!new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId()).exists()) {
            File pf = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId());
            pf.mkdir();
        }

        //Copy default stats.ini file in [Player UUID] directory if stats.ini is nonexistent
        if (!new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId() + "/stats.ini").exists()) {
            File statsSrc = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/stats.ini");
            File statsPlayer = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId() + "/stats.ini");
            //Create Stat instance for player
            try {
                Files.copy(statsSrc.toPath(), statsPlayer.toPath());
            } catch (IOException ex) {
                System.out.println("Copying the default Player Stats file to the player folder failed!");
                throw new RuntimeException(ex);
            }
        } else {
            ps.loadPlayerStatsToRam(ps); //Load stats if available
        }
    }
}
