package dev.arcticdevelopment.arctictools.commands.controllers;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;


public abstract class RodEnchant implements Listener {

	public abstract String getName();
	public abstract String getReferenceName();
	public abstract int getMaxLevel();

	public RodEnchant() {

	}
}
