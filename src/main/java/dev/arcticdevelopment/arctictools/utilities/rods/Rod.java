package dev.arcticdevelopment.arctictools.utilities.rods;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rod {

	private ItemStack rod;
	private int crystals;
	private int totalFish;
	private ItemMeta rodMeta;

	public Rod(ItemStack rod) {
		this.rod = rod;
		this.rodMeta = rod.getItemMeta();

	}

	public int getCrystals() {
		return crystals;
	}

	public void setCrystals(int crystals) {
		this.crystals = crystals;
	}

	public int getTotalFish() {
		return totalFish;
	}

	public void setTotalFish(int totalFish) {
		this.totalFish = totalFish;
	}
}
