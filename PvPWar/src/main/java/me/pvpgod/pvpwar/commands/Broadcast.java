package me.pvpgod.pvpwar.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("command.broadcast")) {
                if (args.length == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid Usage! Use: '/broadcast <text>'"));
                        return true;
                }

                StringBuilder message = new StringBuilder();

                for (String arg : args) {
                        message.append(arg).append(" ");
                }

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(" ");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&bANNOUNCEMENT&7] " + message));
                    p.sendMessage(" ");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                return true;
            }
        }
        return true;
    }
}
