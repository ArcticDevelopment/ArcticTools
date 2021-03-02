package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

import java.util.List;

public class SoulboundEnchant extends RodEnchant {

	@Override
	public String getName() { return ChatColor.RED + "Soulbound"; }

	@Override
	public String getDescription() {
		return " &7Rod is kept on death, however, one level of Soulbound is removed";
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
