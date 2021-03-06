package dev.arcticdevelopment.arctictools.commands.rods;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminCrystalsCommand extends ASubCommand {

	public AdminCrystalsCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		FileConfiguration playerData = APlayerData.getPlayerData(player);

		if (!player.hasPermission("arctic.tools.admin.crystals")) {
			AOutput.error(player, ArcticTools.CONFIG.getConfiguration().getString("messages.no-permission"));
			return;
		}

		if (args.size() < 2) return;

		try {
			Integer.parseInt(args.get(1));

		} catch(Exception ignored) {

			AOutput.error(player, "Invalid number");
			return;
		}

		String arg = args.get(0);
		String receiver = args.get(1);
		int amount = Integer.parseInt(args.get(2));
		Player receiverPlayer;

		for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
			if (onlinePlayer.getName().equals(receiver)) {
				receiverPlayer = onlinePlayer;
				APlayerData.getPlayerData(receiverPlayer);
			}
		}

				switch(arg) {

			case "set":

				playerData.set("crystals", amount);
				APlayerData.savePlayerData(player);
				AOutput.send(player, "Set crystals to &b" + playerData.getInt("crystals"));
				return;

			case "add":

				if (amount < 1) {
					AOutput.error(player,"Invalid number" );
					return;
				}

				playerData.set("crystals", playerData.getInt("crystals") + amount );
				APlayerData.savePlayerData(player);
				AOutput.send(player, "Added &b" + amount + "&f crystals");
				return;

			case "remove":

				if (amount < 1) {
					AOutput.error(player,"Invalid number" );
					return;
				}

				playerData.set("crystals", playerData.getInt("crystals") - amount );
				APlayerData.savePlayerData(player);
				AOutput.send(player, "Removed &b" + amount  + "&f crystals");
				return;

			default:
				AOutput.error(player, "Invalid args");

		}
	}
}
