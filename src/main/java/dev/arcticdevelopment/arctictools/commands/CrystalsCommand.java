package dev.arcticdevelopment.arctictools.commands;

import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CrystalsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		FileConfiguration playerData = APlayerData.getPlayerData(player);
		int crystals = playerData.getInt("crystals");

		AOutput.send(player, "You have: &b" + crystals + "&f crystals");

		return false;
	}
}
