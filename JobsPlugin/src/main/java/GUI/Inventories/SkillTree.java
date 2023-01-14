package GUI.Inventories;

import PlayerData.PlayerSkills;
import PlayerData.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;

public class SkillTree {

    private Player owningPlayer;
    private PlayerSkills playerSkills;

    private ItemStack lumberJackIcon = new ItemStack(Material.OAK_LOG);
    private ItemStack minerIcon = new ItemStack(Material.IRON_ORE);
    private ItemStack fisherIcon = new ItemStack(Material.COD);
    private ItemStack farmerIcon =  new ItemStack(Material.WHEAT);

    public SkillTree(Player p) {

        owningPlayer = p;
        playerSkills = PlayerStats.getPlayerStats(owningPlayer).playerSkills;

        Inventory skillTreeMenu = Bukkit.createInventory(owningPlayer, 9, "Skill Tree of " + owningPlayer.getName());
        skillTreeInventoryList.put(skillTreeMenu, this);

        ItemMeta lumberJackIconMeta = lumberJackIcon.getItemMeta();
        lumberJackIconMeta.setDisplayName("Lumberjack");
        lumberJackIconMeta.setLore(Arrays.asList("Level: " + playerSkills.lumberjackLevel));
        lumberJackIcon.setItemMeta(lumberJackIconMeta);

        ItemMeta minerIconMeta = minerIcon.getItemMeta();
        minerIconMeta.setDisplayName("Miner");
        minerIconMeta.setLore(Arrays.asList("Level: " + playerSkills.minerLevel));
        minerIcon.setItemMeta(minerIconMeta);

        ItemMeta fisherIconMeta = fisherIcon.getItemMeta();
        fisherIconMeta.setDisplayName("Fisher");
        fisherIconMeta.setLore(Arrays.asList("Level: " + playerSkills.fisherLevel));
        fisherIcon.setItemMeta(fisherIconMeta);

        ItemMeta farmerIconMeta = farmerIcon.getItemMeta();
        farmerIconMeta.setDisplayName("Farmer");
        farmerIconMeta.setLore(Arrays.asList("Level: " + playerSkills.farmerLevel));
        farmerIcon.setItemMeta(farmerIconMeta);

        skillTreeMenu.setItem(0, lumberJackIcon);
        skillTreeMenu.setItem(1, minerIcon);
        skillTreeMenu.setItem(2, fisherIcon);
        skillTreeMenu.setItem(3, farmerIcon);

        owningPlayer.openInventory(skillTreeMenu);
    }

    public void lumberjackSkillTree() {
        Inventory skillTreeMenu = Bukkit.createInventory(owningPlayer, 45, owningPlayer.getName() + " - Lumberjack");
        skillTreeInventoryList.put(skillTreeMenu, this);
        PlayerSkills pSkills = PlayerStats.getPlayerStats(owningPlayer).playerSkills;

        for (int i = 0; i < 40; i++) {
            if (i + 1 <= pSkills.lumberjackLevel) {
                ItemStack icon = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(ChatColor.GREEN + "Level " + (i + 1));
                icon.setItemMeta(iconMeta);

                skillTreeMenu.setItem(i, icon);
            } else {
                ItemStack icon = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(ChatColor.RED + "Level " + (i + 1));
                icon.setItemMeta(iconMeta);

                skillTreeMenu.setItem(i, icon);
            }
        }

        ItemStack backButton = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta backButtonMeta = (SkullMeta) backButton.getItemMeta();
        backButtonMeta.setOwningPlayer(Bukkit.getOfflinePlayer(owningPlayer.getUniqueId()));
        backButtonMeta.setDisplayName("Back");
        backButtonMeta.setLore(Arrays.asList("Takes you back to parent menu"));
        backButton.setItemMeta(backButtonMeta);
        skillTreeMenu.setItem(skillTreeMenu.getSize() - 1, backButton);

        owningPlayer.openInventory(skillTreeMenu);
    }

    public static HashMap<Inventory, SkillTree> skillTreeInventoryList = new HashMap<>();
}
