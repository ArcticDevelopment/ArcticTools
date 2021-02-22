package dev.arcticdevelopment.arctictools.listeners;

import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.Material;
import org.bukkit.entity.Item;
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

		if (!WorldGuardHook.hasFlag(player.getLocation(),"arctic-fishing")) {
			System.out.println("lmfao" + WorldGuardHook.hasFlag(player.getLocation(),"arctic-fishing"));
			return;
		}

		if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {

			((Item) event.getCaught()).setItemStack(item);
		}


	}
}
