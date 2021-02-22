
package dev.arcticdevelopment.arctictools;

import dev.arcticdevelopment.arctictools.commands.BaseCommand;
import dev.arcticdevelopment.arctictools.listeners.PlayerFishListener;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcticTools extends JavaPlugin {

    public static ArcticTools INSTANCE;
    public static Plugin WORLDGUARD;

    @Override
    public void onLoad() {
        try {

            WORLDGUARD = getServer().getPluginManager().getPlugin("WorldGuard");

            WorldGuardHook.registerFlag("arctic-fishing",false);
        } catch(Exception e) {
            System.out.println("penis");
        }

    }

    @Override
    public void onEnable() {

        INSTANCE = this;

        loadConfig();

        ArcticAPI.configInit(this, "prefix", "error-prefix");

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {


    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {
        //ABaseCommand apiCommand = new BaseCommand("arcticfishing");

//        apiCommand.registerCommand(new SetExitLocation("setexit"));
//        getCommand("printer").setExecutor(new PrinterCommand());
    }

    private void registerListeners() {

        getServer().getPluginManager().registerEvents(new PlayerFishListener(), this);
    }
}
