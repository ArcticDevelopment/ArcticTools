package dev.arcticdevelopment.arctictools.listeners;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerRespawnListener implements Listener {

	@EventHandler
	public static void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();
		UUID playerUUID = player.getUniqueId();
		PlayerInventory playerInventory = player.getInventory();

		if (!OnPlayerDeathListener.deathItems.containsKey(playerUUID)) {
			return;
		}
		ArrayList<ItemStack> recoveredItems = OnPlayerDeathListener.deathItems.get(playerUUID);

		for (ItemStack item : recoveredItems) {

			NBTItem nbtRod = new NBTItem(item);

			nbtRod.setInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef(), nbtRod.getInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef()) - 1);
			RodEnchant.updateRod(nbtRod);

			playerInventory.addItem(nbtRod.getItem());
		}
		OnPlayerDeathListener.deathItems.remove(playerUUID);
	}
}
