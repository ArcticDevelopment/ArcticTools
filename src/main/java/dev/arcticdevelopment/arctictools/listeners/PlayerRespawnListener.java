package dev.arcticdevelopment.arctictools.listeners;

import de.tr7zw.nbtapi.NBTItem;
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

		for (ItemStack item: recoveredItems) {
			playerInventory.addItem(item);
		}
	}
}
