package PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerSkills {

    public PlayerSkills(Player p)  {
        owningPlayer = p;
    }

    public double[] lumberjackRequiredXPPerLevel = {
            40.0, 45.0, 50.0, 55.0, 60.0, //Level 1-5
            70.0, 70.0, 80.0, 100.0, 150.0, //Level 6-10
            175.0, 200.0, 225.0, 250.0, 375.0, //Level 11-15
            400.0, 450.0, 500.0, 550.0, 600.0, //Level 16-20
            610.0, 620.0, 630.0, 640.0, 650.0, //Level 21-25
            670.0, 690.0, 710.0, 730.0, 760.0, //Level 26-30
            790.0, 820.0, 850.0, 880.0, 910.0, //Level 31-35
            928.0, 936.0, 954.0, 972.0, 1000.0 //Level 36-40
    };

    public double[] minerRequiredXPPerLevel = {
            40.0, 45.0, 50.0, 55.0, 60.0, //Level 1-5
            70.0, 70.0, 80.0, 100.0, 150.0, //Level 6-10
            175.0, 200.0, 225.0, 250.0, 375.0, //Level 11-15
            400.0, 450.0, 500.0, 550.0, 600.0, //Level 16-20
            610.0, 620.0, 630.0, 640.0, 650.0, //Level 21-25
            670.0, 690.0, 710.0, 730.0, 760.0, //Level 26-30
            790.0, 820.0, 850.0, 880.0, 910.0, //Level 31-35
            928.0, 936.0, 954.0, 972.0, 1000.0 //Level 36-40
    };

    public double[] farmerRequiredXPPerLevel = {
            40.0, 45.0, 50.0, 55.0, 60.0, //Level 1-5
            70.0, 70.0, 80.0, 100.0, 150.0, //Level 6-10
            175.0, 200.0, 225.0, 250.0, 375.0, //Level 11-15
            400.0, 450.0, 500.0, 550.0, 600.0, //Level 16-20
            610.0, 620.0, 630.0, 640.0, 650.0, //Level 21-25
            670.0, 690.0, 710.0, 730.0, 760.0, //Level 26-30
            790.0, 820.0, 850.0, 880.0, 910.0, //Level 31-35
            928.0, 936.0, 954.0, 972.0, 1000.0 //Level 36-40
    };

    public double[] fisherRequiredXPPerLevel = {
            40.0, 45.0, 50.0, 55.0, 60.0, //Level 1-5
            70.0, 70.0, 80.0, 100.0, 150.0, //Level 6-10
            175.0, 200.0, 225.0, 250.0, 375.0, //Level 11-15
            400.0, 450.0, 500.0, 550.0, 600.0, //Level 16-20
            610.0, 620.0, 630.0, 640.0, 650.0, //Level 21-25
            670.0, 690.0, 710.0, 730.0, 760.0, //Level 26-30
            790.0, 820.0, 850.0, 880.0, 910.0, //Level 31-35
            928.0, 936.0, 954.0, 972.0, 1000.0 //Level 36-40
    };

    private Player owningPlayer;

    public double lumberjackXP = 0;
    public double lumberjackRequiredXP = 20;
    public int lumberjackLevel = 0;


    public double fisherXP = 0;
    public double fisherRequiredXP = 20;
    public int fisherLevel = 0;

    public double farmerXP = 0;
    public double farmerRequiredXP = 20;
    public int farmerLevel = 0;

    public double minerXP = 0;
    public double minerRequiredXP = 20;
    public int minerLevel = 0;

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

    //Lumberjack
    public void increaseJobXP(double amount, String job) {

        job = job.toLowerCase();

        int increasedSkillLevel = 0;
        double increasedSkillXP = 0;
        double[] increasedSkillRequiredXPPerLevel = null;

        switch(job) {
            case "lumberjack" -> {
                increasedSkillLevel = lumberjackLevel;
                increasedSkillXP = lumberjackXP;
                increasedSkillRequiredXPPerLevel = lumberjackRequiredXPPerLevel;
            }
            case "miner" -> {
                increasedSkillLevel = minerLevel;
                increasedSkillXP = minerXP;
                increasedSkillRequiredXPPerLevel = minerRequiredXPPerLevel;
            }
            case "farmer" -> {
                increasedSkillLevel = farmerLevel;
                increasedSkillXP = farmerXP;
                increasedSkillRequiredXPPerLevel = farmerRequiredXPPerLevel;
            }
            case "fisher" -> {
                increasedSkillLevel = fisherLevel;
                increasedSkillXP = fisherXP;
                increasedSkillRequiredXPPerLevel = fisherRequiredXPPerLevel;
            }
        }

        increasedSkillXP += amount;

        if (increasedSkillXP >= increasedSkillRequiredXPPerLevel[increasedSkillLevel - 1]) {
            if (increasedSkillLevel == increasedSkillRequiredXPPerLevel.length) {
                return;
            }

            increasedSkillLevel++;
            increasedSkillXP = 0;

            PlayerStats ps = PlayerStats.getPlayerStats(owningPlayer);

            switch (increasedSkillLevel) {
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
                    ps.backPackSize += 9;
                    owningPlayer.sendMessage(ChatColor.GOLD + "Your backpack size has increased! Use /bp to access!");
                }
                case 40 -> {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Congratulations! " + owningPlayer.getName() + " has reached the max level [" + lumberjackLevel + "] for Lumberjack!");
                    owningPlayer.sendMessage(ChatColor.GOLD + "Ultimate Skill unlocked: 'Timber Mod' - Automatically break whole tress!");
                    ps.setTotalActionsRequired(ps.getTotalActionsRequired() * 0.98);
                    ps.setEarningMultiplier(ps.getEarningMultiplier() + 0.05);
                    ps.timberSkill = true;
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.playSound(t, Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
                    }
                }
            }
        }

        switch(job) {
            case "lumberjack" -> {
                lumberjackLevel = increasedSkillLevel;
                lumberjackXP = increasedSkillXP;
            }
            case "miner" -> {
                minerLevel = increasedSkillLevel;
                minerXP = increasedSkillXP;
            }
            case "farmer" -> {
                farmerLevel = increasedSkillLevel;
                farmerXP = increasedSkillXP;
            }
            case "fisher" -> {
                fisherLevel = increasedSkillLevel;
                fisherXP = increasedSkillXP;
            }
        }
    }
}