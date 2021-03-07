package dev.arcticdevelopment.arctictools.commands.rods;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.controllers.SellManager;
import dev.kyro.arcticapi.misc.AOutput;
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

		if (!player.hasPermission("arctic.tools.player.sell")) {
			AOutput.error(player, ArcticTools.CONFIG.getConfiguration().getString("messages.no-permission"));
			return false;
		}

		SellManager.sellFish(player);
		return false;
	}
}
