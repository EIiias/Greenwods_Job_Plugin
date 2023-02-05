package PlayerData;

import Commands.BackpackCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import Math.CustomMath;
import Economy.Vault;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PlayerStats {

    /*
    Class Variables
     */

    public Player owningPlayer;

    private String jobName = "unemployed";

    private MaterialHandler materialHandler;

    private PlayerSkills playerSkills;

    private double totalActionsRequired = 64;

    private double actionsRequired = totalActionsRequired;

    private double baseWage = 5;

    private double earningsMultiplier = 1;

    public Location lastFishingPosition = null;
    public Boolean autoFishFlag = false;

    public Boolean timberSkill = false;
    public Boolean timberSkillCooldown = false;

    public int backPackSize = 9;
    public ItemStack[] backpackContents = {new ItemStack(Material.AIR)};

    public ItemStack[] fisherBackpackContents = {new ItemStack(Material.AIR)};

    private ArrayList<String> saveStatsNames = new ArrayList<>();

    /*
    Class Constructor
     */

    public PlayerStats(Player p) {
        playerStats.put(p.getUniqueId(), this);
        owningPlayer = p;

        //Create material handler instance for player
        materialHandler = new MaterialHandler(p);

        //Create player skills instance for player
        playerSkills = new PlayerSkills(p);

        //Name of the save stats found in the file
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

        loadStatsYAML();
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
        return earningsMultiplier;
    }

    public void setEarningMultiplier(double earningMultiplier) {
        this.earningsMultiplier = earningMultiplier;
    }

    //Keeps tracks if player should get money and level up
    public void decreaseActionsRequired(double amount) {
        actionsRequired -= amount;

        playerSkills.increaseJobXP(amount, jobName);

        //Check if player has done enough actions for the next reward and reset if yes
        if (actionsRequired <= 0.0) {
            actionsRequired = CustomMath.randomNumber(90, 110) * (totalActionsRequired / 100);
            Economy economy = Vault.getEconomy();
            double random = CustomMath.randomNumber((int) (baseWage * 90) / 100, (int) (baseWage * 110) / 100);
            economy.depositPlayer(owningPlayer, earningsMultiplier * random);
            owningPlayer.sendActionBar(ChatColor.GOLD + "You got awarded " + CustomMath.truncate(random * earningsMultiplier, 2) + " Woodcoins!");
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

    public void loadBackpack() {
        if (!backpackList.containsKey(owningPlayer)) {
            Inventory bp = Bukkit.createInventory(owningPlayer, PlayerStats.getPlayerStats(owningPlayer).backPackSize, "Backpack of " + owningPlayer.getName());
            backpackList.put(owningPlayer, bp);
            bp.setContents(PlayerStats.getPlayerStats(owningPlayer).backpackContents);
        }
    }

    public PlayerSkills getSkills() {
        return playerSkills;
    }

    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }

    public void saveStatsYAML() {

        File playerStatsFile = new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + owningPlayer.getUniqueId(), "stats.yml");

        YamlConfiguration playerStatsFileYAML = YamlConfiguration.loadConfiguration(playerStatsFile);




        playerStatsFileYAML.options().setHeader(Arrays.asList("Player Stats for " + owningPlayer.getName() + " (last known name)"));

        playerStatsFileYAML.set("Occupation", getPlayerStats(owningPlayer).getJobName());
        playerStatsFileYAML.setComments("Occupation", Arrays.asList("Last saved occupation of the player"));

        playerStatsFileYAML.set("EarningsMultiplier", getPlayerStats(owningPlayer).getEarningMultiplier());
        playerStatsFileYAML.setComments("EarningsMultiplier", Arrays.asList("Earnings multiplier"));

        playerStatsFileYAML.set("ActionsRequired", getPlayerStats(owningPlayer).actionsRequired);
        playerStatsFileYAML.setComments("ActionsRequired", Arrays.asList("Actions required until next reward"));

        playerStatsFileYAML.set("TotalActionsRequired", getPlayerStats(owningPlayer).actionsRequired);
        playerStatsFileYAML.setComments("TotalActionsRequired", Arrays.asList("The reset value for actions required when actions required is <=0"));

        playerStatsFileYAML.set("BackpackSize", getPlayerStats(owningPlayer).backPackSize);
        playerStatsFileYAML.setComments("BackpackSize", Arrays.asList("", "The amount of slots the player backpack has (Warning: needs to be multiple of 9)"));

        backpackContents = PlayerStats.backpackList.get(owningPlayer).getContents();
        playerStatsFileYAML.set("BackpackContents", backpackContents);
        playerStatsFileYAML.setComments("BackpackContents", Arrays.asList("", "Items currently in the player's backpack"));

        playerStatsFileYAML.set("lumberjackLevel", getPlayerStats(owningPlayer).getSkills().lumberjackLevel);
        playerStatsFileYAML.setComments("lumberjackLevel", Arrays.asList("", "Player skills", "", "Lumberjack skills"));
        playerStatsFileYAML.set("lumberjackXP", getPlayerStats(owningPlayer).getSkills().lumberjackXP);
        playerStatsFileYAML.set("lumberjackRequiredXP", getPlayerStats(owningPlayer).getSkills().lumberjackRequiredXP);

        playerStatsFileYAML.set("minerLevel", getPlayerStats(owningPlayer).getSkills().minerLevel);
        playerStatsFileYAML.setComments("minerLevel", Arrays.asList("", "Miner skills"));
        playerStatsFileYAML.set("minerXP", getPlayerStats(owningPlayer).getSkills().minerXP);
        playerStatsFileYAML.set("minerRequiredXP", getPlayerStats(owningPlayer).getSkills().minerRequiredXP);

        playerStatsFileYAML.set("farmerLevel", getPlayerStats(owningPlayer).getSkills().farmerLevel);
        playerStatsFileYAML.setComments("farmerLevel", Arrays.asList("", "Farmer skills"));
        playerStatsFileYAML.set("farmerXP", getPlayerStats(owningPlayer).getSkills().farmerXP);
        playerStatsFileYAML.set("farmerRequiredXP", getPlayerStats(owningPlayer).getSkills().farmerRequiredXP);

        playerStatsFileYAML.set("fisherLevel", getPlayerStats(owningPlayer).getSkills().fisherLevel);
        playerStatsFileYAML.setComments("fisherLevel", Arrays.asList("", "Fisher skills"));
        playerStatsFileYAML.set("fisherXP", getPlayerStats(owningPlayer).getSkills().fisherXP);
        playerStatsFileYAML.set("fisherRequiredXP", getPlayerStats(owningPlayer).getSkills().fisherRequiredXP);

        playerStatsFileYAML.set("TimberSkill", getPlayerStats(owningPlayer).timberSkill);
        playerStatsFileYAML.setComments("TimberSkill", Arrays.asList("", "Player unlocked skills", ""));

        try {
            playerStatsFileYAML.save(new File(Bukkit.getPluginsFolder() + "/JobsPlugin/players/" + owningPlayer.getUniqueId(), "stats.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadStatsYAML() {

        //Create [PluginFolder]/JobsPlugin/players/[Player UUID] directory if nonexistent
        if (!new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId()).exists()) {
            File pf = new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId());
            pf.mkdir();
        }

        //Check if the old version for the player stats file is still existing
        if (new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId() + "/stats.ini").exists()) {
            Bukkit.getLogger().fine("[Jobs] Outdated save stats formating for player " + owningPlayer.getName() + " found! Migrating to new format...");
            loadPlayerStatsToRam(this);
            saveStatsYAML();
            new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId() + "/stats.ini").delete();
            return;
        //Check if player save data is available
        } else if (!(new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId() + "/stats.yml").exists())) {
            Bukkit.getLogger().fine("[Jobs] No save data found for player " + owningPlayer.getName() + "! Creating new...");
            saveStatsYAML();
            return;
        }

        File playerStatsFile = new File(Bukkit.getPluginsFolder() + "/GreenwoodsJobs/players/" + owningPlayer.getUniqueId(), "stats.yml");

        YamlConfiguration playerStatsFileYAML = YamlConfiguration.loadConfiguration(playerStatsFile);

        //Load basic stats
        assert playerStatsFileYAML.get("Occupation") != null : getPlayerStats(owningPlayer).jobName = (String) playerStatsFileYAML.get("Occupation");
        assert playerStatsFileYAML.get("EarningsMultiplier") != null : getPlayerStats(owningPlayer).earningsMultiplier = (double) playerStatsFileYAML.get("EarningsMultiplier");
        assert playerStatsFileYAML.get("ActionsRequired") != null : getPlayerStats(owningPlayer).actionsRequired = (double) playerStatsFileYAML.get("ActionsRequired");
        getPlayerStats(owningPlayer).totalActionsRequired = (double) playerStatsFileYAML.get("TotalActionsRequired");
        getPlayerStats(owningPlayer).backPackSize = (int) playerStatsFileYAML.get("BackpackSize");
        getPlayerStats(owningPlayer).backpackContents = ((List<ItemStack>) playerStatsFileYAML.get("BackpackContents")).toArray(new ItemStack[0]);
        loadBackpack();

        //Load data for lumberjack
        getPlayerStats(owningPlayer).getSkills().lumberjackLevel = (int) playerStatsFileYAML.get("lumberjackLevel");
        getPlayerStats(owningPlayer).getSkills().lumberjackXP = (double) playerStatsFileYAML.get("lumberjackXP");
        getPlayerStats(owningPlayer).getSkills().lumberjackRequiredXP = (double) playerStatsFileYAML.get("lumberjackRequiredXP");

        //Load data for miner
        getPlayerStats(owningPlayer).getSkills().minerLevel = (int) playerStatsFileYAML.get("minerLevel");
        getPlayerStats(owningPlayer).getSkills().minerXP = (double) playerStatsFileYAML.get("minerXP");
        getPlayerStats(owningPlayer).getSkills().minerRequiredXP = (double) playerStatsFileYAML.get("minerRequiredXP");

        //Load data for farmer
        getPlayerStats(owningPlayer).getSkills().farmerLevel = (int) playerStatsFileYAML.get("farmerLevel");
        getPlayerStats(owningPlayer).getSkills().farmerXP = (double) playerStatsFileYAML.get("farmerXP");
        getPlayerStats(owningPlayer).getSkills().farmerRequiredXP = (double) playerStatsFileYAML.get("farmerRequiredXP");

        //Load data for fisher
        getPlayerStats(owningPlayer).getSkills().fisherLevel = (int) playerStatsFileYAML.get("fisherLevel");
        getPlayerStats(owningPlayer).getSkills().fisherXP = (double) playerStatsFileYAML.get("fisherXP");
        getPlayerStats(owningPlayer).getSkills().fisherRequiredXP = (double) playerStatsFileYAML.get("fisherRequiredXP");

        //Load unlocked skills
        getPlayerStats(owningPlayer).timberSkill = (boolean) playerStatsFileYAML.get("TimberSkill");
    }

    /*
    Static Functions and Variables
     */

    public static HashMap<UUID, PlayerStats> playerStats = new HashMap<UUID, PlayerStats>();

    public static HashMap<Player, Inventory> backpackList = new HashMap<>();

    public static PlayerStats getPlayerStats(Player p) {
        return playerStats.get(p.getUniqueId());
    }

    public static void removePlayerStats(Player p) {
        PlayerStats ps = getPlayerStats(p);
        PlayerStats.backpackList.remove(p);
        PlayerStats.playerStats.remove(p.getUniqueId());
        ps = null;
    }
}


