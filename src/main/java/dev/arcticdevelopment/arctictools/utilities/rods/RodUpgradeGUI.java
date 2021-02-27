package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RodUpgradeGUI extends AInventoryGUI {

	public RodUpgradeGUI(Player player) {

		super("Upgrade Harvester Hoe", 6);

		ItemStack itemStack = player.getItemInHand();
		ItemMeta itemMeta = itemStack.getItemMeta();
		String displayName = itemMeta.getDisplayName();
		ArrayList<String> lore = (ArrayList<String>) itemMeta.getLore();

		AInventoryBuilder inventoryBuilder = new AInventoryBuilder(null, 54, "Rod Upgrades")
				.createBorder(Material.STAINED_GLASS_PANE, 7)

				.setSlot(Material.ENCHANTED_BOOK, 0 , 10, "&b&lLuck", null)
				.setSlot(Material.ENCHANTED_BOOK, 0 , 13, "&c&lSpawner Finder", null)
				.setSlot(Material.ENCHANTED_BOOK, 0 , 16, "&f&lLure", null)
				.setSlot(Material.ENCHANTED_BOOK, 0 , 21, "&6&lSize Boost", null)
				.setSlot(Material.ENCHANTED_BOOK, 0 , 23, "&a&lKeys Finder", null)
				.setSlot(Material.ENCHANTED_BOOK, 0 , 31, "&d&lCrystal Boost", null)

				.setSlot(Material.PRISMARINE_SHARD, 0 , 22, "&bWeapon Enchants", null)
				.setSlot(Material.FISHING_ROD, 0 , 42, displayName, lore)
				.setSlot(Material.WATCH, 0 , 38, "&bWeapon Enchants", null)

				.addEnchantGlint(true, 22);

		baseGUI = inventoryBuilder.getInventory();
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		if(!(event.getWhoClicked() instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();

		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()) return;

		switch(event.getCurrentItem().getItemMeta().getDisplayName()) {
			case "\u00a7b\u00a7lLuck":
				System.out.println("luck");
				return;
			case "\u00a7c\u00a7lSpawner Finder":
				System.out.println("spawner");
				return;
			case "\u00a7f\u00a7lLure":
				System.out.println("lure");
				return;
			case "\u00a76\u00a7lSize Boost":
				System.out.println("size boost");
				return;
			case "\u00a7a\u00a7lKeys Finder":
				System.out.println("keys");
				return;
			case "\u00a7d\u00a7lCrystal Boost":
				System.out.println("Crystal Boost");
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
