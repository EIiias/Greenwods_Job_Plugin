package Events;

import Jobs.Jobs;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class OnBlockPlace implements Listener {
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        b.setMetadata("PlacedByPlayer", new FixedMetadataValue(Jobs.getInstance(), p.getName()));
    }
}
