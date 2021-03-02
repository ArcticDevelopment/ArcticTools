package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class LureEnchant extends RodEnchant {
	@Override
	public String getName() {
		return ChatColor.GREEN + "Lure";
	}

	@Override
	public List<String> getDescription(int level) {
		List<String> description = new ArrayList<>();
		description.add("&7Increases bite chance");
		return description;
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_LURE;
	}

	@Override
	public int getMaxLevel() {
		return 20;
	}

	@Override
	public int getSlot() {
		return 15;
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
