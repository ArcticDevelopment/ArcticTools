package dev.arcticdevelopment.arctictools.controllers;

import dev.arcticdevelopment.arctictools.utilities.NBTTags;
import org.bukkit.event.Listener;

public abstract class RodEnchant implements Listener {

	public abstract String getName();
	public abstract NBTTags getNBTTag();
	public abstract int getMaxLevel();

	public RodEnchant() {

	}
}
