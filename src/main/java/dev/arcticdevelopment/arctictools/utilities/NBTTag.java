package dev.arcticdevelopment.arctictools.utilities;

public enum NBTTag {

	ROD_UUID("arod-uuid"),
	ROD_FISH("arod-fish"),
	ROD_ENCHANT_TREASURE("arod-spawners"),
	ROD_ENCHANT_SOULBOUND("arod-soulbound");

	private final String ref;

	NBTTag(String ref) {

		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}
}
