package dev.arcticdevelopment.arctictools.controllers;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.inventories.LootEditorGUI;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDrop;
import dev.arcticdevelopment.arctictools.utilities.rods.FishDropRarity;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import dev.kyro.arcticapi.misc.AOutput;
import dev.kyro.arcticapi.misc.ASound;
import net.minecraft.server.v1_8_R3.EntityFishingHook;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishManager implements Listener {

	public static List<TreasureDrop> treasureDrops = new ArrayList<>();
	public static Map<Item, Player> recentlyFished = new HashMap<>();

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
		int multiDropLevel = nbtRod.getInteger(NBTTag.ROD_ENCHANT_MULTI_DROP.getRef());
//		For testing only
		double treasureChance = (double) treasureLevel / 2;

		if(random < treasureChance) {

//			double totalWeight = 0;
//			for(TreasureDrop treasureDrop : treasureDrops) {
//
//				totalWeight += treasureDrop.getWeight();
//			}
//
//			double countWeight = 0;
//			double randomTreasure = Math.random() * totalWeight;
//			for(TreasureDrop treasureDrop : treasureDrops) {
//
//				countWeight += treasureDrop.getWeight();
//				if(countWeight >= randomTreasure) return treasureDrop.getItemStack();
//			}

			if(LootEditorGUI.drops.size() != 0) return LootEditorGUI.drops.get((int) (Math.random() * LootEditorGUI.drops.size()));
		}

		int random2 = (int) (Math.random() * FishDrop.drops.size());
		FishDrop fishDrop = FishDrop.drops.get(random2);

		if (Math.random() * 20 <= luckLevel ) {

			FishDropRarity rarity = fishDrop.getRarity().getNextRarity();;

			fishDrop = FishDrop.getRareDrop(rarity);
		}

		assert fishDrop != null;
		drop = new ItemStack(fishDrop.getDrop());

		if(Math.random() * 80 < multiDropLevel) {

			int random3 = (int) (Math.random() * 8) + 3;
			drop.setAmount(random3 + 1);

			new BukkitRunnable() {
				int count = 0;
				@Override
				public void run() {

					ASound.play(player, Sound.ORB_PICKUP, (float) (0.7 + (count * 0.03)));

					if(++count == random3) cancel();
				}
			}.runTaskTimer(ArcticTools.INSTANCE, 0L, 2L);
		}
		return drop;
	}

	@EventHandler(ignoreCancelled = true)
	public void onStartFish(PlayerFishEvent event) {

		if(event.getState() != PlayerFishEvent.State.FISHING) return;

		NBTItem nbtRod = new NBTItem(event.getPlayer().getItemInHand());
		int lureLevel = nbtRod.getInteger(NBTTag.ROD_ENCHANT_LURE.getRef());

		setBiteTime(event.getHook(), (int) (Math.pow(0.9, lureLevel) * 350));
	}

	@EventHandler
	public static void onItemPickup(PlayerPickupItemEvent event) {

		if(!recentlyFished.containsKey(event.getItem()) || recentlyFished.get(event.getItem()) == event.getPlayer()) return;

		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public static void onFish(PlayerFishEvent event) {

		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();

		if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

		if (ArcticTools.CONFIG.getConfiguration().getBoolean("enable-worldguard-hook")) {
			if (!WorldGuardHook.hasFlag(player.getLocation(),"arctic-fishing")
					|| !event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) return;
		}

		ItemStack drop = getDrop(player, player.getItemInHand());

		((Item) event.getCaught()).setItemStack(drop);
		recentlyFished.put((Item) event.getCaught(), player);
		new BukkitRunnable() {
			@Override
			public void run() {
				recentlyFished.entrySet().removeIf(entry -> entry.getKey() == event.getCaught());
			}
		}.runTaskLater(ArcticTools.INSTANCE, 60L);

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

		AOutput.send(player, "You just received &b" + crystals + "&f crystals");
	}

	private void setBiteTime(FishHook hook, int time) {
		net.minecraft.server.v1_8_R3.EntityFishingHook hookCopy = (EntityFishingHook) ((CraftEntity) hook).getHandle();

		Field fishCatchTime = null;

		try {
			fishCatchTime = net.minecraft.server.v1_8_R3.EntityFishingHook.class.getDeclaredField("aw");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		assert fishCatchTime != null;
		fishCatchTime.setAccessible(true);

		try {
			fishCatchTime.setInt(hookCopy, time);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		fishCatchTime.setAccessible(false);
	}
}
