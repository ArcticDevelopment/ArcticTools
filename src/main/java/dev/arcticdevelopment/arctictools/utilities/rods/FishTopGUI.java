package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Map;
import java.util.UUID;

public class FishTopGUI  extends AInventoryGUI {

	public FishTopGUI() {

		super("Fish Top", 3);

		Map<UUID, FileConfiguration> playerData = APlayerData.getAllData();

		for(Map.Entry<UUID, FileConfiguration> player : playerData.entrySet()) {
			player.getValue()
		}
	}

	@Override
	public void onClick(InventoryClickEvent event) {

	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
