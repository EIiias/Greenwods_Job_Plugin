package Commands;

import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.openInventory(PlayerStats.backpackList.get(p));
            } else if (args.length == 1) {
                if (p.hasPermission("jobs.command.backpackSee")) {
                    if (PlayerStats.backpackList.get(Bukkit.getOfflinePlayer(args[0])) != null) {
                        OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                        if (!t.isOnline()) {
                            p.sendMessage(ChatColor.RED + "Für Offline Player gibt es noch keine Möglichkeit das Backpack im Spiel zu bearbeiten! Das kommt dann in Zukunft mal..." +
                                    " Bitte kurz ein Ticket erstellen oder einen Admin anschreiben, damit das manuel aus dem save stat file gelöscht werden kann!");
                        } else {
                            p.openInventory(PlayerStats.backpackList.get((Player) t));
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Backpack for " + Bukkit.getPlayer(args[0]).getName() + " not found!");
                    }
                }
            }
        }

        return true;
    }
}
