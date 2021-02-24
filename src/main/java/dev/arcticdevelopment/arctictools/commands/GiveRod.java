package dev.arcticdevelopment.arctictools.commands;

import dev.kyro.arcticapi.builders.AItemStackBuilder;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.nms.ANBTItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class GiveRod extends ASubCommand {

	public GiveRod(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;

		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 25;

		Player player = (Player) sender;
		Inventory playerInventory = player.getInventory();
		ItemStack rodItemStack = new ItemStack(Material.FISHING_ROD);
		ItemMeta rodMeta = rodItemStack.getItemMeta();
		rodItemStack.setItemMeta(rodMeta);


		ANBTItemStack NBTItemStack = new ANBTItemStack(rodItemStack);
		ItemMeta NBTMeta = NBTItemStack.getItemMeta();
		NBTMeta = NBTItemStack.hasItemMeta() ? NBTItemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(NBTItemStack.getType());
		NBTItemStack.setItemMeta(NBTMeta);
		String identifier = UUID.randomUUID().toString();
		System.out.println(NBTItemStack);
		System.out.println(identifier);

		NBTItemStack.setNBTTag("UUID",identifier);
		NBTItemStack.setNBTTag("TotalFish", String.valueOf(0));
		playerInventory.addItem(NBTItemStack);




//		if (args.size() == 0) {
//
//
//		} else if (args.size() == 1){
//
//		} else {

		//AOutput.send(player, "You have unused args");

	}
}
