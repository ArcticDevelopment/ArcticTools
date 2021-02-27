package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RodUpgradeInventory {

	public RodUpgradeUI rodUpgradeUI = new RodUpgradeUI("thing",6);

	public static void click(InventoryClickEvent event, Player player, Inventory openInventory, ItemStack clickedItem) {

		event.setCancelled(true);

		switch(event.getCurrentItem().getItemMeta().getDisplayName()) {
			case "&b&lLuck":
				System.out.println("luck");
				return;
			case "&c&lSpawner Finder":
				System.out.println("spawner");
				return;
			case "&f&lLure":
				System.out.println("lure");
				return;
			case "&6&lSize Boost":
				System.out.println("size boost");
				return;
			case "&a&lKeys Finder":
				System.out.println("keys");
				return;
			case "&d&lCrystal Boost":
				System.out.println("Crystal Boost");
				return;
		}
	}

	public static AInventoryBuilder create(Player player) {

		ItemStack itemStack = player.getItemInHand();
		ItemMeta itemMeta = itemStack.getItemMeta();
		String displayName = itemMeta.getDisplayName();
		ArrayList<String> lore = (ArrayList<String>) itemMeta.getLore();

		return new AInventoryBuilder(null, 54, "Rod Upgrades")
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

		this.rodUpgradeUI.inank and
	}
}
