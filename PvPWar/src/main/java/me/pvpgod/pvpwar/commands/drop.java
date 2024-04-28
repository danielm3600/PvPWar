package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class drop implements CommandExecutor {
    private static HashMap<Player, Boolean> drop = new HashMap<>();
    public static void setDrop(Player player, boolean value) {
        drop.put(player, value);
    }
    public static boolean getDrop(Player player) {
        return drop.getOrDefault(player, false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("drop")) {
                if (getDrop(p)) {
                    setDrop(p, false);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can no longer drop items! (Type /drop to toggle back on!)"));
                }
                else {
                    setDrop(p, true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou can now drop items! (Type /drop to toggle back off!)"));
                }
            }
        }
        return true;
    }
}
