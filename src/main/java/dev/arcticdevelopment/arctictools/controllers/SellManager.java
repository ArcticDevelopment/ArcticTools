package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellManager {

	public static void sellFish(Player player) {

		int total = 0;

		Inventory inventory = player.getInventory();
		ItemStack testItem;

		for (ItemStack item: inventory.getContents()){
			if (item == null) {
				continue;
			}

			for (FishDrop fishDrop: FishDrop.drops) {

				 testItem = new ItemStack(item);
				 testItem.setAmount(1);


				if (fishDrop.getDrop().equals(testItem)) {

					total += fishDrop.getSellPrice() * item.getAmount();
				}
			}
		}
		System.out.println(total);
		ArcticTools.VAULT.depositPlayer(player,total);
	}
}
