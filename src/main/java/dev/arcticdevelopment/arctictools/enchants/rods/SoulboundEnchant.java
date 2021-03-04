package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class SoulboundEnchant extends RodEnchant {
	@Override
	public String getName() {
		return ChatColor.RED + "Soulbound";
	}

	@Override
	public List<String> getDescription(int level) {
		List<String> description = new ArrayList<>();

		int chance = 0;
		switch(level) {
			case 1:
				chance = 50;
				break;
			case 2:
				chance = 70;
				break;
			case 3:
				chance = 85;
				break;
			case 4:
				chance = 95;
				break;
			case 5:
				chance = 100;
				break;
		}

		description.add("&7Chance to keep rod on death,");
		description.add("&7but removes this enchant");
		description.add("&7Current Chance: &b" + chance + "%");
		return description;
	}

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_SOULBOUND;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getSlot() {
		return 13;
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
