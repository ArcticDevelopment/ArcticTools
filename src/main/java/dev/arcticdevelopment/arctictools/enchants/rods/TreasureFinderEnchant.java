package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.List;


public class TreasureFinderEnchant extends RodEnchant {

	@Override
	public String getName() {
		return ChatColor.AQUA + "Treasure Finder";
	}

	@Override
	public String getDescription() {
		return " &7Chance to find rare treasure";
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_TREASURE;
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}

	@Override
	public int getSlot() {
		return 23;
	}

	@Override
	public int getLevelCost(int level) {

		return (int) (Math.floor(Math.pow(1.08, level) * 20) * 100);
	}

	@Override
	public List<String> getLore(int level) {

		return createDefaultLore(level);
	}
}
