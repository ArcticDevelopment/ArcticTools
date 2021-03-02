package dev.arcticdevelopment.arctictools.utilities.rods;

import dev.arcticdevelopment.arctictools.utilities.rods.enums.FishDropRarity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FishDrop {

	public static List<FishDrop> drops = new ArrayList<>();

	public ItemStack drop;
	public FishDropRarity rarity;

	public ItemStack getDrop() {
		return drop;
	}

	public FishDrop(ItemStack drop, FishDropRarity rarity) {

		this.rarity = rarity;
		this.drop = drop;
		drops.add(this);
	}

	public FishDropRarity getRarity() {

		return rarity;
	}

	public static FishDrop getRareDrop(FishDropRarity rarity) {

		List<FishDrop> copy = new ArrayList<>(drops);
		Collections.shuffle(copy);

		for (FishDrop testDrop: copy) {

			FishDropRarity fishDropRarity = testDrop.getRarity();

			if (rarity == fishDropRarity) {

				return testDrop;
			}
		}

		System.out.println("could not find target rarity");
		return null;



	}

}
