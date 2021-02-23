
package dev.arcticdevelopment.arctictools;

import dev.arcticdevelopment.arctictools.commands.BaseCommand;
import dev.arcticdevelopment.arctictools.listeners.PlayerFishListener;
import dev.arcticdevelopment.arctictools.utilities.FishDrop;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcticTools extends JavaPlugin {

    public static ArcticTools INSTANCE;
    public static Plugin WORLDGUARD;

    @Override
    public void onLoad() {
        try {

            WORLDGUARD = getServer().getPluginManager().getPlugin("WorldGuard");

            WorldGuardHook.registerFlag("arctic-fishing",false);
        } catch(Exception e) {
            System.out.println("penis");
        }

    }

    @Override
    public void onEnable() {

        INSTANCE = this;

        loadConfig();

        ArcticAPI.configInit(this, "prefix", "error-prefix");

        registerCommands();
        registerListeners();
        registerFish();

    }

    @Override
    public void onDisable() {


    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {
        //ABaseCommand apiCommand = new BaseCommand("arcticfishing");

//        apiCommand.registerCommand(new SetExitLocation("setexit"));
//        getCommand("printer").setExecutor(new PrinterCommand());
    }

    private void registerListeners() {

        getServer().getPluginManager().registerEvents(new PlayerFishListener(), this);
    }

    private void registerFish() {

        ItemStack item = new ItemStack(Material.COOKED_FISH);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&f&nBasic&r&8]&r &7Cod"));
        item.setItemMeta(itemMeta);
        new FishDrop(item, "basic");

        ItemStack item1 = new ItemStack(Material.RAW_FISH);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&f&nBasic&r&8]&r &7Carp"));
        item1.setItemMeta(itemMeta1);
        new FishDrop(item1, "basic");




        ItemStack item2 = new ItemStack(Material.COOKED_FISH);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Cod"));
        item2.setItemMeta(itemMeta2);
        new FishDrop(item2, "enchanted");

        ItemStack item3 = new ItemStack(Material.RAW_FISH);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Carp"));
        item3.setItemMeta(itemMeta3);
        new FishDrop(item3, "enchanted");

        ItemStack item4 = new ItemStack(Material.COOKED_FISH,1,(short) 1);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Red Snapper"));
        item4.setItemMeta(itemMeta4);
        new FishDrop(item4, "enchanted");

        ItemStack item5 = new ItemStack(Material.RAW_FISH,1,(short) 1);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Salmon"));
        item5.setItemMeta(itemMeta5);
        new FishDrop(item5, "enchanted");

        ItemStack item6 = new ItemStack(Material.RAW_FISH,1, (short) 2);
        ItemMeta itemMeta6 = item6.getItemMeta();
        itemMeta6.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Clownfish"));
        item6.setItemMeta(itemMeta6);
        new FishDrop(item6, "enchanted");




        ItemStack item7 = new ItemStack(Material.RAW_FISH,1, (short) 2);
        ItemMeta itemMeta7 = item7.getItemMeta();
        itemMeta7.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Clownfish"));
        item7.setItemMeta(itemMeta7);
        new FishDrop(item7, "mystical");

        ItemStack item8 = new ItemStack(Material.RAW_FISH,1,(short) 1);
        ItemMeta itemMeta8 = item.getItemMeta();
        itemMeta8.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Salmon"));
        item8.setItemMeta(itemMeta8);
        new FishDrop(item8, "mystical");

        ItemStack item9 = new ItemStack(Material.COOKED_FISH,1,(short) 1);
        ItemMeta itemMeta9 = item9.getItemMeta();
        itemMeta9.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Red Snapper"));
        item9.setItemMeta(itemMeta9);
        new FishDrop(item9, "mystical");




        ItemStack item10 = new ItemStack(Material.RAW_FISH,1,(short) 3);
        ItemMeta itemMeta10 = item10.getItemMeta();
        itemMeta10.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&6&nLegendary&r&8]&r &7Pufferfish"));
        item10.setItemMeta(itemMeta10);
        new FishDrop(item10, "legendary");

        ItemStack item11 = new ItemStack(Material.RAW_FISH,1,(short) 2);
        ItemMeta itemMeta11 = item11.getItemMeta();
        itemMeta11.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&6&nLegendary&r&8]&r &7Clownfish"));
        item11.setItemMeta(itemMeta11);
        new FishDrop(item11, "legendary");




        ItemStack item12 = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta12 = item12.getItemMeta();
        itemMeta12.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Tear"));
        item12.setItemMeta(itemMeta12);
        new FishDrop(item12, "divine");

        ItemStack item13 = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta itemMeta13 = item13.getItemMeta();
        itemMeta13.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Crystal"));
        item13.setItemMeta(itemMeta13);
        new FishDrop(item13, "divine");

        ItemStack item14 = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta itemMeta14 = item14.getItemMeta();
        itemMeta14.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Shard"));
        item14.setItemMeta(itemMeta14);
        new FishDrop(item14, "divine");

    }
}
