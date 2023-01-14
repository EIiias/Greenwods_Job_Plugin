package Commands;

import GUI.Inventories.SkillTree;
import GUI.Scoreboards.JobScoreboard;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JobCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        String[] validOccupations = {"lumberjack", "miner", "farmer", "fisher", "unemployed"};
        Player p = null;
        PlayerStats ps = null;

        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toLowerCase();
        }

        if (sender instanceof Player) {
            p = (Player) sender;
            ps = PlayerStats.getPlayerStats(p);
        }

        switch (args.length) {
            case 1 -> {
                switch (args[0]) {
                    case "list" -> {
                        for (String validOccupation : validOccupations) {
                            sender.sendMessage(ChatColor.YELLOW + validOccupation);
                        }
                        return true;
                    }
                    case "show" -> {
                        if (p == null) {
                            sender.sendMessage(ChatColor.RED + "The command can only be used like this by a player!");
                            sendUsage(sender);
                            return true;
                        }

                        if (!sender.hasPermission("jobs.command.showJobScoreboard")) {
                            sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                            sendUsage(sender);
                            return true;
                        }

                        JobScoreboard.updateJobScoreboard(p);
                        return true;
                    }
                    case "hide" -> {
                        if (p == null) {
                            sender.sendMessage(ChatColor.RED + "The command can only be used like this by a player!");
                            sendUsage(sender);
                            return true;
                        }

                        if (!sender.hasPermission("jobs.command.hideJobScoreboard")) {
                            sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                            sendUsage(sender);
                            return true;
                        }

                        JobScoreboard.hideJobScoreboard(p);
                        return true;
                    }
                    case "change" -> {
                        sender.sendMessage(ChatColor.YELLOW + "To change your Job you need a Job Reset Token! These tokens can be bought from villagers!");
                        return true;
                    }
                    case "skills" -> {
                        if (sender instanceof Player) {
                            new SkillTree(p);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Command may only be used by a player!");
                        }
                    }
                    default -> {
                        sendUsage(sender);
                        return true;
                    }
                }
            }
            case 2 -> {
                switch (args[0] + " " + args[1]) {
                    default -> {
                        if (args[0].equals("select")) {

                            if (p == null) {
                                sender.sendMessage(ChatColor.RED + "The command can only be used like this by a player!");
                                sendUsage(sender);
                                return true;
                            }

                            if (!sender.hasPermission("jobs.command.selectJob")) {
                                sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                                sendUsage(sender);
                                return true;
                            }

                            if (!ps.getJobName().equals("unemployed")) {
                                sender.sendMessage(ChatColor.RED + "You already have an occupation!");
                                return true;
                            }

                            for (String validOccupation : validOccupations) {
                                if (args[1].equals(validOccupation)) {
                                    ps.setJobName(args[1]);
                                    sender.sendMessage(ChatColor.GREEN + "Job set: " + args[1]);
                                    JobScoreboard.updateJobScoreboard(p);
                                    JobScoreboard.hideJobScoreboard(p);
                                    return true;
                                }
                            }
                        } else {
                            sendUsage(sender);
                            return true;
                        }
                        return true;
                    }
                }
            }
            case 3 -> {
                switch (args[0] + " " + args[1] + " " + args[2]) {
                    default -> {
                        if (args[0].equals("select")) {

                            if (!sender.hasPermission("jobs.command.selectJobAdmin")) {
                                sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                                sendUsage(sender);
                                return true;
                            }

                            Player t = null;
                            PlayerStats tps = null;

                            if (Bukkit.getPlayer(args[2]) != null) {
                                t = Bukkit.getPlayer(args[2]);
                                tps = PlayerStats.getPlayerStats(t);
                            } else {
                                sender.sendMessage(ChatColor.RED + "Invalid target Player!");
                                sendUsage(sender);
                                return true;
                            }

                            for (String validOccupation : validOccupations) {
                                if (args[1].equals(validOccupation)) {
                                    tps.setJobName(args[1]);
                                    sender.sendMessage(ChatColor.GREEN + "Job set for " + t.getName() + ": " + args[1]);
                                    t.sendMessage(ChatColor.GREEN + "Your job was changed by an admin: " + args[1]);
                                    JobScoreboard.updateJobScoreboard(p);
                                    JobScoreboard.hideJobScoreboard(p);
                                    return true;
                                }
                            }

                        } else {
                            sendUsage(sender);
                            return true;
                        }
                    }
                }
            }
            default -> {
                sendUsage(sender);
                return true;
            }
        }

        return true;
    }

    private void sendUsage(CommandSender s) {

        Player p = null;

        if (s instanceof Player) {
            p = (Player) s;
        }

        s.sendMessage(ChatColor.YELLOW + "Usage: /job help - lists all your available commands");
        s.sendMessage(ChatColor.YELLOW + "Usage: /job list - lists all jobs");

        //For Command Senders other than Player
        if (p == null) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job select <occupation> <target> - assign occupation to target");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job list - lists all jobs");
            return;
        }

        //For Players
        if (p.hasPermission("jobs.command.*")) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job select <occupation> - assign occupation to self if unemployed");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job select <occupation> <target> - assign occupation to target");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job list - lists all jobs");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job show - show job debug");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job hide - hide job debug");
            s.sendMessage(ChatColor.YELLOW + "Usage: /job change - reset your job to unemployed");
            return;
        }

        if (p.hasPermission("jobs.command.selectJob")) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job select <occupation> - assign occupation to self if unemployed");
        }

        if (p.hasPermission("jobs.command.selectJobAdmin")) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job select <occupation> <target> - assign occupation to target");
        }

        if (p.hasPermission("jobs.command.showJobScoreboard")) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job show - show job debug");
        }

        if (p.hasPermission("jobs.command.hideJobScoreboard")) {
            s.sendMessage(ChatColor.YELLOW + "Usage: /job hide - hide job debug");
        }

        s.sendMessage(ChatColor.YELLOW + "Usage: /job skills - opens your skill tree menus");
        s.sendMessage(ChatColor.YELLOW + "Usage: /job change - tells you how to change your job");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> argSuggestions = new ArrayList<>();
        ArrayList<String> argsToRemove = new ArrayList<>();

        switch (args.length) {
            case 1 -> {

                argSuggestions.add("help");
                argSuggestions.add("show");
                argSuggestions.add("hide");
                argSuggestions.add("select");
                argSuggestions.add("list");
                argSuggestions.add("change");
                argSuggestions.add("skills");

                for (String argSuggestion : argSuggestions) {
                    if (!argSuggestion.startsWith(args[0])) {
                        argsToRemove.add(argSuggestion);
                    }
                }
            }

            case 2 -> {
                argSuggestions.add("lumberjack");
                argSuggestions.add("miner");
                argSuggestions.add("fisher");
                argSuggestions.add("farmer");
                argSuggestions.add("unemployed");

                for (String argSuggestion : argSuggestions) {
                    if (!argSuggestion.startsWith(args[1])) {
                        argsToRemove.add(argSuggestion);
                    }
                }
            }

            case 3 -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    argSuggestions.add(p.getName());
                }

                for (String argSuggestion : argSuggestions) {
                    if (!argSuggestion.startsWith(args[2])) {
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