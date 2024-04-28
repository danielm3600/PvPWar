package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class God implements CommandExecutor {
    public static ArrayList<String> godPlayers = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("god")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("command.god")) {
                    if (godPlayers.contains(p.getName())) {
                        godPlayers.remove(p.getName());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are no longer in God Mode!"));
                    } else {
                        godPlayers.add(p.getName());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now in God Mode!"));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont have permission for this command!"));
                    return true;
                }
            }
        }
        return true;
    }
}