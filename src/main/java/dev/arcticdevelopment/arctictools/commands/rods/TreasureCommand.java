package dev.arcticdevelopment.arctictools.commands.rods;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.inventories.LootEditorGUI;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class TreasureCommand extends ASubCommand {

	public TreasureCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		FileConfiguration playerData = APlayerData.getPlayerData(player);

		if (!player.hasPermission("arctic.tools.admin.drops")) {
			AOutput.error(player, ArcticTools.CONFIG.getConfiguration().getString("messages.no-permission"));
			return;
		}

		player.openInventory(new LootEditorGUI().getInventory());
	}
}
