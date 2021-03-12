package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellManager {

	public static void sellFish(Player player) {

		int total = 0;

		Inventory inventory = player.getInventory();
		ItemStack testItem;
		int counter = 0;

		for(int i = 0 ; i < inventory.getSize() ; i++) {
			ItemStack item = inventory.getItem(i);

			if (item == null) {
				continue;
			}

			for (FishDrop fishDrop: FishDrop.drops) {

				 testItem = new ItemStack(item);
				 testItem.setAmount(1);

				if (fishDrop.getDrop().equals(testItem)) {
					counter += item.getAmount();
					total += fishDrop.getSellPrice() * item.getAmount();

					inventory.setItem(i,new ItemStack(Material.AIR));
				}
			}
		}

		String message = AConfig.getString("messages.sell-fish");
		message = message.replaceAll("%amountsold%", String.valueOf(counter));
		message = message.replaceAll("%totalmoney%", String.valueOf(total));
		AOutput.send(player, message);

		ArcticTools.VAULT.depositPlayer(player, total);
	}
}
