package PlayerData;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import Math.CustomMath;
import Economy.Vault;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PlayerStats {

    /*
    Class Variables
     */

    private Player owningPlayer;

    private String jobName = "unemployed";

    public MaterialHandler materialHandler = new MaterialHandler();

    public PlayerSkills playerSkills = new PlayerSkills();

    private double totalActionsRequired = 64;

    private double actionsRequired = totalActionsRequired;

    private double baseWage = 5;

    private double earningMultiplier = 1;

    public Location lastFishingPosition = null;
    public Boolean autoFishFlag = false;

    private ArrayList<String> saveStatsNames = new ArrayList<>();

    /*
    Class Constructor
     */

    public PlayerStats(Player p) {
        playerStats.put(p.getUniqueId(), this);
        owningPlayer = p;

        saveStatsNames.add("occupation");
        saveStatsNames.add("earningsmultiplier");
        saveStatsNames.add("actionsrequired");
        saveStatsNames.add("totalactionsrequired");
        saveStatsNames.add("lumberjackLevel");
        saveStatsNames.add("lumberjackXP");
        saveStatsNames.add("minerLevel");
        saveStatsNames.add("minerXP");
        saveStatsNames.add("fisherLevel");
        saveStatsNames.add("fisherXP");
        saveStatsNames.add("farmerLevel");
        saveStatsNames.add("farmerXP");
    }

    /*
    Class functions
     */

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public double getTotalActionsRequired() { return totalActionsRequired; }

    public void setTotalActionsRequired(double totalActionsRequired) { this.totalActionsRequired = totalActionsRequired; }

    public double getActionsRequired() {
        return actionsRequired;
    }

    public void setActionsRequired(double actionsRequired) {
        this.actionsRequired = actionsRequired;
    }

    public double getEarningMultiplier() {
        return earningMultiplier;
    }

    public void setEarningMultiplier(double earningMultiplier) {
        this.earningMultiplier = earningMultiplier;
    }

    //Keeps tracks if player should get money and level up
    public void decreaseActionsRequired(double amount) {
        actionsRequired -= amount;

        switch (jobName) {
            case "lumberjack" -> {
                playerSkills.increaseLumberjackXP(owningPlayer, amount);
            }
            case "miner" -> {
                playerSkills.increaseMinerXP(owningPlayer, amount);
            }
            case "farmer" -> {
                 playerSkills.increaseFarmerXP(owningPlayer, amount);
            }
            //Since fisher works different it's not here
        }

        //Check if player has done enough actions for the next reward and reset if yes
        if (actionsRequired <= 0.0) {
            actionsRequired = CustomMath.randomNumber(90, 110) * (totalActionsRequired / 100);
            Economy economy = Vault.getEconomy();
            double random = CustomMath.randomNumber((int) (baseWage * 90) / 100, (int) (baseWage * 110) / 100);
            economy.depositPlayer(owningPlayer, earningMultiplier * random);
            owningPlayer.sendMessage(ChatColor.GREEN + "You got awarded " + CustomMath.truncate(random * earningMultiplier, 2)  + " Woodcoins!");
        }
    }

    public void loadPlayerStatsToRam(PlayerStats ps) {

        //Read stats file line by line
        String psp = Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + owningPlayer.getUniqueId() + "/stats.ini";

        InputStream is;
        try {
            is = new FileInputStream(psp);
        } catch (FileNotFoundException e) {
            owningPlayer.sendMessage(ChatColor.RED + "Failed to load stats!");
            throw new RuntimeException(e);
        }

        try (Scanner sc = new Scanner( is, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] statPair = line.split("=");

                //Continue if stat in stats.ini has no value assigned
                if (statPair[1] == null) {
                    owningPlayer.sendMessage(ChatColor.YELLOW + "The stat " + statPair[0] + " has no value assigned! Skipping... The stat will probably be removed when you rejoin!");
                    continue;
                }

                //Load the stats
                if (statPair[0].equals(saveStatsNames.get(0))) {
                    setJobName(statPair[1]);

                } else if (statPair[0].equals(saveStatsNames.get(1))) {
                    setEarningMultiplier(Double.parseDouble(statPair[1]));

                } else if (statPair[0].equals(saveStatsNames.get(2))) {
                    setActionsRequired(Double.parseDouble(statPair[1]));

                } else if (statPair[0].equals(saveStatsNames.get(3))) {
                    setTotalActionsRequired(Double.parseDouble(statPair[1]));
                }
                else if (statPair[0].equals(saveStatsNames.get(4))) {
                    playerSkills.lumberjackLevel = Integer.parseInt(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(5))) {
                    playerSkills.lumberjackXP = Double.parseDouble(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(6))) {
                    playerSkills.minerLevel = Integer.parseInt(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(7))) {
                    playerSkills.minerXP = Double.parseDouble(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(8))) {
                    playerSkills.fisherLevel = Integer.parseInt(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(9))) {
                    playerSkills.fisherXP = Double.parseDouble(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(10))) {
                    playerSkills.farmerLevel = Integer.parseInt(statPair[1]);
                }
                else if (statPair[0].equals(saveStatsNames.get(11))) {
                    playerSkills.farmerXP = Double.parseDouble(statPair[1]);
                }
            }
        }
    }

    public void savePlayerStatsInRam() {

        ArrayList<String> statsToSave = getAllStatsInRam();

        //Write stats to file
        File psfp = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + owningPlayer.getUniqueId() + "/stats.ini");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(psfp));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < statsToSave.size(); i++) {
            if (statsToSave.get(i) == null) {
                statsToSave.set(i, "");
            }
            try {
                writer.write(saveStatsNames.get(i) + "=" + statsToSave.get(i));
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getAllStatsInRam() {
        ArrayList<String> stats = new ArrayList<>();

        stats.add(getJobName());
        stats.add(String.valueOf(getEarningMultiplier()));
        stats.add(String.valueOf(getActionsRequired()));
        stats.add(String.valueOf(getTotalActionsRequired()));
        stats.add(String.valueOf(playerSkills.lumberjackLevel));
        stats.add(String.valueOf(playerSkills.lumberjackXP));
        stats.add(String.valueOf(playerSkills.minerLevel));
        stats.add(String.valueOf(playerSkills.minerXP));
        stats.add(String.valueOf(playerSkills.fisherLevel));
        stats.add(String.valueOf(playerSkills.fisherXP));
        stats.add(String.valueOf(playerSkills.farmerLevel));
        stats.add(String.valueOf(playerSkills.farmerXP));

        return stats;
    }

    /*
    Static Functions and Variables
     */

    public static HashMap<UUID, PlayerStats> playerStats = new HashMap<UUID, PlayerStats>();

    public static PlayerStats getPlayerStats(Player p) {
        return playerStats.get(p.getUniqueId());
    }

    public static void removePlayerStats(Player p) {
        PlayerStats ps = getPlayerStats(p);
        playerStats.remove(p.getUniqueId());
        ps = null;
    }
}


