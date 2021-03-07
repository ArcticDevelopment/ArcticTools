package dev.arcticdevelopment.arctictools.inventories;

import dev.arcticdevelopment.arctictools.utilities.Base64;
import dev.kyro.arcticapi.data.AData;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import dev.kyro.arcticapi.misc.AOutput;
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

public class LootEditorGUI extends AInventoryGUI {

	public static List<ItemStack> drops = new ArrayList<>();

	public LootEditorGUI() {

		super("Loot Editor", 6);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();

		if(event.getCurrentItem() == null) return;
		ItemStack clickedItem = event.getCurrentItem();

		if(event.getClickedInventory() instanceof PlayerInventory) {

			if(event.getInventory().firstEmpty() != -1) {

				event.setCurrentItem(new ItemStack(Material.AIR));
				event.getInventory().setItem(event.getInventory().firstEmpty(), clickedItem);
			} else {

				AOutput.error(player, "Inventory full");
			}
		} else {

			for(int i = event.getSlot(); i < event.getInventory().getSize() - 1; i++) {

				event.getInventory().setItem(i, event.getInventory().getItem(i + 1));
			}
			event.getInventory().setItem(event.getInventory().getSize() - 1, new ItemStack(Material.AIR));

			if(player.getInventory().firstEmpty() != -1) {

				player.getInventory().setItem(player.getInventory().firstEmpty(), clickedItem);
			}
		}
	}

	public static void updateDrops() {

		drops.clear();
		AData fishingLoot = new AData("fishing-loot");
		List<String> dropStrings = fishingLoot.getConfiguration().getStringList("rod-loot");

		for(String drop : dropStrings) {
			try {
				drops.add(Base64.itemFrom64(drop));
			} catch(IOException ignored) {}
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {


	}

	@Override
	public void onClose(InventoryCloseEvent event) {

		AData fishingLoot = new AData("fishing-loot");
		fishingLoot.getConfiguration().set("rod-loot", new ArrayList<>());

		for(ItemStack drop : event.getInventory().getContents()) {

			if(drop == null) continue;
			fishingLoot.addToList("rod-loot", Base64.itemTo64(drop));
		}
		fishingLoot.saveDataFile();
		updateDrops();
	}

	@Override
	public Inventory getInventory() {

		Inventory inventory = super.getInventory();

		int count = 0;
		for(ItemStack drop : drops) {
			inventory.setItem(count++, drop);
		}

		return inventory;
	}
}
