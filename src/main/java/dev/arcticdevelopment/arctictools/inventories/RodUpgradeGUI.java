package dev.arcticdevelopment.arctictools.inventories;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.controllers.LeaderboardManager;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.gui.AInventoryGUI;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
				.createBorder(Material.STAINED_GLASS_PANE, 3)
				.setSlots(Material.STAINED_GLASS_PANE, 3, 10, 12, 14, 16, 19, 20, 24, 25, 28, 30, 32, 34)
				.setSlots(Material.STAINED_GLASS_PANE, 11, 29, 33, 37, 39, 40, 41, 43, 47, 51);

		for(RodEnchant enchant : RodEnchant.enchants) {

			int level = nbtRod.getInteger(enchant.getNBTTag().getRef());

			inventoryBuilder.setSlot(Material.ENCHANTED_BOOK, 0, enchant.getSlot(), enchant.getName(), enchant.getLore(level));
		}

		baseGUI = inventoryBuilder.getInventory();

		ItemStack itemStack1 = new ItemStack(Material.PRISMARINE_SHARD);
		itemStack1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta itemMeta1 = itemStack1.getItemMeta();
		itemMeta1.setDisplayName(ChatColor.AQUA + "Rod Enchants");
		itemMeta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemStack1.setItemMeta(itemMeta1);
		baseGUI.setItem(22, itemStack1);

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
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
