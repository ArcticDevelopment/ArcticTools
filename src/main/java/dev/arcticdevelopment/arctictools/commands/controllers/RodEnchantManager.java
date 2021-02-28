package dev.arcticdevelopment.arctictools.commands.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RodEnchantManager {

	public static final ArrayList<RodEnchant> enchants = new ArrayList<>();

	public static void registerEnchant(RodEnchant enchant) {

		Bukkit.getServer().getPluginManager().registerEvents(enchant, ArcticTools.INSTANCE);
		enchants.add(enchant);

	}


//	public static HashMap<RodEnchant, Integer> getActiveEnchants(Player player) {
//		HashMap<RodEnchant, Integer> activeEnchants = new HashMap<>();
//
//		ItemStack rod = player.getItemInHand();
//
//
//		for(RodEnchant enchant : enchants) {
//
//			if(itemHasEnchant(item, enchant)) {
//
//				int level;
//
//				activeEnchants.put(enchant, level);
//			}
//
//		}
//		return activeEnchants;
//	}

	public static boolean itemHasEnchant(ItemStack item, RodEnchant enchant) {

		if(item == null || item.getItemMeta() == null) return false;
		ItemMeta itemMeta = item.getItemMeta();

		if(itemMeta.getLore() == null) return false;
		List<String> lore = itemMeta.getLore();

		for(String loreItem : lore) {

			if(loreItem.contains(enchant.getName())) return true;
		}

		return false;
	}
}
