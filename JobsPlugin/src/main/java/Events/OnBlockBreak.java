package Events;

import Jobs.Jobs;
import PlayerData.MaterialHandler;
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
import java.util.Arrays;

public class OnBlockBreak implements Listener {
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {

        //Cancel if event is cancelled
        if (e.isCancelled()) {
            return;
        }

        Block b = e.getBlock();
        Location bLocation = b.getLocation();

        Player p = e.getPlayer();
        Material m = b.getType();

        PlayerStats ps = PlayerStats.getPlayerStats(p);

        //Timber skill only works if broken block is wood and player's lumberjack level is 40 or higher
        if (ps.playerSkills.lumberjackLevel >= 40 && ps.materialHandler.isJobMaterial(p, b.getType())) {

            //The range of the timber skill
            int xRange = 5;
            int yRange = 15;
            int zRange = 5;

            ArrayList<Block> blocksToBreak = new ArrayList<>();

            //Subtract 0.5 * xRange/zRange of the x/z coordinates of the origin block so the origin block is in the center
            bLocation.add((int) ((float) -xRange / 2), 0, (int) ((float) -zRange / 2));

            //Y cord Loop
            for (int y = 0; y < yRange; y++) {
                //X cord Loop
                for (int x = 0; x < xRange; x++) {
                    //Z cord Loop
                    for (int z = 0; z < zRange; z++) {
                        //Add the block at the current coordinate to a list
                        blocksToBreak.add(bLocation.getBlock());
                        //Add 1 for the z cord
                        bLocation.add(0, 0, 1);
                    }
                    //Add 1 for the x cord and reset z cord to origin point * (0.5 * zRange)
                    bLocation.add(1, 0, -zRange);
                }
                //Add 1 for the y cord and reset x cord to origin point * (0.5 * xRange)
                bLocation.add(-xRange, 1, 0);
            }

            //Send all blocks in timber skill range to function
            run(p, blocksToBreak);
            return;
        }

        //Remove metadata if block was placed by placed
        if (b.hasMetadata("PlacedByPlayer")) {
            b.removeMetadata("PlacedByPlayer", Jobs.getInstance());
            return;
        }

        //Remove actions required if job material
        if (ps.materialHandler.isJobMaterial(p, m)) {
            ps.decreaseActionsRequired(ps.materialHandler.getMaterialValue(m));
        }
    }
    private void run(Player p, ArrayList<Block> blocksToBreak) {
        new BukkitRunnable() {

            //Current iteration
            private int i = 0;
            //How much should be subtracted from actionsRequired
            private double reward = 0;

            @Override
            public void run() {

                if (i >= blocksToBreak.size() - 1) {
                    p.sendMessage("Max Range");
                    Bukkit.getScheduler().cancelTask(getTaskId());
                }

                //Skip current block until it should be broken
                while (!PlayerStats.getPlayerStats(p).materialHandler.isJobMaterial(p, blocksToBreak.get(i).getType())) {
                    i++;
                }

                //Check if player should receive a reward for breaking blocks
                if (blocksToBreak.get(i).hasMetadata("PlacedByPlayer")) {
                    blocksToBreak.get(i).removeMetadata("PlacedByPlayer", Jobs.getInstance());
                } else {
                    reward += PlayerStats.getPlayerStats(p).materialHandler.getMaterialValue(blocksToBreak.get(i).getType());
                }

                //Break the block
                blocksToBreak.get(i).breakNaturally();

                i++;

                //Decrease actions required based on reward
                PlayerStats.getPlayerStats(p).decreaseActionsRequired(reward);
            }
        }.runTaskTimer(Jobs.getInstance(), 0, 3);
    }
}
