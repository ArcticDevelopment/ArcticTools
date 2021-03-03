package dev.arcticdevelopment.arctictools.commands.rods;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.controllers.LeaderboardManager;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishTopCommand implements CommandExecutor {

	public static String getPlaceColor(int i) {

		switch(i) {

			case 1:
				return "&e";
			case 2:
				return "&7";
			case 3:
				return "&6";
		}

		return "&f";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;
		List<Map.Entry<String, Integer>> leaderboard = LeaderboardManager.getLeaderboard();
		List<String> message = new ArrayList<>();

		if(!player.hasPermission("arctic.tools.player.fishtop")) {
			AOutput.error(player, ArcticTools.CONFIG.getConfiguration().getString("messages.no-permission"));
			return false;
		}

		System.out.println(player.getDisplayName());

		int page = 1;
		int pages = leaderboard.size() / 10 + 1;
		try {
			page = Math.min(Math.max(Integer.parseInt(args[0]), pages), 1);
		} catch(Exception ignored) {}

		message.add(ArcticAPI.prefix + "&bFishTop &7(&3" + page + "&7/&3" + pages + "&7)");
		for(int i = (page - 1) * 10; i < page * 10; i++) {

			if(i >= leaderboard.size()) break;

			Map.Entry<String, Integer> entry = leaderboard.get(i);

			message.add(" * " + getPlaceColor(i + 1) + (i + 1) + ">&f " + entry.getKey() + "&7 // " + getPlaceColor(i + 1) + entry.getValue());
		}

		for(String line : message) {

			AOutput.color(player, line);
		}

		return false;
	}
}
