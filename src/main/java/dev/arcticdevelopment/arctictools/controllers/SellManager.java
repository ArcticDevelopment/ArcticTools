package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellManager {

	public static void sellFish(Player player) {

		int total = 0;

		Inventory inventory = player.getInventory();

		for (ItemStack item: inventory.getContents()){

			for (FishDrop fishDrop: FishDrop.drops) {

				if (fishDrop.getDrop().equals(item)) {

					total += fishDrop.getSellPrice();
				}
			}
		}
		System.out.println(total);
		ArcticTools.VAULT.depositPlayer(player,total);
	}
}
