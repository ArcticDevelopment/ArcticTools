package dev.arcticdevelopment.arctictools.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerFishListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public static void rodCast(PlayerFishEvent event) {

		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();

		if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			event.setCancelled(true);

			inventory.addItem(item);
		}


	}
}
