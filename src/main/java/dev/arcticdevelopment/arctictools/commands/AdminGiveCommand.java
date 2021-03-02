package dev.arcticdevelopment.arctictools.commands;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.kyro.arcticapi.commands.ASubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class AdminGiveCommand extends ASubCommand {

	public AdminGiveCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if (!(sender instanceof Player)) return;

		Player player = (Player) sender;
		Inventory playerInventory = player.getInventory();
		ItemStack rodItemStack = new ItemStack(Material.FISHING_ROD);
		rodItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta rodMeta = rodItemStack.getItemMeta();

		rodMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&b&nFishing&r &7Rod"));
		rodMeta.spigot().setUnbreakable(true);
		rodMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		rodMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		rodItemStack.setItemMeta(rodMeta);

		String identifier = UUID.randomUUID().toString();
		NBTItem nbtRod = new NBTItem(rodItemStack);
		nbtRod.setString(NBTTag.ROD_UUID.getRef(), identifier);

		RodEnchant.updateRod(nbtRod);
		playerInventory.addItem(nbtRod.getItem());
	}
}
