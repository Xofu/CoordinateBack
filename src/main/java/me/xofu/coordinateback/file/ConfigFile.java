package me.xofu.coordinateback.file;

import me.xofu.coordinateback.CoordinateBack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private CoordinateBack instance;

    public String name;
    public File file;
    private FileConfiguration config;

    public ConfigFile(String name, CoordinateBack instance) {
        this.instance = instance;

        this.name = name;
        this.file = new File(instance.getDataFolder(), name);

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();

            if (instance.getResource(name) == null) {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                instance.saveResource(name, false);
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save() {
        try {
            this.getConfig().save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
