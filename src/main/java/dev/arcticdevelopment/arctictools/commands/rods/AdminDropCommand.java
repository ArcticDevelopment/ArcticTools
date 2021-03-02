package dev.arcticdevelopment.arctictools.commands.rods;

import dev.arcticdevelopment.arctictools.inventories.LootEditorGUI;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.data.APlayerData;
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

		player.openInventory(new LootEditorGUI().getInventory());
	}
}
