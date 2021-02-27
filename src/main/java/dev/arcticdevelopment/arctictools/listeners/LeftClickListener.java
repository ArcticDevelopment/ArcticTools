package dev.arcticdevelopment.arctictools.listeners;

import dev.arcticdevelopment.arctictools.utilities.rods.RodUpgradeGUI;
import dev.kyro.arcticapi.misc.ASound;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {

	@EventHandler(ignoreCancelled = false, priority = EventPriority.LOWEST)
	public static void onLeftClick(PlayerInteractEvent event) {

		if (!event.getAction().equals(Action.LEFT_CLICK_AIR) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;


		Player player = event.getPlayer();

		if (!player.isSneaking()) return;

		event.setCancelled(true);

		player.openInventory(new RodUpgradeGUI(player).getInventory());
		ASound.play(player, Sound.BAT_TAKEOFF, 1000, 1);
	}

}
