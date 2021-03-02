package dev.arcticdevelopment.arctictools.commands.rods;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class PayCommand extends ASubCommand {

	public PayCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;

		if(args.size() < 2) {

			AOutput.error(player, "Invalid usage: /crystals pay <player> <amount>");
			return;
		}

		int amount;
		try {
			amount = Integer.parseInt(args.get(1));
		} catch(Exception exception) {
			AOutput.error(player, "Invalid amount");
			return;
		}
		if(amount <= 0) {
			AOutput.error(player, "Invalid amount");
			return;
		}

		FileConfiguration playerData = APlayerData.getPlayerData(player);
		if(amount > playerData.getInt("crystals")) {

			AOutput.error(player, "That would make your balance negative");
			return;
		}

		for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {

			if(!args.get(0).equalsIgnoreCase(onlinePlayer.getName())) continue;

			FileConfiguration targetData = APlayerData.getPlayerData(onlinePlayer);
			targetData.set("crystals", targetData.getInt("crystals") + amount);
			playerData.set("crystals", playerData.getInt("crystals") - amount);
			APlayerData.savePlayerData(player);
			APlayerData.savePlayerData(onlinePlayer);
			AOutput.send(player, "You have paid " + onlinePlayer.getName() + " &b" + amount + "&f crystal" + (amount == 1 ? "" : "s"));
			AOutput.send(onlinePlayer, "You have received &b" + amount + "&f crystals from " + player.getName());
			return;
		}

		AOutput.error(player, "That player doesn't exist or is offline");
	}
}
