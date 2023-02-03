package Jobs;

import java.io.*;

import Commands.JobCommand;
import Commands.SkillsCommand;
import Events.*;
import Commands.BackpackCommand;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import Economy.Vault;

public final class Jobs extends JavaPlugin implements Listener {

    private static Jobs instance;

    public static ShapedRecipe sixOakPlanksRecipe = null;

    @Override
    public void onEnable() {

        //Store this instance for access later
        instance = this;

        //Create [PluginFolder]/JobsPlugin directory if nonexistent
        if (!new File(Bukkit.getPluginsFolder() + "/JobsPlugin").exists()) {
            File jpf = new File(Bukkit.getPluginsFolder() + "/JobsPlugin");
            jpf.mkdir();
        }

        //Create [PluginFolder]/JobsPlugin/players directory if nonexistent
        if (!new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players").exists()) {
            File pf = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players");
            pf.mkdir();
        }

        //Disable Vault if not working
        if (!Vault.setupEconomy() ) {
            System.out.println("No economy plugin found. Disabling Vault!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Register commands
        getCommand("job").setExecutor(new JobCommand());
        getCommand("job").setTabCompleter(new JobCommand());
        getCommand("skills").setExecutor(new SkillsCommand());
        getCommand("skills").setTabCompleter(new SkillsCommand());
        getCommand("backpack").setExecutor(new BackpackCommand());

        //Register Event Listener
        Bukkit.getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnStructureGrow(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerFish(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnCropGrow(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClose(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerCraft(), this);

        //Save player stats for all online players
        for (Player p : Bukkit.getOnlinePlayers()) {

            PlayerStats ps = new PlayerStats(p);

            //Load player stats if available!
            if (new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId() + "/stats.yml").exists()) {
                ps.loadStatsYAML(); //Load player stats if available
            }
        }

        //Custom Crafting test
        //NamespacedKey sixOakPlankKey = new NamespacedKey(this, "lumberjack_plank");

        //ItemStack sixOakPlanksItem = new ItemStack(Material.OAK_PLANKS, 6);

        //sixOakPlanksRecipe = new ShapedRecipe(sixOakPlankKey, sixOakPlanksItem);
        //sixOakPlanksRecipe.shape("%");
        //sixOakPlanksRecipe.setIngredient('%', Material.OAK_LOG);

        System.out.println("Started Jobs plugin!");
    }


    @Override
    public void onDisable() {

        //Save stats for all online players
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerStats.getPlayerStats(p).saveStatsYAML();
        }
        System.out.println("Stopped Jobs plugin!");
    }

    public static Jobs getInstance() {
        return instance;
    }
}
