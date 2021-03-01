package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.kyro.arcticapi.data.APlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class LeaderboardManager {

	private static Map<String, Integer> leaderboardMap = new HashMap<>();

	public static void init() {

		new BukkitRunnable() {
			@Override
			public void run() {

				updateLeaderboard();
			}
		}.runTaskTimer(ArcticTools.INSTANCE, 0L, 20L);
	}

	private static void updateLeaderboard() {

		leaderboardMap.clear();

		Map<UUID, FileConfiguration> allPlayerData = APlayerData.getAllData();

		for(Map.Entry<UUID, FileConfiguration> playerData : allPlayerData.entrySet()) {

			FileConfiguration playerConfig = playerData.getValue();
			String displayname = playerConfig.getString("displayname");
			int fish = playerConfig.getInt("total-fish");

			leaderboardMap.put(displayname, fish);
		}
	}

	public static List<Map.Entry<String, Integer>> getLeaderboard() {

		List<Map.Entry<String, Integer>> sortedLeaderboard = new LinkedList<>(leaderboardMap.entrySet());
		sortedLeaderboard.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
		return sortedLeaderboard;
	}
}
