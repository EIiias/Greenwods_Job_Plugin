package Events;

import Jobs.Jobs;
import PlayerData.PlayerStats;
import net.md_5.bungee.api.ChatMessageType;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import Economy.Vault;
import Math.CustomMath;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnPlayerFish implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {

        //Check if player fished something
        if (e.getState().name().equals("CAUGHT_FISH")) {

            Player p = e.getPlayer();
            PlayerStats ps = PlayerStats.getPlayerStats(p);

            ps.lastFishingPosition = p.getLocation();

            //Detect auto fish
            run(p, p.getLocation());

            //Abort function if player is not fisher
            if (!ps.getJobName().equals("fisher")) {
                return;
            }

            Economy economy = Vault.getEconomy();

            double randomValue = CustomMath.randomNumber(1, 130);

            /*
            YES THIS PART IS RETARDED BUT IT WORKS

            Common: 56 of 130
            Uncommon: 44 of 130
            Rare: 20 of 130
            Epic: 8 of 130
            Legendary: 2 of 130
             */

            if (randomValue <= 2) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.getMaterialHandler().legendaryFish.get((int) CustomMath.randomNumber(ps.getMaterialHandler().legendaryFish.size() - 1, 0)));
                //ps.playerSkills.increaseFisgetMaterialHandler(
            } else if (randomValue > 2 && randomValue <= 10) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.getMaterialHandler().epicFish.get((int) CustomMath.randomNumber(ps.getMaterialHandler().epicFish.size() - 1, 0)));
                //ps.playerSkills.increaseFisherXP(p, 25.0);
            } else if (randomValue > 10 && randomValue <= 30) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.getMaterialHandler().rareFish.get((int) CustomMath.randomNumber(ps.getMaterialHandler().rareFish.size() - 1, 0)));
                //ps.playerSkills.increaseFisherXP(p, 10.0);
            } else if (randomValue > 30 && randomValue <= 74) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.getMaterialHandler().uncommonFish.get((int) CustomMath.randomNumber(ps.getMaterialHandler().uncommonFish.size() - 1, 0)));
                //ps.playerSkills.increaseFisherXP(p, 2.0);
            } else if (randomValue > 74) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.getMaterialHandler().commonFish.get((int) CustomMath.randomNumber(ps.getMaterialHandler().commonFish.size() - 1, 0)));
                //ps.playerSkills.increaseFisherXP(p, 0.5);
            }
        }
    }

    //Anti auto fish
    private void run(Player p, Location locationAtEvenTrigger) {
        new BukkitRunnable() {
            @Override
            public void run() {

                //Kicks player if player is flagged and still hasn't moved
                if (PlayerStats.getPlayerStats(p).autoFishFlag && locationAtEvenTrigger.equals(p.getLocation())) {
                    p.kickPlayer(ChatColor.YELLOW + "[Automatic] You have been kicked for suspected auto fish!");
                    return;
                }

                //Add flag if player doesn't move
                if (locationAtEvenTrigger.equals(p.getLocation())) {
                    PlayerStats.getPlayerStats(p).autoFishFlag = true;
                    p.sendMessage(ChatColor.DARK_RED + "Warning!" + ChatColor.YELLOW +  " You have been flagged for potential auto fish! Move to not get kicked!");
                } else {
                    PlayerStats.getPlayerStats(p).autoFishFlag = false;
                }
            }
        }.runTaskLater(Jobs.getInstance(), 6000); //Delay 5 Minute
    }
}


