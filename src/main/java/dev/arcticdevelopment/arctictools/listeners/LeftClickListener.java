package dev.arcticdevelopment.arctictools.listeners;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.arcticdevelopment.arctictools.inventories.RodUpgradeGUI;
import dev.kyro.arcticapi.misc.ASound;
import javafx.scene.shape.Arc;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeftClickListener implements Listener {

	public static List<Player> players = new ArrayList<>();

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onLeftClick(PlayerInteractEvent event) {

//		TODO: When you right click with a rod it also left clicks???
		//System.out.println(event.getAction().toString());

		Player player = event.getPlayer();

		if (player.getItemInHand() == null || player.getItemInHand().getType() != Material.FISHING_ROD) return;

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

			players.add(player);

			new BukkitRunnable() {
				@Override
				public void run() {

					players.remove(player);
				}
			}.runTaskLater(ArcticTools.INSTANCE, 1L);

			return;
		}

		if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK)
			return;

		if (players.contains(player)) {
			return;
		}

		if (!player.isSneaking()) return;

		NBTItem nbtItem = new NBTItem(player.getItemInHand());
		if(!nbtItem.hasKey(NBTTag.ROD_UUID.getRef())) return;

		event.setCancelled(true);

		player.openInventory(new RodUpgradeGUI(player).getInventory());
		ASound.play(player, Sound.BAT_TAKEOFF, 0.7F, 1);
	}

}
