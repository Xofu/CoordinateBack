package me.xofu.coordinateback;

import me.xofu.coordinateback.commands.BackCommand;
import me.xofu.coordinateback.death.DeathManager;
import me.xofu.coordinateback.listeners.DeathListener;
import me.xofu.coordinateback.task.TaskManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CoordinateBack extends JavaPlugin {

    private DeathManager deathManager;
    private TaskManager taskManager;

    @Override
    public void onEnable() {
        deathManager = new DeathManager(this);
        taskManager = new TaskManager(this);

        registerListeners();
        registerCommands();
        taskManager.runTasks();

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        deathManager.save();
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    public void registerCommands() {
        getCommand("back").setExecutor(new BackCommand(this));
    }

    public DeathManager getDeathManager() {
        return deathManager;
    }
}
