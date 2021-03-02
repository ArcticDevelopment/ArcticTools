package dev.arcticdevelopment.arctictools.controllers;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import dev.arcticdevelopment.arctictools.utilities.rods.enums.FishDropRarity;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FishManager implements Listener {

	public static List<TreasureDrop> treasureDrops = new ArrayList<>();

	public FishManager() {

		treasureDrops.add(new TreasureDrop(new ItemStack(Material.DIAMOND_BLOCK),1));
		treasureDrops.add(new TreasureDrop(new ItemStack(Material.EMERALD_BLOCK),1));
	}

	public static int getCrystals(Player player, ItemStack rod) {

		double crystals = (Math.random()*50) + 50;

		NBTItem NBTRod = new NBTItem(rod);

		crystals = Math.pow(1.1, NBTRod.getInteger(NBTTag.ROD_ENCHANT_CRYSTALBOOST.getRef())) * crystals;

		return (int) crystals;
	}

	public static ItemStack getDrop(Player player, ItemStack rod) {

		ItemStack drop;
		NBTItem nbtRod = new NBTItem(rod);

		double random = Math.random() * 100;

		int treasureLevel = nbtRod.getInteger(NBTTag.ROD_ENCHANT_TREASURE.getRef());
		int luckLevel = nbtRod.getInteger(NBTTag.ROD_ENCHANT_LUCK.getRef());
//		For testing only
		double treasureChance = treasureLevel * 1;

		if(random < treasureChance) {

			double totalWeight = 0;
			for(TreasureDrop treasureDrop : treasureDrops) {

				totalWeight += treasureDrop.getWeight();
			}

			double countWeight = 0;
			double randomTreasure = Math.random() * totalWeight;
			for(TreasureDrop treasureDrop : treasureDrops) {

				countWeight += treasureDrop.getWeight();
				if(countWeight >= randomTreasure) return treasureDrop.getItemStack();
			}
		}

		int random2 = (int) (Math.random() * FishDrop.drops.size());
		//drop = new ItemStack(FishDrop.drops.get(random2).getDrop());
		FishDrop fishDrop = FishDrop.drops.get(random2);
//		ItemMeta dropMeta = drop.getItemMeta();

//		double multiplier = (double) Math.round(Math.random() * 10 * 100)/100;
//
//		ALoreBuilder loreBuilder = new ALoreBuilder();
//		loreBuilder.addLore(multiplier + "");
//
//		dropMeta.setLore(loreBuilder.getLore());
//		drop.setItemMeta(dropMeta);

		if (((Math.random()*25)+25) <= treasureLevel ) {
			System.out.println("upgraded rarity");
			FishDropRarity rarity = fishDrop.getRarity().getNextRarity();
			fishDrop =  FishDrop.getRareDrop(rarity);

		}


		drop = new ItemStack(fishDrop.getDrop());
		return drop;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public static void onFish(PlayerFishEvent event) {

		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();

		if (!WorldGuardHook.hasFlag(player.getLocation(),"arctic-fishing")
				|| !event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) return;

		ItemStack drop = getDrop(player, player.getItemInHand());

		((Item) event.getCaught()).setItemStack(drop);

		NBTItem nbtItem = new NBTItem(event.getPlayer().getItemInHand());
		int totalFish = nbtItem.getInteger(NBTTag.ROD_FISH.getRef()) + 1;
		nbtItem.setInteger(NBTTag.ROD_FISH.getRef(), totalFish);
		event.getPlayer().setItemInHand(nbtItem.getItem());

		FileConfiguration playerData = APlayerData.getPlayerData(player);

		int crystals = getCrystals(player, nbtItem.getItem());

		playerData.set("ign", player.getName());
		playerData.set("crystals", playerData.getInt("crystals") + crystals);
		playerData.set("total-fish", playerData.getInt("total-fish") + 1);
		APlayerData.savePlayerData(player);

		AOutput.send(player, "You just received &b" + crystals + " crystals");

	}
}
