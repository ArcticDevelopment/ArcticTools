package dev.arcticdevelopment.arctictools.commands.rods;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
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

		if(args.size() == 0) return;

		try {
			Integer.parseInt(args.get(0));
		} catch(Exception ignored) {

			AOutput.error(player, "Invalid number");
			return;
		}

		playerData.set("crystals", Integer.parseInt(args.get(0)));
		APlayerData.savePlayerData(player);
		AOutput.send(player, "Set crystals to &b" + args.get(0));
	}
}
