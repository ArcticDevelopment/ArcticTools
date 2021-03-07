package dev.arcticdevelopment.arctictools.commands.givecommand;

import de.tr7zw.nbtapi.NBTItem;
import dev.arcticdevelopment.arctictools.ArcticTools;
import dev.arcticdevelopment.arctictools.controllers.RodEnchant;
import dev.arcticdevelopment.arctictools.utilities.NBTTag;
import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.misc.AOutput;
import dev.kyro.arcticapi.misc.AUtil;
import org.bukkit.Bukkit;
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

public class GiveRodCommand extends ASubCommand {

	public GiveRodCommand(String executor) {

		super(executor);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		System.out.println(args);

		if(args.size() < 1) {

			AOutput.errorIfPlayer(sender, "Usage: /atools give rod <player>");
			return;
		}

		Player targetPlayer = null;
		for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {

			if(!onlinePlayer.getName().equalsIgnoreCase(args.get(0))) continue;

			targetPlayer = onlinePlayer;
			break;
		}
		if(targetPlayer == null) {

			AOutput.errorIfPlayer(sender, "That player is not online");
			return;
		}

		Inventory playerInventory = targetPlayer.getInventory();
		ItemStack rodItemStack = new ItemStack(Material.FISHING_ROD);
		rodItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta rodMeta = rodItemStack.getItemMeta();

		if (!sender.hasPermission("arctic.tools.admin.give")) {
			AOutput.errorIfPlayer(sender, ArcticTools.CONFIG.getConfiguration().getString("messages.no-permission"));
			return;
		}

		rodMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',ArcticTools.CONFIG.getString("rod-name")));
		rodMeta.spigot().setUnbreakable(true);
		rodMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		rodMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		rodItemStack.setItemMeta(rodMeta);

		String identifier = UUID.randomUUID().toString();
		NBTItem nbtRod = new NBTItem(rodItemStack);
		nbtRod.setString(NBTTag.ROD_UUID.getRef(), identifier);

		RodEnchant.updateRod(nbtRod);
		AUtil.giveItemSafely(targetPlayer, nbtRod.getItem());
	}
}
