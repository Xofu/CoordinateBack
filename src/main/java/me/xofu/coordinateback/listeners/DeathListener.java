package me.xofu.coordinateback.listeners;

import me.xofu.coordinateback.CoordinateBack;
import me.xofu.coordinateback.death.Death;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private CoordinateBack instance;

    public DeathListener(CoordinateBack instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(instance.getDeathManager().hasDeath(player.getUniqueId())) {
            instance.getDeathManager().removeDeath(instance.getDeathManager().getDeathByOwner(player.getUniqueId()));
        }

        Death death = new Death(player.getUniqueId(), player.getLocation());
        instance.getDeathManager().addDeath(death);
    }

}
