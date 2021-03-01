package dev.arcticdevelopment.arctictools.controllers;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import org.bukkit.Bukkit;
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
	public abstract NBTTag getNBTTag();
	public abstract int getMaxLevel();

	public static void registerEnchant(RodEnchant enchant) {

		Bukkit.getServer().getPluginManager().registerEvents(enchant, ArcticTools.INSTANCE);
		enchants.add(enchant);
	}

	public static void updateRod(NBTItem nbtRod) {

		ItemMeta rodMeta = nbtRod.getItem().getItemMeta();
		if(!rodMeta.hasLore()) rodMeta.setLore(new ArrayList<>());
		ALoreBuilder loreBuilder = new ALoreBuilder(nbtRod.getItem());

		loreBuilder.addLore("&f");
		loreBuilder.addLore("&fSick harvester hoe pog champ &9/upgrade");
		loreBuilder.addLore("&f");
		loreBuilder.addLore("&9Enchantments");
		loreBuilder.addLore("&f");

		for(RodEnchant enchant : enchants) {

			int level = nbtRod.getInteger(enchant.getNBTTag().getRef());

			if(level == 0) continue;

			loreBuilder.addLore(enchant.getName() + " " + level);
		}

		rodMeta.setLore(loreBuilder.colorize().getLore());
		nbtRod.getItem().setItemMeta(rodMeta);
	}

	public static void updateEnchant(NBTItem nbtRod, RodEnchant enchant) {

		System.out.println(nbtRod);
		System.out.println(enchant);

		int level = nbtRod.getInteger(enchant.getNBTTag().getRef());

		ItemMeta rodMeta = nbtRod.getItem().getItemMeta();
		if(!rodMeta.hasLore()) rodMeta.setLore(new ArrayList<>());
		List<String> lore = rodMeta.getLore();

		boolean onHoe = false;
		for(String line : lore) {
			if(line.contains(enchant.getName())) onHoe = true;
		}

		if(!onHoe) {

			ALoreBuilder rodLore = new ALoreBuilder(nbtRod.getItem())
					.addLore(enchant.getName() + " " + level);

			rodMeta.setLore(rodLore.getLore());
		} else {

			for(int i = 0; i < lore.size(); i++) {

				if(lore.get(i).contains(enchant.getName())) {

					lore.set(i, enchant.getName() + " " + level);
				}
			}
			rodMeta.setLore(lore);
		}

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
