package dev.arcticdevelopment.arctictools.placeholders;

import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.APAPIPlaceholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CrystalPlaceholder implements APAPIPlaceholder {

	@Override
	public String getIdentifier() {
		return "crystals";
	}

	@Override
	public String getValue(Player player) {

		FileConfiguration playerData = APlayerData.getPlayerData(player);
		return playerData.getInt("crystals") + "";
	}
}
