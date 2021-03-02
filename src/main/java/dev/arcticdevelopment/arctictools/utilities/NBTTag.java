package dev.arcticdevelopment.arctictools.utilities;

public enum NBTTag {

	ROD_UUID("arod-uuid"),
	ROD_FISH("arod-fish"),
	ROD_ENCHANT_TREASURE("arod-spawners"),
	ROD_ENCHANT_SOULBOUND("arod-soulbound"),
	ROD_ENCHANT_CRYSTALBOOST("arod-crystalboost"),
	ROD_ENCHANT_DOUBLE_DROP("arod-double-drop"),
	ROD_ENCHANT_LURE("arod-lure"),
	ROD_ENCHANT_LUCK("arod-luck");

	private final String ref;

	NBTTag(String ref) {

		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}
}
