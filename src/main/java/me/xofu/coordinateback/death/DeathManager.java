package me.xofu.coordinateback.death;

import me.xofu.coordinateback.CoordinateBack;
import me.xofu.coordinateback.file.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeathManager {

    private CoordinateBack instance;

    private List<Death> deaths;
    private ConfigFile deathsFile;
    private FileConfiguration deathsConfig;

    public DeathManager(CoordinateBack instance) {
        this.instance = instance;

        deaths = new ArrayList<>();
        deathsFile = new ConfigFile("deaths.yml", instance);
        deathsConfig = deathsFile.getConfig();

        load();
    }

    public void addDeath(Death death) {
        deaths.add(death);
    }

    public void removeDeath(Death death) {
        deaths.remove(death);
    }

    public boolean hasDeath(UUID uuid) {
        if(getDeathByOwner(uuid) != null) {
            return true;
        }
        return false;
    }

    public Death getDeathByOwner(UUID uuid) {
        for(Death death: getDeaths()) {
            if(death.getOwner().equals(uuid)) {
                return death;
            }
        }
        return null;
    }

    public List<Death> getDeaths() {
        if(deaths == null) {
            return null;
        }
        return deaths;
    }

    public void load() {
        for(String string: deathsConfig.getStringList("List.deaths")) {
            UUID uuid = UUID.fromString(string);

            World world = Bukkit.getWorld(deathsConfig.getString("Death." + string + ".world"));
            int blockX = deathsConfig.getInt("Death." + string + ".x");
            int blockY = deathsConfig.getInt("Death." + string + ".y");
            int blockZ = deathsConfig.getInt("Death." + string + ".z");

            Location location = new Location(world, blockX, blockY, blockZ);

            Death death = new Death(uuid, location);
            addDeath(death);
        }
    }

    public void save() {
        List<String> deathsList = new ArrayList<>();

        deathsConfig.set("Death", null);
        deathsFile.save();
        for(Death death: getDeaths()) {
            deathsList.add(death.getOwner().toString());
            deathsConfig.set("Death." + death.getOwner().toString() + ".world", death.getLocation().getWorld().getName());
            deathsConfig.set("Death." + death.getOwner().toString() + ".x", death.getLocation().getBlockX());
            deathsConfig.set("Death." + death.getOwner().toString() + ".y", death.getLocation().getBlockY());
            deathsConfig.set("Death." + death.getOwner().toString() + ".z", death.getLocation().getBlockZ());
            deathsFile.save();
        }
        deathsConfig.set("List.deaths", deathsList);
        deathsFile.save();
    }
}
