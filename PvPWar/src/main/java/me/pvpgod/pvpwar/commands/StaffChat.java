package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class StaffChat implements CommandExecutor {
    private static HashMap<Player, Boolean> staffchat = new HashMap<>();
    public static void setStaffChat(Player player, boolean value) {
        staffchat.put(player, value);
    }
    public static boolean getStaffChat(Player player) {
        return staffchat.getOrDefault(player, false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("staffchat.use")) {
                    if (getStaffChat(p)) {
                        setStaffChat(p, false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have toggled staff chat off, your messages are now visible to everyone!"));
                    }
                    else {
                        setStaffChat(p, true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have toggled staff chat on, your messages are only visible to staff members!"));
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        return true;
    }
}
