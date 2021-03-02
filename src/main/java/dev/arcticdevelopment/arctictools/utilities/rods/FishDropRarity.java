package dev.arcticdevelopment.arctictools.utilities.rods;

public enum FishDropRarity {

	BASIC,
	ENCHANTED,
	MYSTICAL,
	LEGENDARY,
	DIVINE;

	public FishDropRarity getNextRarity() {

		switch(this) {
			case BASIC:
				return ENCHANTED;
			case ENCHANTED:
				return MYSTICAL;
			case MYSTICAL:
				return LEGENDARY;
			case LEGENDARY:
				return DIVINE;
		}
		return DIVINE;
	}
}
