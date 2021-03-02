package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.*;

public class CrystalBoostEnchant extends RodEnchant {
	@Override
	public String getName() {
		return ChatColor.LIGHT_PURPLE + "Crystal Boost";
	}

	@Override
	public List<String> getDescription(int level) {
		List<String> description = new ArrayList<>();
		description.add("&7Chance to get additional crystals");
		return description;
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_CRYSTALBOOST;
	}

	@Override
	public int getMaxLevel() {
		return 20;
	}

	@Override
	public int getSlot() {
		return 31;
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
