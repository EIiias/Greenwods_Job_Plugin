package Events;

import PlayerData.PlayerStats;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import Economy.Vault;
import Math.CustomMath;

import java.util.ArrayList;

public class OnPlayerFish implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {

        Player p = e.getPlayer();
        PlayerStats ps = PlayerStats.getPlayerStats(p);

        if (!ps.getJobName().equals("fisher")) {
            return;
        }

        Economy economy = Vault.getEconomy();

        if (e.getState().name().equals("CAUGHT_FISH")) {
            double randomVal = CustomMath.randomNumber(90, 110) / 100;
            economy.depositPlayer(p, randomVal);
        }
    }
}

