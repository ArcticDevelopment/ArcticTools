package dev.arcticdevelopment.arctictools.utilities.rods;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.controllers.LeaderboardManager;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class RodUpgradeGUI extends AInventoryGUI {

	private NBTItem nbtRod;
	private int rodSlot;

	public RodUpgradeGUI(Player player) {

		super("Upgrade Harvester Hoe", 6);

		nbtRod = new NBTItem(player.getItemInHand());
		rodSlot = player.getInventory().getHeldItemSlot();
		FileConfiguration playerData = APlayerData.getPlayerData(player);
		String leaderboardPos = LeaderboardManager.getPosition(player) != -1 ? LeaderboardManager.getPosition(player) + "" : "Haven't Fished";

		ItemStack itemStack = player.getItemInHand();
		ItemMeta itemMeta = itemStack.getItemMeta();
		String displayName = itemMeta.getDisplayName();
		ArrayList<String> lore = (ArrayList<String>) itemMeta.getLore();

		ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
		playerHeadMeta.setOwner(player.getName());
		playerHeadMeta.setDisplayName(player.getDisplayName());
		ALoreBuilder loreBuilder = new ALoreBuilder(playerHead)
				.addLore("&b * &fCrystals: &3" + playerData.getInt("crystals"))
				.addLore("&b * &fTotal Fish: &3" + playerData.getInt("total-fish"))
				.addLore("&b * &fPosition: &3#" + leaderboardPos);
		playerHeadMeta.setLore(loreBuilder.colorize().getLore());
		playerHead.setItemMeta(playerHeadMeta);

		AInventoryBuilder inventoryBuilder = new AInventoryBuilder(null, 54, "Rod Upgrades")
				.setSlot(Material.PRISMARINE_SHARD, 0 , 22, "&bWeapon Enchants", null)
				.addEnchantGlint(true, 22);

		for(RodEnchant enchant : RodEnchant.enchants) {

			int level = nbtRod.getInteger(enchant.getNBTTag().getRef());

			inventoryBuilder.setSlot(Material.ENCHANTED_BOOK, 0, enchant.getSlot(), enchant.getName(), enchant.getLore(level));
		}

		baseGUI = inventoryBuilder.getInventory();
		baseGUI.setItem(42, nbtRod.getItem());
		baseGUI.setItem(38, playerHead);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		if(!(event.getWhoClicked() instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();

		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()) return;
		ItemStack clickedItem = event.getCurrentItem();

		for(RodEnchant enchant : RodEnchant.enchants) {

			if(!clickedItem.getItemMeta().getDisplayName().equals(enchant.getName())) continue;

			int level = nbtRod.getInteger(enchant.getNBTTag().getRef());
			int cost = enchant.getLevelCost(level);

			FileConfiguration playerData = APlayerData.getPlayerData(player);
			int crystals = playerData.getInt("crystals");

			if(level == enchant.getMaxLevel()) {

				AOutput.error(player, "Already at max level");
				return;
			} else if(crystals < cost) {

				AOutput.error(player, "Not enough crystals");
				return;
			}

			nbtRod.setInteger(enchant.getNBTTag().getRef(), level + 1);
			playerData.set("crystals", playerData.getInt("crystals") - cost);
			APlayerData.savePlayerData(player);

			ItemStack updatedUpgradeItem = player.getOpenInventory().getItem(enchant.getSlot()).clone();
			ItemMeta itemMeta = updatedUpgradeItem.getItemMeta();
			itemMeta.setLore(enchant.getLore(level + 1));
			updatedUpgradeItem.setItemMeta(itemMeta);
			player.getOpenInventory().setItem(enchant.getSlot(), updatedUpgradeItem);

			RodEnchant.updateRod(nbtRod);
			player.getInventory().setItem(rodSlot, nbtRod.getItem());
			player.getOpenInventory().setItem(42, nbtRod.getItem());

			AOutput.send(player, "Spent " + cost + " crystals to upgrade " + enchant.getName() + "&f to level " + (level + 1));
		}

//		switch(event.getCurrentItem().getItemMeta().getDisplayName()) {
//			case "\u00a7b\u00a7lLuck":
//				System.out.println("luck");
//				return;
//			case "\u00a7c\u00a7lSoulbound":
//
//				nbtRod.setInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef(), nbtRod.getInteger(NBTTag.ROD_ENCHANT_SOULBOUND.getRef()) + 1);
//				RodEnchant.updateEnchant(nbtRod, RodEnchant.enchants.get(1));
//				player.getInventory().setItem(rodSlot, nbtRod.getItem());
//
//				return;
//			case "\u00a7f\u00a7lLure":
//				System.out.println("lure");
//				return;
//			case "\u00a76\u00a7lSize Boost":
//				System.out.println("size boost");
//				return;
//			case "\u00a7a\u00a7lTreasue Finder":
//
//				nbtRod.setInteger(NBTTag.ROD_ENCHANT_TREASURE.getRef(), nbtRod.getInteger(NBTTag.ROD_ENCHANT_TREASURE.getRef()) + 1);
//				RodEnchant.updateEnchant(nbtRod, RodEnchant.enchants.get(0));
//				player.getInventory().setItem(rodSlot, nbtRod.getItem());
//
//				return;
//			case "\u00a7d\u00a7lCrystal Boost":
//
//				nbtRod.setInteger(NBTTag.ROD_ENCHANT_CRYSTALBOOST.getRef(), nbtRod.getInteger(NBTTag.ROD_ENCHANT_CRYSTALBOOST.getRef()) + 1);
//				RodEnchant.updateEnchant(nbtRod, RodEnchant.enchants.get(2));
//				player.getInventory().setItem(rodSlot, nbtRod.getItem());
//		}

	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
