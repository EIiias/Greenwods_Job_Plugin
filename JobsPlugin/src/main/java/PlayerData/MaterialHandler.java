package PlayerData;

import Jobs.Jobs;
import PlayerData.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ArrayList<ItemStack> commonFish = new ArrayList<>();
    public ArrayList<ItemStack> uncommonFish = new ArrayList<>();
    public ArrayList<ItemStack> rareFish = new ArrayList<>();
    public ArrayList<ItemStack> epicFish = new ArrayList<>();
    public ArrayList<ItemStack> legendaryFish = new ArrayList<>();


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

        //Items
        customFishingLoot.add(new ItemStack(Material.TOTEM_OF_UNDYING));
        epicFish.add(customFishingLoot.get(0));
        customFishingLoot.add(new ItemStack(Material.HEART_OF_THE_SEA));
        rareFish.add(customFishingLoot.get(1));
        customFishingLoot.add(new ItemStack(Material.NAME_TAG));
        epicFish.add(customFishingLoot.get(2));
        customFishingLoot.add(new ItemStack(Material.NAUTILUS_SHELL));
        rareFish.add(customFishingLoot.get(3));
        customFishingLoot.add(new ItemStack(Material.IRON_NUGGET, 3));
        commonFish.add(customFishingLoot.get(4));
        customFishingLoot.add(new ItemStack(Material.GOLD_NUGGET, 3));
        commonFish.add(customFishingLoot.get(5));
        customFishingLoot.add(new ItemStack(Material.IRON_INGOT));
        commonFish.add(customFishingLoot.get(6));
        customFishingLoot.add(new ItemStack(Material.GOLD_INGOT));
        uncommonFish.add(customFishingLoot.get(7));
        customFishingLoot.add(new ItemStack(Material.LEATHER_BOOTS));
        commonFish.add(customFishingLoot.get(8));
        customFishingLoot.add(new ItemStack(Material.TRIDENT));
        commonFish.add(customFishingLoot.get(9));

        /*
        Custom Fishes
         */

        //Common Fishes
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 0.5, "Common Carp", "common"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.SALMON), 0.5, "Tuna", "common"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 0.5, "Sardine", "common"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.SALMON), 0.5, "Salmon", "common"));

        //Uncommon Fishes
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 2.0, "Goldfish", "uncommon"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 2.0, "Atlantic Cod", "uncommon"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 2.0, "Trout", "uncommon"));

        //Rare Fishes
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 10.0, "Catfish", "rare"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.PUFFERFISH), 10.0, "Pufferfish", "rare"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.NAUTILUS_SHELL), 10.0, "Crab", "rare"));

        //Epic Fishes
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 25.0, "Sword Fish", "epic"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 25.0, "Shark", "epic"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.TROPICAL_FISH), 25.0, "Koi", "epic"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 25.0, "Dolphin", "epic"));

        //Legendary Fishes
        customFishingLoot.add(createCustomFish(new ItemStack(Material.COD), 75.0, "Whale", "legendary"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.TROPICAL_FISH), 75.0, "Creo Blub", "legendary"));
        customFishingLoot.add(createCustomFish(new ItemStack(Material.TROPICAL_FISH), 75.0, "Nemo", "legendary"));








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

    public ItemStack createCustomFish(ItemStack fish, Double fishValue, String fishName, String rarity) {

        //Get item meta
        ItemMeta fishItemMeta = fish.getItemMeta();

        //Store value in fish item
        PersistentDataContainer fishPDC = fishItemMeta.getPersistentDataContainer();
        fishPDC.set(new NamespacedKey(Jobs.getInstance(), "FishValue"), PersistentDataType.DOUBLE, fishValue);

        //Set color based on rarity
        ChatColor fishTextColor = null;
        switch (rarity) {
            case "common" -> {
                fishTextColor = ChatColor.DARK_GRAY;
                commonFish.add(fish);
            } case "uncommon" -> {
                fishTextColor = ChatColor.GREEN;
                uncommonFish.add(fish);
            } case "rare" -> {
                fishTextColor = ChatColor.AQUA;
                rareFish.add(fish);
            } case "epic" -> {
                fishTextColor = ChatColor.LIGHT_PURPLE;
                epicFish.add(fish);
            } case "legendary" -> {
                fishTextColor = ChatColor.GOLD;
                legendaryFish.add(fish);
            }
        }

        //Set item name to custom name
        fishItemMeta.setDisplayName(fishTextColor + ChatColor.BOLD.toString() + fishName);

        //Add lore (make attributes visible to players)
        ArrayList<String> lore = new ArrayList<>();
        lore.add(fishTextColor + "Rarity: " + rarity.toUpperCase());
        lore.add(fishTextColor + "Can be sold for: ♧" + fishValue);
        fishItemMeta.setLore(lore);

        //Add glint without showing an enchantment
        fishItemMeta.addEnchant(Enchantment.MENDING, 1, true);
        fishItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        //Apply item meta
        fish.setItemMeta(fishItemMeta);

        return fish;
    }
}