package dev.arcticdevelopment.arctictools.commands;

import dev.kyro.arcticapi.commands.ASubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GiveRod extends ASubCommand {

	public GiveRod(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;

		Player player = (Player) sender;
		Inventory playerInventory = player.getInventory();
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		ItemMeta rodMeta = rod.getItemMeta();

		rodMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&aRod"));
		rod.setItemMeta(rodMeta);

		playerInventory.addItem(rod);

//		if (args.size() == 0) {
//
//
//		} else if (args.size() == 1){
//
//		} else {

		//AOutput.send(player, "You have unused args");

	}
}
