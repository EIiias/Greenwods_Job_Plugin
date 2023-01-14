package Commands;

import GUI.Inventories.SkillTree;
import GUI.Scoreboards.JobScoreboard;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SkillsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length != 0) {
                sendUsage(sender);
            } else {
                new SkillTree(p);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Command may only be used by player's!");
        }



        return true;
    }

    private void sendUsage(CommandSender s) {
        s.sendMessage(ChatColor.YELLOW + "/skills - opens the skill menu");
        s.sendMessage(ChatColor.YELLOW + "/skills help - help for skills command");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> argSuggestions = new ArrayList<>();
        ArrayList<String> argsToRemove = new ArrayList<>();

        switch (args.length) {
            case 1 -> {

                argSuggestions.add("help");

                for (String argSuggestion : argSuggestions) {
                    if (!argSuggestion.startsWith(args[0])) {
                        argsToRemove.add(argSuggestion);
                    }
                }
            }
        }

        for (String argToRemove : argsToRemove) {
            argSuggestions.remove(argToRemove);
        }

        return argSuggestions;
    }
}