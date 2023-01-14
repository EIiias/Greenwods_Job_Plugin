package Events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.plugin.Plugin;

public class OnCropGrow implements Listener {

    //Removes "PlacedByPlayer" metadata from all blocks
    @EventHandler
    public void onPlantGrowth(BlockGrowEvent e) {

        Plugin pl = Bukkit.getPluginManager().getPlugin("Jobs");
        Block b = e.getBlock();

        if (b.hasMetadata("PlacedByPlayer")); {
            b.removeMetadata("PlacedByPlayer", pl);
        }
    }
}
