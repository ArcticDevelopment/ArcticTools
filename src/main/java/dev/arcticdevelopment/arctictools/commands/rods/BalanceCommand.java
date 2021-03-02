package dev.arcticdevelopment.arctictools.commands.rods;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class BalanceCommand extends ASubCommand {

	public BalanceCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;

		FileConfiguration playerData = APlayerData.getPlayerData(player);
		int crystals = playerData.getInt("crystals");

		AOutput.send(player, "You have: &b" + crystals + "&f crystal" + (crystals == 1 ? "" : "s"));
	}
}
