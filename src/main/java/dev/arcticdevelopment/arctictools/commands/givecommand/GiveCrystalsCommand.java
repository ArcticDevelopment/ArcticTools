package dev.arcticdevelopment.arctictools.commands.givecommand;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveCrystalsCommand extends ASubCommand {

	public GiveCrystalsCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(args.size() < 2) {

			AOutput.errorIfPlayer(sender, "Usage: /atools give crystals <player> <amount>");
			return;
		}

		Player targetPlayer = null;
		for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {

			if(!onlinePlayer.getName().equalsIgnoreCase(args.get(0))) continue;

			targetPlayer = onlinePlayer;
			break;
		}
		if(targetPlayer == null) {

			AOutput.errorIfPlayer(sender, "That player is not online");
			return;
		}
		FileConfiguration playerData = APlayerData.getPlayerData(targetPlayer);

		int amount;
		try {
			amount = Integer.parseInt(args.get(1));
		} catch(Exception exception) {
			AOutput.errorIfPlayer(sender, "Not a valid number");
			return;
		}
		if(amount < 1) {
			AOutput.errorIfPlayer(sender, "Not a valid number");
			return;
		}

		playerData.set("crystals", playerData.getInt("crystals") + amount);
		APlayerData.savePlayerData(targetPlayer);
		AOutput.sendIfPlayer(sender, "Gave &9" + targetPlayer.getName() + " &b" + amount + " &fcrystals");
		AOutput.send(targetPlayer, "You have been given &b" + amount + " &fcrystals");
	}
}
