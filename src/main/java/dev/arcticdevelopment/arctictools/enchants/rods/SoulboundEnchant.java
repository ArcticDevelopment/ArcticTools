package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.ChatColor;

public class SoulboundEnchant extends RodEnchant {

	@Override
	public String getName() { return ChatColor.RED + "Soulbound"; }

	@Override
	public NBTTag getNBTTag() {
		return NBTTag.ROD_ENCHANT_SOULBOUND;
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}
}
