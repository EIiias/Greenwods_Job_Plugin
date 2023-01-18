package Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.plugin.Plugin;

public class OnStructureGrow implements Listener {

    //Removes "PlacedByPlayer" metadata from all blocks
    @EventHandler
    public void onStructureGrow(StructureGrowEvent e) {
        //Remove metadata for all blocks when f.e. a tree grows
        Plugin pl = Bukkit.getPluginManager().getPlugin("Jobs");
        for (int i = 0; i < e.getBlocks().size(); i++) {
            if (e.getBlocks().get(i).hasMetadata("PlacedByPlayer")); {
                e.getBlocks().get(i).removeMetadata("PlacedByPlayer", pl);
            }
        }
    }
}
