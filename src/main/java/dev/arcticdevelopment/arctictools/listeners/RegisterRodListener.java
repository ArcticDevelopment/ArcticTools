package dev.arcticdevelopment.arctictools.listeners;

import dev.arcticdevelopment.arctictools.utilities.rods.Rod;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegisterRodListener implements Listener {

	@EventHandler(ignoreCancelled = false, priority = EventPriority.LOWEST)
	public static void onLeftClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		ItemMeta itemMeta = item.getItemMeta();

		if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',"&b&nFishing&r &7Rod"))) {
			Rod rod = new Rod(item);
			System.out.println("registered rod");
		}
	}
}
