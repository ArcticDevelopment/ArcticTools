package dev.arcticdevelopment.arctictools.controllers;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import dev.kyro.arcticapi.misc.AUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class RodEnchant implements Listener {

	public static final ArrayList<RodEnchant> enchants = new ArrayList<>();

	public abstract String getName();
	public abstract List<String> getDescription(int level);
	public abstract NBTTag getNBTTag();
	public abstract int getMaxLevel();;
	public abstract int getSlot();
	public abstract int getLevelCost(int level);
	public abstract List<String> getLore(int level);

	public static void registerEnchant(RodEnchant enchant) {

		Bukkit.getServer().getPluginManager().registerEvents(enchant, ArcticTools.INSTANCE);
		enchants.add(enchant);
	}

	public List<String> createDefaultLore(int level) {

		ALoreBuilder loreBuilder = new ALoreBuilder();

		loreBuilder.addLore(getDescription(level));
		loreBuilder.addLore("");
		loreBuilder.addLore("&f[" +
				AUtil.createProgressBar("|", ChatColor.AQUA, ChatColor.GRAY, 20, (double) level/getMaxLevel())
				+ "&f]");
		loreBuilder.addLore("");
		loreBuilder.addLore("&b * &fProgress: &3" + level + "&7/&3" + getMaxLevel());
		loreBuilder.addLore("&b * &fCost (Crystals): &3" + getLevelCost(level));

		return loreBuilder.colorize().getLore();
	}

	public static void updateRod(NBTItem nbtRod) {

		ItemMeta rodMeta = nbtRod.getItem().getItemMeta();
		ALoreBuilder loreBuilder = new ALoreBuilder();

		rodMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ArcticTools.CONFIG.getString("rod-name")));

		loreBuilder.addLore("&f");
		loreBuilder.addLore(ArcticTools.CONFIG.getStringList("rod-description"));
//		loreBuilder.addLore("&f");

		boolean hasEnchants = false;
		for(RodEnchant enchant : enchants) {

			int level = nbtRod.getInteger(enchant.getNBTTag().getRef());

			if(level == 0) continue;

			if(!hasEnchants) {

				hasEnchants = true;
				loreBuilder.addLore("&f");
				loreBuilder.addLore("&9Enchantments");
			}

			loreBuilder.addLore("&7 * " + enchant.getName() + " " + AUtil.toRoman(level));
		}

		rodMeta.setLore(loreBuilder.colorize().getLore());
		nbtRod.getItem().setItemMeta(rodMeta);
	}

	public static HashMap<RodEnchant, Integer> getActiveEnchants(Player player) {
		HashMap<RodEnchant, Integer> activeEnchants = new HashMap<>();

		ItemStack rod = player.getItemInHand();
		NBTItem nbtItem = new NBTItem(rod);
		if(!nbtItem.hasKey(NBTTag.ROD_UUID.getRef())) return activeEnchants;

		for(RodEnchant enchant : enchants) {

			if(!nbtItem.hasKey(enchant.getNBTTag().getRef())) continue;

			int level = nbtItem.getInteger(enchant.getNBTTag().getRef());
			activeEnchants.put(enchant, level);
		}
		return activeEnchants;
	}
}
