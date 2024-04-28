package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (sender.hasPermission("command.fly")) {
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are no longer flying!"));
                    }
                    else {
                        p.setAllowFlight(true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now flying!"));
                    }
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont have permission for this command!"));
                    return true;
                }
            }
        }
        return true;
    }
}
