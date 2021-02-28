package dev.arcticdevelopment.arctictools.listeners;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class OnPlayerDeathListener implements Listener {

	public static HashMap<UUID, NBTItem> itemMap = new HashMap<>();

	@EventHandler(priority = EventPriority.LOW)
	public static void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity().getPlayer();
		Inventory inventory = player.getInventory();
		UUID playerUUID = player.getUniqueId();

		for (ItemStack testItem : inventory.getContents()) {

			NBTItem testNBTItem = new NBTItem(testItem);
			if (testNBTItem.hasKey("ROD_UUID")) {
				if(testNBTItem.getInteger("ROD_ENCHANT_SOULBOUND") > 0) {
					itemMap.put(playerUUID, testNBTItem);
				}

			}
		}
	}
}
