package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("help")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(" ");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.UNDERLINE + "&aPvPWar Help"));
                p.sendMessage(" ");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&eIf you just started, do '/start' to start the tutorial!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&eRemember, if you need any help, ask fellow members or staff."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&eEverything that you need will be located near spawn!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&c&lPvP is enabled past the red line, so be cautious!"));
                p.sendMessage(" ");
            }
        }
        return true;
    }
}
