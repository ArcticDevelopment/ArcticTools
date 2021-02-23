package dev.arcticdevelopment.arctictools.listeners;

import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import dev.kyro.arcticapi.builders.ALoreBuilder;
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
import org.bukkit.inventory.meta.ItemMeta;

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

			int random = (int) (Math.random() * FishDrop.drops.size());
			ItemStack drop = new ItemStack(FishDrop.drops.get(random).getDrop());
			ItemMeta dropMeta = drop.getItemMeta();

			double multiplier = (double) Math.round(Math.random() * 10 * 100)/100;

			ALoreBuilder loreBuilder = new ALoreBuilder();
			loreBuilder.addLore(multiplier + "");

			dropMeta.setLore(loreBuilder.getLore());
			drop.setItemMeta(dropMeta);

			((Item) event.getCaught()).setItemStack(drop);
		}
	}
}
