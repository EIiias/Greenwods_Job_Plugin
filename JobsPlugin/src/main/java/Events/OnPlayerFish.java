package Events;

import PlayerData.PlayerStats;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnPlayerFish implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {

        Player p = e.getPlayer();
        PlayerStats ps = PlayerStats.getPlayerStats(p);

        //Abort function if player is not fisher
        if (!ps.getJobName().equals("fisher")) {
            return;
        }

        Economy economy = Vault.getEconomy();

        //Check if player fished something
        if (e.getState().name().equals("CAUGHT_FISH")) {

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
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.materialHandler.legendaryFish.get((int) CustomMath.randomNumber(ps.materialHandler.legendaryFish.size() - 1, 0)));
                ps.playerSkills.increaseFisherXP(p, 75.0);
            } else if (randomValue > 2 && randomValue <= 10) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.materialHandler.epicFish.get((int) CustomMath.randomNumber(ps.materialHandler.epicFish.size() - 1, 0)));
                ps.playerSkills.increaseFisherXP(p, 25.0);
            } else if (randomValue > 10 && randomValue <= 30) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.materialHandler.rareFish.get((int) CustomMath.randomNumber(ps.materialHandler.rareFish.size() - 1, 0)));
                ps.playerSkills.increaseFisherXP(p, 10.0);
            } else if (randomValue > 30 && randomValue <= 74) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.materialHandler.uncommonFish.get((int) CustomMath.randomNumber(ps.materialHandler.uncommonFish.size() - 1, 0)));
                ps.playerSkills.increaseFisherXP(p, 2.0);
            } else if (randomValue > 74) {
                ((Item) Objects.requireNonNull(e.getCaught())).setItemStack(ps.materialHandler.commonFish.get((int) CustomMath.randomNumber(ps.materialHandler.commonFish.size() - 1, 0)));
                ps.playerSkills.increaseFisherXP(p, 0.5);
            }
        }
    }
}

