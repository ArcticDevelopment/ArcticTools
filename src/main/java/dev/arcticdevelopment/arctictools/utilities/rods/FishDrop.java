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

	public static FishDrop getRareDrop(FishDropRarity rarity)  {

		List<FishDrop> copy = new ArrayList<>(drops);
		Collections.shuffle(copy);
		for (FishDrop fishDrop: copy) {
			FishDropRarity fishDropRarity = fishDrop.getRarity().getNextRarity();
			if (rarity == fishDropRarity) {
				System.out.println("og rarity" + rarity.toString());
				System.out.println("new rarity" + fishDropRarity.toString());
				return fishDrop;
			}
		}
		System.out.println("could not find target rarity");
		return copy.get(1);



	}

}
