package dev.arcticdevelopment.arctictools.enchants.rods;

import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTags;


public class SpawnerFinderEnchant extends RodEnchant {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public NBTTags getNBTTag() {
		return NBTTags.ROD_ENCHANT_SPAWNERS;
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}
}
