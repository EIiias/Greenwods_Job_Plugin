package Events;

import Jobs.Jobs;
import PlayerData.PlayerStats;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;

public class OnPlayerCraft implements Listener {
    @EventHandler
    public void onPlayerCraft(CraftItemEvent e) {

        PlayerStats ps = PlayerStats.getPlayerStats((Player) e.getWhoClicked());

        if (ps.playerSkills.lumberjackLevel >= 20) {
            if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.OAK_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.OAK_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.SPRUCE_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.SPRUCE_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.BIRCH_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.BIRCH_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.JUNGLE_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.JUNGLE_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.ACACIA_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.ACACIA_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.DARK_OAK_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.DARK_OAK_PLANKS, 6));

            } else if (Objects.equals(e.getInventory().getResult(), new ItemStack(Material.MANGROVE_PLANKS, 4))) {
                e.getInventory().setResult(new ItemStack(Material.MANGROVE_PLANKS, 6));
            }
        }
    }
}
