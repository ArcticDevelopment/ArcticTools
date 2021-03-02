package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.kyro.arcticapi.data.APlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
		}.runTaskTimer(ArcticTools.INSTANCE, 0L, 1L);
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

	public static int getPosition(Player player) {

		List<Map.Entry<String, Integer>> sortedLeaderboard = getLeaderboard();

		for(Map.Entry<String, Integer> entry : sortedLeaderboard) {

			if(!player.getDisplayName().equals(entry.getKey())) continue;

			return sortedLeaderboard.indexOf(entry) + 1;
		}

		return -1;
	}
}
