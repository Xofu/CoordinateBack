package me.xofu.coordinateback.death;

import org.bukkit.Location;

import java.util.UUID;

public class Death {

    private UUID owner;
    private Location location;

    public Death(UUID owner, Location location) {
        this.owner = owner;
        this.location = location;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }
}
