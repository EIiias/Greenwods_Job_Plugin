package PlayerData;

import PlayerData.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MaterialHandler {

    /*
    Class Variables
     */

    private HashMap<Material, Double> materialValue = new HashMap<>();
    private HashMap<Material, String> materialName = new HashMap<>();
    private HashMap<Material, String> materialOccupationOwner = new HashMap<>();
    private HashMap<Material, Material> materialAliases = new HashMap<>();
    public ArrayList<ItemStack> customFishingLoot = new ArrayList<>();
    public  ArrayList<Entity> customFishingEntities = new ArrayList<>();

    /*
    Class constructor
     */

    public MaterialHandler() {

        /*
        Material Value
         */

        materialValue.put(Material.OAK_LOG, 1.2);
        materialValue.put(Material.SPRUCE_LOG, 1.0);
        materialValue.put(Material.BIRCH_LOG, 1.2);
        materialValue.put(Material.JUNGLE_LOG, 1.0);
        materialValue.put(Material.ACACIA_LOG, 1.4);
        materialValue.put(Material.DARK_OAK_LOG, 1.0);
        materialValue.put(Material.MANGROVE_LOG, 1.0);

        materialValue.put(Material.COAL_ORE, 1.9);
        materialValue.put(Material.IRON_ORE, 3.8);
        materialValue.put(Material.COPPER_ORE, 1.0);
        materialValue.put(Material.GOLD_ORE, 5.8);
        materialValue.put(Material.REDSTONE_ORE, 1.6);
        materialValue.put(Material.EMERALD_ORE, 8.0);
        materialValue.put(Material.LAPIS_ORE, 3.0);
        materialValue.put(Material.DIAMOND_ORE, 8.0);

        materialValue.put(Material.WHEAT, 1.0);
        materialValue.put(Material.CARROT, 1.0);
        materialValue.put(Material.POTATO, 1.0);
        materialValue.put(Material.BEETROOT, 1.0);
        materialValue.put(Material.SUGAR_CANE, 0.8);
        materialValue.put(Material.MELON, 0.8);
        materialValue.put(Material.PUMPKIN, 0.8);
        materialValue.put(Material.CACTUS, 0.8);

        /*
        Display Name for Players
         */

        materialName.put(Material.SPRUCE_LOG, "Spruce Log");
        materialName.put(Material.BIRCH_LOG, "Birch Log");
        materialName.put(Material.OAK_LOG, "Oak Log");
        materialName.put(Material.JUNGLE_LOG, "Jungle Log");
        materialName.put(Material.ACACIA_LOG, "Acacia Log");
        materialName.put(Material.DARK_OAK_LOG, "Dark Oak Log");
        materialName.put(Material.MANGROVE_LOG, "Mangrove Log");

        materialName.put(Material.COAL_ORE, "Coal Ore");
        materialName.put(Material.IRON_ORE, "Iron Ore");
        materialName.put(Material.COPPER_ORE, "Copper Ore");
        materialName.put(Material.GOLD_ORE, "Gold Ore");
        materialName.put(Material.REDSTONE_ORE, "Redstone Ore");
        materialName.put(Material.EMERALD_ORE, "Emerald Ore");
        materialName.put(Material.LAPIS_ORE, "Lapis Ore");
        materialName.put(Material.DIAMOND_ORE, "Diamond Ore");

        materialName.put(Material.WHEAT, "Wheat");
        materialName.put(Material.CARROT, "Carrot");
        materialName.put(Material.POTATO, "Potato");
        materialName.put(Material.BEETROOT, "Beetroot");
        materialName.put(Material.SUGAR_CANE, "Sugar Cane");
        materialName.put(Material.MELON, "Melon");
        materialName.put(Material.PUMPKIN, "Pumpkin");
        materialName.put(Material.CACTUS, "Cactus");

        /*
        Job that contains this Material
         */

        materialOccupationOwner.put(Material.SPRUCE_LOG, "lumberjack");
        materialOccupationOwner.put(Material.BIRCH_LOG, "lumberjack");
        materialOccupationOwner.put(Material.OAK_LOG, "lumberjack");
        materialOccupationOwner.put(Material.JUNGLE_LOG, "lumberjack");
        materialOccupationOwner.put(Material.ACACIA_LOG, "lumberjack");
        materialOccupationOwner.put(Material.DARK_OAK_LOG, "lumberjack");
        materialOccupationOwner.put(Material.MANGROVE_LOG, "lumberjack");

        materialOccupationOwner.put(Material.COAL_ORE, "miner");
        materialOccupationOwner.put(Material.IRON_ORE, "miner");
        materialOccupationOwner.put(Material.COPPER_ORE, "miner");
        materialOccupationOwner.put(Material.GOLD_ORE, "miner");
        materialOccupationOwner.put(Material.REDSTONE_ORE, "miner");
        materialOccupationOwner.put(Material.EMERALD_ORE, "miner");
        materialOccupationOwner.put(Material.LAPIS_ORE, "miner");
        materialOccupationOwner.put(Material.DIAMOND_ORE, "miner");

        materialOccupationOwner.put(Material.WHEAT, "farmer");
        materialOccupationOwner.put(Material.CARROT, "farmer");
        materialOccupationOwner.put(Material.POTATO, "farmer");
        materialOccupationOwner.put(Material.BEETROOT, "farmer");
        materialOccupationOwner.put(Material.SUGAR_CANE, "farmer");
        materialOccupationOwner.put(Material.MELON, "farmer");
        materialOccupationOwner.put(Material.PUMPKIN, "farmer");
        materialOccupationOwner.put(Material.CACTUS, "farmer");

        /*
        Different Materials that players don't differentiate
         */

        materialAliases.put(Material.DEEPSLATE_COAL_ORE, Material.COAL_ORE);
        materialAliases.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_ORE);
        materialAliases.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_ORE);
        materialAliases.put(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_ORE);
        materialAliases.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_ORE);
        materialAliases.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD_ORE);
        materialAliases.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_ORE);
        materialAliases.put(Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_ORE);

        /*
        Custom Fishing loot
         */

    }

    /*
    Class functions
     */

    //Check if Materials is part of player occupation
    public boolean isJobMaterial(Player p, Material m) {
        PlayerStats ps = PlayerStats.getPlayerStats(p);

        if (hasAlias(m)) {
            m = getAlias(m);
        }

        return Objects.equals(ps.getJobName(), materialOccupationOwner.get(m));
    }

    //Checks if Material has another Material that we as player would call the same
    public boolean hasAlias(Material m) {
        return materialAliases.get(m) != null;
    }

    //Returns Material that we as player would call the same
    public Material getAlias(Material m) {
        return materialAliases.get(m);
    }

    //Get the value of the Material
    public Double getMaterialValue(Material m) {

        if (hasAlias(m)) {
            m = getAlias(m);
        }

        return materialValue.get(m);
    }
}