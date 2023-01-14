package Events;

import GUI.Inventories.SkillTree;
import Jobs.Jobs;
import PlayerData.PlayerStats;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnInventoryClose implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        if (SkillTree.skillTreeInventoryList.containsKey(e.getInventory())) {
            SkillTree skillTreeInstance = SkillTree.skillTreeInventoryList.get(e.getInventory());
            SkillTree.skillTreeInventoryList.remove(e.getInventory());
            skillTreeInstance = null;
        }
    }
}
