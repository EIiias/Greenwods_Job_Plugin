package Jobs;

import java.io.*;
import java.nio.file.Files;

import Commands.JobCommand;
import Commands.SkillsCommand;
import Events.*;
import PlayerData.PlayerStats;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import Economy.Vault;

public final class Jobs extends JavaPlugin implements Listener {

    private static Jobs instance;

    @Override
    public void onEnable() {

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

        for (Player p : Bukkit.getOnlinePlayers()) {

            PlayerStats ps = new PlayerStats(p);

            //Load player stats if available!
            if (new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + p.getUniqueId() + "/stats.ini").exists()) {
                ps.loadPlayerStatsToRam(ps); //Load stats if available
            } else {
                p.sendMessage(ChatColor.RED + "No player stats found!");
            }
        }

        //Custom Crafting rec
        NamespacedKey customRecipeKey = new NamespacedKey(this, "test_item");

        ItemStack customRecipeItem = new ItemStack(Material.OAK_PLANKS, 6);

        ShapedRecipe testRecipe = new ShapedRecipe(customRecipeKey, customRecipeItem);
        testRecipe.shape("   ", "   ", " % ");
        testRecipe.shape("% ", "  ");
        testRecipe.setIngredient('%', Material.COD);

        getServer().addRecipe(testRecipe);

        System.out.println("Started Jobs plugin!");
    }


    @Override
    public void onDisable() {

        //Save stats for all players
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerStats.getPlayerStats(p).savePlayerStatsInRam();
        }
        System.out.println("Stopped Jobs plugin!");
    }

    public static Jobs getInstance() {
        return instance;
    }
}
