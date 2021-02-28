package dev.arcticdevelopment.arctictools.controllers;

import org.bukkit.inventory.ItemStack;

public class TreasureDrop {

	private ItemStack itemStack;

	private int weight = 1;

	public TreasureDrop(ItemStack itemStack, int weight) {

		this.itemStack = itemStack;
		this.weight = weight;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public int getWeight() {
		return weight;
	}
}
