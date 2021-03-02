package dev.arcticdevelopment.arctictools.commands;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AdminReloadCommand extends ASubCommand {

	public AdminReloadCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;

		long start = System.currentTimeMillis();

		ArcticTools.CONFIG.reloadDataFile();
		ArcticAPI.prefix = ArcticTools.CONFIG.getConfiguration().getString("prefix");
		ArcticAPI.errorPrefix = ArcticTools.CONFIG.getConfiguration().getString("error-prefix");
		AOutput.send(player, "Reloaded config in &b" + (System.currentTimeMillis() - start) + "ms");
	}
}
