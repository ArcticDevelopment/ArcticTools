package dev.arcticdevelopment.arcticfishing.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public static void onLeftClick(PlayerInteractEvent event) {

		if (!event.getAction().equals(Action.LEFT_CLICK_AIR) || !event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;


	}

	@EventHandler(ignoreCancelled = true)
	public static void onShiftLeftClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if (!event.getAction().equals(Action.LEFT_CLICK_AIR) || !event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;

		if (!player.isSneaking()) return;



	}
}
