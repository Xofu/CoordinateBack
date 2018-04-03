package me.xofu.coordinateback.task;

import me.xofu.coordinateback.CoordinateBack;
import me.xofu.coordinateback.task.tasks.DeathSaveTask;

public class TaskManager {

    private CoordinateBack instance;

    private DeathSaveTask deathSaveTask;

    public TaskManager(CoordinateBack instance) {
        this.instance = instance;

        deathSaveTask = new DeathSaveTask(instance);
    }

    public void runTasks() {
        deathSaveTask.run();
    }
}
