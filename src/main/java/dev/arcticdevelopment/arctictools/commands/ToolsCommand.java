package dev.arcticdevelopment.arctictools.commands;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ToolsCommand extends ABaseCommand {

	public ToolsCommand(String baseExecutor) {


		super(baseExecutor);
	}

	@Override
	public void executeBase(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;
		createHelp().send((Player) sender);
	}

	@Override
	public void executeFail(CommandSender sender, List<String> args) {

		executeBase(sender,args);
	}
}
