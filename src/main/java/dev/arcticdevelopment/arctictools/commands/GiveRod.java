package dev.arcticdevelopment.arctictools.commands;

import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.utilities.rods.RodManager;
import dev.kyro.arcticapi.commands.ASubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class GiveRod extends ASubCommand {

	public GiveRod(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;

		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 15;

		Player player = (Player) sender;
		Inventory playerInventory = player.getInventory();
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		ItemMeta rodMeta = rod.getItemMeta();
		Random random = new Random();

		String randomString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		System.out.println(randomString);


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
