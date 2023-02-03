package Commands;

import Jobs.Jobs;
import PlayerData.MaterialHandler;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BackpackCommand implements CommandExecutor {

    public static HashMap<Player, Inventory> backpackList = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.openInventory(backpackList.get(p));
            } else if (args.length == 1) {
                if (p.hasPermission("jobs.command.backpackSee")) {
                    if (BackpackCommand.backpackList.get(Bukkit.getPlayer(args[0])) != null) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (PlayerStats.getPlayerStats(t) == null) {
                            p.sendMessage("Player not online! To edit player's item edit player stat's file");
                        } else {
                            p.openInventory(BackpackCommand.backpackList.get(t));
                        }
                    } else {
                        p.sendMessage("Backpack for " + Bukkit.getPlayer(args[0]).getName() + " not found!");
                    }
                }
            }
        }

        return true;
    }
}
