
package dev.arcticdevelopment.arctictools;

import dev.arcticdevelopment.arctictools.commands.BaseCommand;
import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcticTools extends JavaPlugin {

    public static ArcticTools INSTANCE;

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

//        getServer().getPluginManager().registerEvents(new PrinterEvents(), this);
    }
}
