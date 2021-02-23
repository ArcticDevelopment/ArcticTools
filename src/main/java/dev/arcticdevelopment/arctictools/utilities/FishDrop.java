package dev.arcticdevelopment.arctictools.utilities;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FishDrop {

	public static List<FishDrop> drops = new ArrayList<>();

	public ItemStack drop;
	public String rarity;

	public ItemStack getDrop() {
		return drop;
	}

	public FishDrop(ItemStack drop, String rarity) {

		this.rarity = rarity;
		this.drop = drop;
		drops.add(this);
	}

	public String getRarity() {
		return rarity;
	}

}
