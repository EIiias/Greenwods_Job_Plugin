package Events;

import GUI.Scoreboards.JobScoreboard;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class OnPlayerInteract extends @NotNull ItemStack implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.WRITTEN_BOOK) {
                ItemStack mainHandItem = e.getItem();
                Plugin pl = Bukkit.getPluginManager().getPlugin("Greenwoods");
                assert pl != null;
                if (mainHandItem.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(pl, "JobReset"), PersistentDataType.INTEGER)) {
                    e.setCancelled(true);
                    Player p = e.getPlayer();
                    PlayerStats ps = PlayerStats.getPlayerStats(p);
                    if (!ps.getJobName().equals("unemployed")) {
                        ps.setJobName("unemployed");
                        JobScoreboard.updateJobScoreboard(p);
                        JobScoreboard.hideJobScoreboard(p);
                        p.getInventory().removeItem(mainHandItem.asOne());
                        p.sendMessage(ChatColor.GREEN + "Your job was reset! Use /job select <occupation> to chose a new job!");
                    } else {
                        p.sendMessage(ChatColor.RED + "You already have no job! Use /job select <occupation> to chose a job!");
                    }
                }
            }
        }
    }
}
