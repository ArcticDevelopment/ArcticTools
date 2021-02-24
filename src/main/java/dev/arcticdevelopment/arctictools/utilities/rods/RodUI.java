package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class RodUI extends AInventoryGUI {

	public RodUI(String name, int rows) {

		super(name, rows);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		if (!(event.getWhoClicked() instanceof Player)) return;

		Player player = (Player) event.getWhoClicked();
		player.updateInventory();
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}

	public static AInventoryBuilder create() {

		return new AInventoryBuilder(null, 45, "inventoryName")
				.createBorder(Material.STAINED_GLASS_PANE, 3)
				.setSlot(Material.DIAMOND_SWORD, 0 , 20, "&bWeapon Enchants", null)
				.setSlot(Material.DIAMOND_CHESTPLATE, 0 , 22, "&bArmor Enchants", null)
				.setSlot(Material.DIAMOND_PICKAXE, 0 , 24, "&bTool Enchants", null)
				.addEnchantGlint(true, 20, 22, 24);
	}
}
