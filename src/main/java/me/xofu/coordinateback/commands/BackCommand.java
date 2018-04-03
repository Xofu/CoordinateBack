package me.xofu.coordinateback.commands;

import me.xofu.coordinateback.CoordinateBack;
import me.xofu.coordinateback.death.Death;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    private CoordinateBack instance;

    public BackCommand(CoordinateBack instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NOT_A_PLAYER")));
            return true;
        }

        Player player = (Player) sender;
        if(!player.hasPermission("coordinateback.use")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NO_PERMISSION")));
            return true;
        }

        if(!instance.getDeathManager().hasDeath(player.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NO_DEATH")));
            return true;
        }

        Death death = instance.getDeathManager().getDeathByOwner(player.getUniqueId());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("BACK").replace("%world%", death.getLocation().getWorld().getName()).replace("%x%", String.valueOf(death.getLocation().getBlockX())).replace("%y%", String.valueOf(death.getLocation().getBlockY())).replace("%z%", String.valueOf(death.getLocation().getBlockZ()))));
        return false;
    }
}
