package dev.arcticdevelopment.arctictools.controllers;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SoulboundManager implements Listener {

	public static HashMap<UUID,ArrayList<ItemStack>> deathItems = new HashMap<>();

	@EventHandler(priority = EventPriority.LOW)
	public static void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity().getPlayer();
		Inventory inventory = player.getInventory();
		UUID playerUUID = player.getUniqueId();
		ArrayList<ItemStack> itemList = new ArrayList<>();

		for (ItemStack testItem : inventory.getContents()) {

			if(testItem == null) {
				continue;
			}

			NBTItem testNBTItem = new NBTItem(testItem);

			if (testNBTItem.hasKey(NBTTag.ROD_UUID.getRef())) {

				System.out.println(testNBTItem.getInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef()));
				if(testNBTItem.getInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef()) > 0) {

					itemList.add(testItem);
				}

			}
		}

		if (!itemList.isEmpty()) {

			event.getDrops().removeAll(itemList);
			deathItems.put(playerUUID,itemList);
		}
	}

	@EventHandler
	public static void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();
		UUID playerUUID = player.getUniqueId();
		PlayerInventory playerInventory = player.getInventory();

		if (!SoulboundManager.deathItems.containsKey(playerUUID)) {
			return;
		}
		ArrayList<ItemStack> recoveredItems = SoulboundManager.deathItems.get(playerUUID);

		for (ItemStack item : recoveredItems) {

			NBTItem nbtRod = new NBTItem(item);

			nbtRod.setInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef(), nbtRod.getInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef()) - 1);
			RodEnchant.updateRod(nbtRod);

			playerInventory.addItem(nbtRod.getItem());
		}
		SoulboundManager.deathItems.remove(playerUUID);
	}
}
