package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.List;

public class LuckEnchant extends RodEnchant {
	@Override
	public String getName() {
		return ChatColor.GOLD + "Luck";
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_LUCK;
	}

	@Override
	public int getMaxLevel() {
		return 25;
	}

	@Override
	public int getSlot() {
		return 21;
	}

	@Override
	public int getLevelCost(int level) {
		return (int) (Math.floor(Math.pow(1.08, level) * 20) * 150);
	}

	@Override
	public List<String> getLore(int level) {
		return createDefaultLore(level);
	}
}
