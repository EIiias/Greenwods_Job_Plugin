package PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerSkills {

    public ArrayList<Double> lumberjackRequiredXPPerLevel = new ArrayList<>();
    public ArrayList<Double> fisherRequiredXPPerLevel = new ArrayList<>();
    public ArrayList<Double> farmerRequiredXPPerLevel = new ArrayList<>();
    public ArrayList<Double> minerRequiredXPPerLevel = new ArrayList<>();

    public double lumberjackXP = 0;
    public double lumberjackRequiredXP = 20;
    public int lumberjackLevel = 0;


    public double fisherXP = 0;
    public double fisherRequiredXP = 20;
    public int fisherLevel = 0;

    public double farmerXP = 0;
    public double farmerRequiredXP = 100;
    public int farmerLevel = 0;

    public double minerXP = 0;
    public double minerRequiredXP = 20;
    public int minerLevel = 0;

    public PlayerSkills() {

        /*
        XP Required for Lumberjack
         */

        lumberjackRequiredXPPerLevel.add(40.0); //Level 2
        lumberjackRequiredXPPerLevel.add(45.0);
        lumberjackRequiredXPPerLevel.add(50.0);
        lumberjackRequiredXPPerLevel.add(55.0); //Level 5
        lumberjackRequiredXPPerLevel.add(60.0);
        lumberjackRequiredXPPerLevel.add(70.0);
        lumberjackRequiredXPPerLevel.add(70.0);
        lumberjackRequiredXPPerLevel.add(80.0);
        lumberjackRequiredXPPerLevel.add(100.0); //Level 10
        lumberjackRequiredXPPerLevel.add(150.0);
        lumberjackRequiredXPPerLevel.add(175.0);
        lumberjackRequiredXPPerLevel.add(200.0);
        lumberjackRequiredXPPerLevel.add(225.0);
        lumberjackRequiredXPPerLevel.add(250.0); //Level 15
        lumberjackRequiredXPPerLevel.add(375.0);
        lumberjackRequiredXPPerLevel.add(400.0);
        lumberjackRequiredXPPerLevel.add(450.0);
        lumberjackRequiredXPPerLevel.add(500.0);
        lumberjackRequiredXPPerLevel.add(550.0); //Level 20
        lumberjackRequiredXPPerLevel.add(600.0);
        lumberjackRequiredXPPerLevel.add(610.0);
        lumberjackRequiredXPPerLevel.add(620.0);
        lumberjackRequiredXPPerLevel.add(630.0);
        lumberjackRequiredXPPerLevel.add(640.0); //Level 25
        lumberjackRequiredXPPerLevel.add(650.0);
        lumberjackRequiredXPPerLevel.add(670.0);
        lumberjackRequiredXPPerLevel.add(690.0);
        lumberjackRequiredXPPerLevel.add(710.0);
        lumberjackRequiredXPPerLevel.add(730.0); //Level 30
        lumberjackRequiredXPPerLevel.add(760.0);
        lumberjackRequiredXPPerLevel.add(790.0);
        lumberjackRequiredXPPerLevel.add(820.0);
        lumberjackRequiredXPPerLevel.add(850.0);
        lumberjackRequiredXPPerLevel.add(880.0); //Level 35
        lumberjackRequiredXPPerLevel.add(910.0);
        lumberjackRequiredXPPerLevel.add(928.0);
        lumberjackRequiredXPPerLevel.add(936.0);
        lumberjackRequiredXPPerLevel.add(954.0);
        lumberjackRequiredXPPerLevel.add(972.0); // Level 40
        lumberjackRequiredXPPerLevel.add(1000.0);

        fisherRequiredXPPerLevel.add(40.0); //Level 2
        fisherRequiredXPPerLevel.add(45.0);
        fisherRequiredXPPerLevel.add(50.0);
        fisherRequiredXPPerLevel.add(55.0); //Level 5
        fisherRequiredXPPerLevel.add(60.0);
        fisherRequiredXPPerLevel.add(70.0);
        fisherRequiredXPPerLevel.add(70.0);
        fisherRequiredXPPerLevel.add(80.0);
        fisherRequiredXPPerLevel.add(100.0); //Level 10
        fisherRequiredXPPerLevel.add(150.0);
        fisherRequiredXPPerLevel.add(175.0);
        fisherRequiredXPPerLevel.add(200.0);
        fisherRequiredXPPerLevel.add(225.0);
        fisherRequiredXPPerLevel.add(250.0); //Level 15
        fisherRequiredXPPerLevel.add(375.0);
        fisherRequiredXPPerLevel.add(400.0);
        fisherRequiredXPPerLevel.add(450.0);
        fisherRequiredXPPerLevel.add(500.0);
        fisherRequiredXPPerLevel.add(550.0); //Level 20
        fisherRequiredXPPerLevel.add(600.0);
        fisherRequiredXPPerLevel.add(610.0);
        fisherRequiredXPPerLevel.add(620.0);
        fisherRequiredXPPerLevel.add(630.0);
        fisherRequiredXPPerLevel.add(640.0); //Level 25
        fisherRequiredXPPerLevel.add(650.0);
        fisherRequiredXPPerLevel.add(670.0);
        fisherRequiredXPPerLevel.add(690.0);
        fisherRequiredXPPerLevel.add(710.0);
        fisherRequiredXPPerLevel.add(730.0); //Level 30
        fisherRequiredXPPerLevel.add(760.0);
        fisherRequiredXPPerLevel.add(790.0);
        fisherRequiredXPPerLevel.add(820.0);
        fisherRequiredXPPerLevel.add(850.0);
        fisherRequiredXPPerLevel.add(880.0); //Level 35
        fisherRequiredXPPerLevel.add(910.0);
        fisherRequiredXPPerLevel.add(928.0);
        fisherRequiredXPPerLevel.add(936.0);
        fisherRequiredXPPerLevel.add(954.0);
        fisherRequiredXPPerLevel.add(972.0); // Level 40
        fisherRequiredXPPerLevel.add(1000.0);

        farmerRequiredXPPerLevel.add(40.0); //Level 2
        farmerRequiredXPPerLevel.add(45.0);
        farmerRequiredXPPerLevel.add(50.0);
        farmerRequiredXPPerLevel.add(55.0); //Level 5
        farmerRequiredXPPerLevel.add(60.0);
        farmerRequiredXPPerLevel.add(70.0);
        farmerRequiredXPPerLevel.add(70.0);
        farmerRequiredXPPerLevel.add(80.0);
        farmerRequiredXPPerLevel.add(100.0); //Level 10
        farmerRequiredXPPerLevel.add(150.0);
        farmerRequiredXPPerLevel.add(175.0);
        farmerRequiredXPPerLevel.add(200.0);
        farmerRequiredXPPerLevel.add(225.0);
        farmerRequiredXPPerLevel.add(250.0); //Level 15
        farmerRequiredXPPerLevel.add(375.0);
        farmerRequiredXPPerLevel.add(400.0);
        farmerRequiredXPPerLevel.add(450.0);
        farmerRequiredXPPerLevel.add(500.0);
        farmerRequiredXPPerLevel.add(550.0); //Level 20
        farmerRequiredXPPerLevel.add(600.0);
        farmerRequiredXPPerLevel.add(610.0);
        farmerRequiredXPPerLevel.add(620.0);
        farmerRequiredXPPerLevel.add(630.0);
        farmerRequiredXPPerLevel.add(640.0); //Level 25
        farmerRequiredXPPerLevel.add(650.0);
        farmerRequiredXPPerLevel.add(670.0);
        farmerRequiredXPPerLevel.add(690.0);
        farmerRequiredXPPerLevel.add(710.0);
        farmerRequiredXPPerLevel.add(730.0); //Level 30
        farmerRequiredXPPerLevel.add(760.0);
        farmerRequiredXPPerLevel.add(790.0);
        farmerRequiredXPPerLevel.add(820.0);
        farmerRequiredXPPerLevel.add(850.0);
        farmerRequiredXPPerLevel.add(880.0); //Level 35
        farmerRequiredXPPerLevel.add(910.0);
        farmerRequiredXPPerLevel.add(928.0);
        farmerRequiredXPPerLevel.add(936.0);
        farmerRequiredXPPerLevel.add(954.0);
        farmerRequiredXPPerLevel.add(972.0); // Level 40
        farmerRequiredXPPerLevel.add(1000.0);

        minerRequiredXPPerLevel.add(40.0); //Level 2
        minerRequiredXPPerLevel.add(45.0);
        minerRequiredXPPerLevel.add(50.0);
        minerRequiredXPPerLevel.add(55.0); //Level 5
        minerRequiredXPPerLevel.add(60.0);
        minerRequiredXPPerLevel.add(70.0);
        minerRequiredXPPerLevel.add(70.0);
        minerRequiredXPPerLevel.add(80.0);
        minerRequiredXPPerLevel.add(100.0); //Level 10
        minerRequiredXPPerLevel.add(150.0);
        minerRequiredXPPerLevel.add(175.0);
        minerRequiredXPPerLevel.add(200.0);
        minerRequiredXPPerLevel.add(225.0);
        minerRequiredXPPerLevel.add(250.0); //Level 15
        minerRequiredXPPerLevel.add(375.0);
        minerRequiredXPPerLevel.add(400.0);
        minerRequiredXPPerLevel.add(450.0);
        minerRequiredXPPerLevel.add(500.0);
        minerRequiredXPPerLevel.add(550.0); //Level 20
        minerRequiredXPPerLevel.add(600.0);
        minerRequiredXPPerLevel.add(610.0);
        minerRequiredXPPerLevel.add(620.0);
        minerRequiredXPPerLevel.add(630.0);
        minerRequiredXPPerLevel.add(640.0); //Level 25
        minerRequiredXPPerLevel.add(650.0);
        minerRequiredXPPerLevel.add(670.0);
        minerRequiredXPPerLevel.add(690.0);
        minerRequiredXPPerLevel.add(710.0);
        minerRequiredXPPerLevel.add(730.0); //Level 30
        minerRequiredXPPerLevel.add(760.0);
        minerRequiredXPPerLevel.add(790.0);
        minerRequiredXPPerLevel.add(820.0);
        minerRequiredXPPerLevel.add(850.0);
        minerRequiredXPPerLevel.add(880.0); //Level 25
        minerRequiredXPPerLevel.add(910.0);
        minerRequiredXPPerLevel.add(928.0);
        minerRequiredXPPerLevel.add(936.0);
        minerRequiredXPPerLevel.add(954.0);
        minerRequiredXPPerLevel.add(972.0); // Level 40
        minerRequiredXPPerLevel.add(1000.0);



        /*
        Planned Level Upgrades

        All Jobs:
        Earnings multiplier
        Decrease Actions Required

        Fisher:
        Custom fishing loot
        Fisher Backpack (Item Backpack)

        Miner:
        Ability to craft Haste Pick
        Ore Spread

        Farmer:
        More Food Drops
        Food Backpack


        Lumberjack:
        Timber Mod mässig
        Planks 6 instead of 4 - done
         */
    }


    //Lumberjaack
    public void increaseLumberjackXP(Player p, double amount) {
        lumberjackXP += amount;

        if (lumberjackXP >= lumberjackRequiredXP) {
            if (lumberjackLevel == lumberjackRequiredXPPerLevel.size()) {
                lumberjackXP = lumberjackRequiredXPPerLevel.get(lumberjackRequiredXPPerLevel.size() - 1) - 1;
                return;
            }
            lumberjackXP = 0;
            lumberjackLevel++;
            lumberjackRequiredXP = lumberjackRequiredXPPerLevel.get(lumberjackLevel - 1);
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            p.sendMessage(ChatColor.GREEN + "Lumberjack level up! [" + lumberjackLevel + "]");
            PlayerStats ps = PlayerStats.getPlayerStats(p);

            switch (lumberjackLevel) {
                case 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 29, 31, 32, 33, 34, 36, 37, 38, 39 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                }
                case 5, 10, 15, 25, 30, 35 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                }
                case 20 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Refined Woodwork' - get 6 planks from logs");
                }
                case 40 -> {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Congratulations! " + p.getName() + " has reached the max level [" + lumberjackLevel + "] for Lumberjack!");
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Timber Mod' - Break adjacent logs (Max 5)");
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.playSound(t ,Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
                    }
                }
            }
        }
    }


    //Fisher
    public void increaseFisherXP(Player p, double amount) {
        fisherXP += amount;

        if (fisherXP >= fisherRequiredXP) {
            if (fisherLevel == fisherRequiredXPPerLevel.size()) {
                fisherXP = fisherRequiredXPPerLevel.get(fisherRequiredXPPerLevel.size() - 1) - 1;
                return;
            }
            fisherXP = 0;
            fisherLevel++;
            fisherRequiredXP = fisherRequiredXPPerLevel.get(fisherLevel - 1);
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            p.sendMessage(ChatColor.GREEN + "Lumberjack level up! [" + fisherLevel + "]");
            PlayerStats ps = PlayerStats.getPlayerStats(p);

            switch (fisherLevel) {
                case 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 29, 31, 32, 33, 34, 36, 37, 38, 39 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                }
                case 5, 10, 15, 25, 30, 35 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                }
                case 20 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Refined Woodwork' - get 6 planks from logs");
                }
                case 40 -> {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Congratulations! " + p.getName() + " has reached the max level [" + fisherLevel + "] for Lumberjack!");
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Timber Mod' - Break adjacent logs (Max 5)");
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.playSound(t ,Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
                    }
                }
            }
        }
    }


    //Miner
    public void increaseMinerXP(Player p, double amount) {
        minerXP += amount;

        if (minerXP >= minerRequiredXP) {
            if (minerLevel == minerRequiredXPPerLevel.size()) {
                minerXP = minerRequiredXPPerLevel.get(minerRequiredXPPerLevel.size() - 1) - 1;
                return;
            }
            minerXP = 0;
            minerLevel++;
            minerRequiredXP = minerRequiredXPPerLevel.get(minerLevel - 1);
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            p.sendMessage(ChatColor.GREEN + "Lumberjack level up! [" + minerLevel + "]");
            PlayerStats ps = PlayerStats.getPlayerStats(p);

            switch (minerLevel) {
                case 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 29, 31, 32, 33, 34, 36, 37, 38, 39 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                }
                case 5, 10, 15, 25, 30, 35 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                }
                case 20 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Refined Woodwork' - get 6 planks from logs");
                }
                case 40 -> {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Congratulations! " + p.getName() + " has reached the max level [" + minerLevel + "] for Lumberjack!");
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Timber Mod' - Break adjacent logs (Max 5)");
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.playSound(t ,Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
                    }
                }
            }
        }
    }


    //Farmer
    public void increaseFarmerXP(Player p, double amount) {
        farmerXP += amount;

        if (farmerXP >= farmerRequiredXP) {
            if (farmerLevel == farmerRequiredXPPerLevel.size()) {
                farmerXP = farmerRequiredXPPerLevel.get(farmerRequiredXPPerLevel.size() - 1) - 1;
                return;
            }
            farmerXP = 0;
            farmerLevel++;
            farmerRequiredXP = farmerRequiredXPPerLevel.get(farmerLevel - 1);
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            p.sendMessage(ChatColor.GREEN + "Lumberjack level up! [" + farmerLevel + "]");
            PlayerStats ps = PlayerStats.getPlayerStats(p);

            switch (farmerLevel) {
                case 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 29, 31, 32, 33, 34, 36, 37, 38, 39 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                }
                case 5, 10, 15, 25, 30, 35 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                }
                case 20 -> {
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Refined Woodwork' - get 6 planks from logs");
                }
                case 40 -> {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Congratulations! " + p.getName() + " has reached the max level [" + farmerLevel + "] for Lumberjack!");
                    p.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Timber Mod' - Break adjacent logs (Max 5)");
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.playSound(t ,Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
                    }
                }
            }
        }
    }
}
