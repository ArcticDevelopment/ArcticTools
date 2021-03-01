package dev.arcticdevelopment.arctictools.inventories;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.Base64;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LootEditor extends AInventoryGUI {

	public LootEditor() {
		super("Loot Editor", 1);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();

		if(event.getCurrentItem() == null) return;
		ItemStack clickedItem = event.getCurrentItem();

		if(event.getClickedInventory() instanceof PlayerInventory) {

			event.setCurrentItem(new ItemStack(Material.AIR));
			event.getInventory().addItem(clickedItem);

		} else {

			for(int i = event.getSlot(); i < event.getInventory().getSize() - 1; i++) {

				event.getInventory().setItem(i, event.getInventory().getItem(i + 1));
			}
			event.getInventory().setItem(event.getInventory().getSize() - 1, new ItemStack(Material.AIR));

//			event.setCurrentItem(new ItemStack(Material.AIR));
			player.getInventory().addItem(clickedItem);
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {


	}

	@Override
	public void onClose(InventoryCloseEvent event) {

		AConfig.set("rod-loot", new ArrayList<>());

		for(ItemStack drop : event.getInventory().getContents()) {

			if(drop == null) continue;
			AConfig.addToList("rod-loot", Base64.itemTo64(drop));
		}
		ArcticTools.INSTANCE.saveConfig();
	}

	@Override
	public Inventory getInventory() {

		Inventory inventory = super.getInventory();
		List<String> drops = AConfig.getStringList("rod-loot");

		for(String drop : drops) {
			try {
				inventory.addItem(Base64.itemFrom64(drop));
			} catch(IOException ignored) {}
		}

		return inventory;
	}
}
