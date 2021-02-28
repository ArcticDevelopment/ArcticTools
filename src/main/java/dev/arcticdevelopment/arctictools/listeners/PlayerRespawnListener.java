package dev.arcticdevelopment.arctictools.listeners;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerRespawnListener implements Listener {

	@EventHandler
	public static void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();
		UUID playerUUID = player.getUniqueId();
		NBTItem recoveredRod;

		try {
			recoveredRod =OnPlayerDeathListener.itemMap.get(playerUUID);
			//TODO fix this
			inventory.addItem(recoveredRod);
		} catch(Exception e) {
			System.out.println("Player did not have a rod on him");
		}
	}
}
