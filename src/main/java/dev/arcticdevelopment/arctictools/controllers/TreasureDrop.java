package dev.arcticdevelopment.arctictools.controllers;

import org.bukkit.inventory.ItemStack;

public class TreasureDrop {

	private ItemStack itemStack;

	private int weight = 1;

	public TreasureDrop(ItemStack itemStack) {

		this.itemStack = itemStack;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public int getWeight() {
		return weight;
	}
}
