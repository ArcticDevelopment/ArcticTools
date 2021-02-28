package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;


public class TreasureFinderEnchant extends RodEnchant {

	@Override
	public String getName() {
		return ChatColor.AQUA + "Treasure Finder";
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_TREASURE;
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}
}
