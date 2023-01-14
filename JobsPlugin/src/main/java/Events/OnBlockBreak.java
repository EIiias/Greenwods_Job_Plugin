package Events;

import Jobs.Jobs;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class OnBlockBreak implements Listener {
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {

        if (e.isCancelled()) {
            return;
        }

        Block b = e.getBlock();

        Player p = e.getPlayer();
        Material m = b.getType();

        PlayerStats ps = PlayerStats.playerStats.get(p.getUniqueId());

        if (ps.playerSkills.lumberjackLevel >= 40) {
            run(p, e.getBlock().getLocation());
        }

        if (b.hasMetadata("PlacedByPlayer")) {
            b.removeMetadata("PlacedByPlayer", Jobs.getInstance());
            return;
        }

        if (ps.materialHandler.isJobMaterial(p, m)) {
            ps.decreaseActionsRequired(ps.materialHandler.getMaterialValue(m));
        }
    }

    private void run(Player p, Location bLocation) {
        new BukkitRunnable() {
            public void run() {

                ArrayList<Block> blocksToBreak = new ArrayList<>();

                p.sendMessage("Event triggered");

                if (bLocation.getBlock().getType().equals(Material.OAK_LOG)) {
                    blocksToBreak.add(bLocation.getBlock());
                    Material currentWood = Material.OAK_WOOD;

                    p.sendMessage("Should be added to List");

                    if (bLocation.add(1, 0, 0).getBlock().getType().equals(currentWood)) {
                        blocksToBreak.add(bLocation.add(1, 0, 0).getBlock());
                    }
                    if (bLocation.add(-1, 0, 0).getBlock().getType().equals(currentWood)) {
                        blocksToBreak.add(bLocation.add(-1, 0, 0).getBlock());
                    }
                    if (bLocation.add(0, 1, 0).getBlock().getType().equals(currentWood)) {
                        p.sendMessage("Vertical added");
                        blocksToBreak.add(bLocation.add(0, 1, 0).getBlock());
                    }
                    if (bLocation.add(0, -1, 0).getBlock().getType().equals(currentWood)) {
                        blocksToBreak.add(bLocation.add(0, -1, 0).getBlock());
                    }
                    if (bLocation.add(0, 0, 1).getBlock().getType().equals(currentWood)) {
                        blocksToBreak.add(bLocation.add(0, 0, 1).getBlock());
                    }
                    if (bLocation.add(0, 0, -1).getBlock().getType().equals(currentWood)) {
                        blocksToBreak.add(bLocation.add(0, 0, -1).getBlock());
                    }
                }

                for (Block b : blocksToBreak) {
                    b.breakNaturally();
                }
            }
        }.runTaskAsynchronously(Jobs.getInstance());
    }
}
