
package dev.arcticdevelopment.arctictools;

import dev.arcticdevelopment.arctictools.commands.AdminGiveCommand;
import dev.arcticdevelopment.arctictools.commands.AdminReloadCommand;
import dev.arcticdevelopment.arctictools.commands.ToolsCommand;
import dev.arcticdevelopment.arctictools.commands.rods.*;
import dev.arcticdevelopment.arctictools.controllers.FishManager;
import dev.arcticdevelopment.arctictools.controllers.LeaderboardManager;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.controllers.SoulboundManager;
import dev.arcticdevelopment.arctictools.enchants.rods.*;
import dev.arcticdevelopment.arctictools.inventories.LootEditorGUI;
import dev.arcticdevelopment.arctictools.listeners.LeftClickListener;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDropRarity;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.data.AData;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ArcticTools extends JavaPlugin {

    public static ArcticTools INSTANCE;
    public static Plugin WORLDGUARD;
    public static Economy VAULT = null;

    public static AData CONFIG;

    @Override
    public void onLoad() {
        try {

            WORLDGUARD = getServer().getPluginManager().getPlugin("WorldGuard");

            WorldGuardHook.registerFlag("arctic-fishing",false);
        } catch(Exception ignored) {}
    }

    @Override
    public void onEnable() {

        INSTANCE = this;
        Logger LOGGER = getLogger();

        loadConfig();

        ArcticAPI.configInit(this, "prefix", "error-prefix");
        CONFIG = new AData("config");

        LeaderboardManager.init();
        LootEditorGUI.updateDrops();

        registerCommands();
        registerListeners();
        registerFish();
        registerEnchants();

        if (!setupEconomy()) {
            LOGGER.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {


    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {
        ABaseCommand toolsCommand = new ToolsCommand("atools");
        toolsCommand.registerCommand(new AdminGiveCommand("give"));
        toolsCommand.registerCommand(new AdminCrystalsCommand("crystals"));
        toolsCommand.registerCommand(new AdminDropCommand("drop"));
        toolsCommand.registerCommand(new AdminReloadCommand("reload"));

        ABaseCommand crystalsCommand = new CrystalsCommand("crystals");
        crystalsCommand.registerCommand(new BalanceCommand("bal"));
        crystalsCommand.registerCommand(new PayCommand("pay"));

        getCommand("fishtop").setExecutor(new FishTopCommand());
        getCommand("sellfish").setExecutor(new SellCommand());
    }

    private void registerListeners() {

        getServer().getPluginManager().registerEvents(new FishManager(), this);
        getServer().getPluginManager().registerEvents(new LeftClickListener(), this);
        getServer().getPluginManager().registerEvents(new SoulboundManager(), this);
    }

    private void registerEnchants() {

        RodEnchant.registerEnchant(new LuckEnchant());
        RodEnchant.registerEnchant(new LureEnchant());
        RodEnchant.registerEnchant(new SoulboundEnchant());
        RodEnchant.registerEnchant(new MultiDropEnchant());
        RodEnchant.registerEnchant(new CrystalBoostEnchant());
        RodEnchant.registerEnchant(new TreasureFinderEnchant());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        VAULT = rsp.getProvider();
        return VAULT != null;
    }

    public void registerFish() {

        int multiplier = CONFIG.getInt("fish-multiplier");

        ItemStack item = new ItemStack(Material.COOKED_FISH);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&f&nBasic&r&8]&r &7Cod"));
        item.setItemMeta(itemMeta);
        new FishDrop(item, FishDropRarity.BASIC, multiplier);

        ItemStack item1 = new ItemStack(Material.RAW_FISH);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&f&nBasic&r&8]&r &7Carp"));
        item1.setItemMeta(itemMeta1);
        new FishDrop(item1, FishDropRarity.BASIC,2 * multiplier);




        ItemStack item2 = new ItemStack(Material.COOKED_FISH);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Cod"));
        item2.setItemMeta(itemMeta2);
        new FishDrop(item2, FishDropRarity.ENCHANTED,5 * multiplier);

        ItemStack item3 = new ItemStack(Material.RAW_FISH);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Carp"));
        item3.setItemMeta(itemMeta3);
        new FishDrop(item3, FishDropRarity.ENCHANTED,7 * multiplier);

        ItemStack item4 = new ItemStack(Material.COOKED_FISH,1,(short) 1);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Red Snapper"));
        item4.setItemMeta(itemMeta4);
        new FishDrop(item4, FishDropRarity.ENCHANTED,9 * multiplier);

        ItemStack item5 = new ItemStack(Material.RAW_FISH,1,(short) 1);
        ItemMeta itemMeta5 = item5.getItemMeta();
        itemMeta5.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Salmon"));
        item5.setItemMeta(itemMeta5);
        new FishDrop(item5, FishDropRarity.ENCHANTED,9 * multiplier);

        ItemStack item6 = new ItemStack(Material.RAW_FISH,1, (short) 2);
        ItemMeta itemMeta6 = item6.getItemMeta();
        itemMeta6.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&d&nEnchanted&r&8]&r &7Clownfish"));
        item6.setItemMeta(itemMeta6);
        new FishDrop(item6, FishDropRarity.ENCHANTED,7 * multiplier);




        ItemStack item7 = new ItemStack(Material.RAW_FISH,1, (short) 2);
        ItemMeta itemMeta7 = item7.getItemMeta();
        itemMeta7.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Clownfish"));
        item7.setItemMeta(itemMeta7);
        new FishDrop(item7, FishDropRarity.MYSTICAL,16 * multiplier);

        ItemStack item8 = new ItemStack(Material.RAW_FISH,1,(short) 1);
        ItemMeta itemMeta8 = item.getItemMeta();
        itemMeta8.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Salmon"));
        item8.setItemMeta(itemMeta8);
        new FishDrop(item8, FishDropRarity.MYSTICAL,17 * multiplier);

        ItemStack item9 = new ItemStack(Material.COOKED_FISH,1,(short) 1);
        ItemMeta itemMeta9 = item9.getItemMeta();
        itemMeta9.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&3&nMystical&r&8]&r &7Red Snapper"));
        item9.setItemMeta(itemMeta9);
        new FishDrop(item9, FishDropRarity.MYSTICAL,15 * multiplier);




        ItemStack item10 = new ItemStack(Material.RAW_FISH,1,(short) 3);
        ItemMeta itemMeta10 = item10.getItemMeta();
        itemMeta10.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&6&nLegendary&r&8]&r &7Pufferfish"));
        item10.setItemMeta(itemMeta10);
        new FishDrop(item10, FishDropRarity.LEGENDARY,20 * multiplier);

        ItemStack item11 = new ItemStack(Material.RAW_FISH,1,(short) 2);
        ItemMeta itemMeta11 = item11.getItemMeta();
        itemMeta11.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&6&nLegendary&r&8]&r &7Clownfish"));
        item11.setItemMeta(itemMeta11);
        new FishDrop(item11, FishDropRarity.LEGENDARY,25 * multiplier);




        ItemStack item12 = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta12 = item12.getItemMeta();
        itemMeta12.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Tear"));
        item12.setItemMeta(itemMeta12);
        new FishDrop(item12, FishDropRarity.DIVINE,30 * multiplier);

        ItemStack item13 = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta itemMeta13 = item13.getItemMeta();
        itemMeta13.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Crystal"));
        item13.setItemMeta(itemMeta13);
        new FishDrop(item13, FishDropRarity.DIVINE,35 * multiplier);

        ItemStack item14 = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta itemMeta14 = item14.getItemMeta();
        itemMeta14.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&8[&r&k|||&r&b&nDivine&r&k|||&8]&r &7Shard"));
        item14.setItemMeta(itemMeta14);
        new FishDrop(item14, FishDropRarity.DIVINE,40 * multiplier);
    }
}
