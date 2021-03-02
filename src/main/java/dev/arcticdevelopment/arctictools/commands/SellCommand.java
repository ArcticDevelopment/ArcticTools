package dev.arcticdevelopment.arctictools.commands;

import dev.arcticdevelopment.arctictools.controllers.SellManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

		if (!(commandSender instanceof Player)) {
			return false;
		}
		Player player = ((Player) commandSender).getPlayer();

		SellManager.sellFish(player);
		return false;
	}
}
