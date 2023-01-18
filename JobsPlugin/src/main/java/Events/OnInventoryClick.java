package Events;

import GUI.Inventories.SkillTree;
import PlayerData.PlayerSkills;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OnInventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        //Check if inventory is a skill tree inventory and cancel all click events in that case
        if (SkillTree.skillTreeInventoryList.containsKey(e.getInventory())) {

            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            SkillTree skillTreeInstance = SkillTree.skillTreeInventoryList.get(e.getInventory());

            if (e.getCurrentItem() != null) {
                if (!(e.getClickedInventory() instanceof PlayerInventory)){
                    if (e.getCurrentItem().getType() == Material.OAK_LOG) {
                        skillTreeInstance.lumberjackSkillTree();
                    } else if (e.getCurrentItem().getType() == Material.IRON_ORE) {

                    } else if (e.getCurrentItem().getType() == Material.COD) {

                    } else if (e.getCurrentItem().getType() == Material.WHEAT) {

                    } else if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                        e.getInventory().close();
                        new SkillTree(p);
                    }
                }
            }
        }
    }
}

