package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.kyro.arcticapi.gui.AInventoryGUI;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class RodUpgradeUI extends AInventoryGUI {

	public RodUpgradeUI(String name, int rows) {

		super(name, rows);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		if(!(event.getWhoClicked() instanceof Player)) return;

		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		player.updateInventory();

	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
