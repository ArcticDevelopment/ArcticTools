package dev.arcticdevelopment.arctictools.placeholders;

import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.APAPIPlaceholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.Locale;

public class FormattedCrystalPlaceholder implements APAPIPlaceholder {

	@Override
	public String getIdentifier() {
		return "crystals_formatted";
	}

	@Override
	public String getValue(Player player) {

		FileConfiguration playerData = APlayerData.getPlayerData(player);
		return NumberFormat.getNumberInstance(Locale.US).format(playerData.getInt("crystals"));
	}
}
