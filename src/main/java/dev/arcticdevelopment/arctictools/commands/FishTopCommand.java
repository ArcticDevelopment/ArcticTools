package dev.arcticdevelopment.arctictools.commands;

import dev.arcticdevelopment.arctictools.controllers.LeaderboardManager;
import dev.kyro.arcticapi.commands.ASubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class FishTopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;

		List<Map.Entry<String, Integer>> leaderboard = LeaderboardManager.getLeaderboard();

		for(Map.Entry<String, Integer> entry : leaderboard) {
			AO
		}

		return false;
	}
}
