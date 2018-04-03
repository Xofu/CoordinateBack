package me.xofu.coordinateback.task.tasks;

import me.xofu.coordinateback.CoordinateBack;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathSaveTask extends BukkitRunnable {

    private CoordinateBack instance;

    public DeathSaveTask(CoordinateBack instance) {
        this.instance = instance;

        runTaskTimerAsynchronously(instance, 1, 6000);
    }

    @Override
    public void run() {
        instance.getDeathManager().save();
    }
}
