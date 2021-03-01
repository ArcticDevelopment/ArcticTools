package dev.arcticdevelopment.arctictools.commands;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.Base64;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminDropCommand extends ASubCommand {

	public AdminDropCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		FileConfiguration playerData = APlayerData.getPlayerData(player);

		if(player.getItemInHand() == null) {
			AOutput.error(player, "You have to be holding an item in your hand");
			return;
		}

		AConfig.set("loot", Base64.itemTo64(player.getItemInHand()));
		ArcticTools.INSTANCE.saveConfig();
	}
}
