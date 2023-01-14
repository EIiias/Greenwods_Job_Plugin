package GUI.Scoreboards;

import PlayerData.PlayerStats;
import Math.CustomMath;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import Economy.Vault;

public class JobScoreboard {

    private static Scoreboard invisibleScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public static void updateJobScoreboard(Player p) {

        Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = s.registerNewObjective("main", "main", "§6Job Info");

        PlayerStats ps = PlayerStats.getPlayerStats(p);
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        Economy economy = Vault.getEconomy();

        o.getScore("§a").setScore(4);
        o.getScore("Occupation: " + ChatColor.GREEN  + ps.getJobName()).setScore(3);
        //o.getScore("Woodcoins: " + ChatColor.GREEN + CustomMath.truncate(economy.getBalance(p), 2)).setScore(2);
        o.getScore("§a").setScore(2);
        o.getScore(ChatColor.GOLD + "Greenwoods.at").setScore(1);

        p.setScoreboard(s);
    }

    public static void hideJobScoreboard(Player p) {
        p.setScoreboard(invisibleScoreboard);
    }
}


