package dev.arcticdevelopment.arctictools.inventories;

import dev.kyro.arcticapi.gui.AInventoryGUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LootEditor extends AInventoryGUI {

	public static List<ItemStack> drops = new ArrayList<>();

	public LootEditor() {
		super("Loot Editor", 6);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		System.out.println(event.getClick());
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
