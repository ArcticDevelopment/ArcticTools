package dev.arcticdevelopment.arctictools.utilities;

public enum NBTTags {

	ROD_UUID("arod-uuid"),
	ROD_FISH("arod-fish"),
	ROD_ENCHANT_SPAWNERS("arod-spawners");

	private final String ref;

	NBTTags(String ref) {

		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}
}
