package me.k81.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }



    @Override
    public void onEnable() {
        plugin = this;
        this.commands();
        this.EventRegister();
    }

    private void EventRegister() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    private void commands() {
        this.getCommand("Handle").setExecutor(new HandleCommand());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}