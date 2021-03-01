package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

public class CrystalBoostEnchant extends RodEnchant {
	@Override
	public String getName() {
		return ChatColor.LIGHT_PURPLE + "Crystal Boost";
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_CRYSTALBOOST;
	}

	@Override
	public int getMaxLevel() {
		return 20;
	}
}
