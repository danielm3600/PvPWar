package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class deletebugreport implements CommandExecutor {
    private final PvPWar plugin;
    public deletebugreport(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deletebugreport")) {
            if (sender.hasPermission("command.deletebugreport")) {
                if (args.length != 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cUsage: /deletebugreport <player> <date>"));
                    return true;
                }

                String playerName = args[0];
                UUID playerUUID = UUID.fromString(playerName);
                String date = args[1];

                // Check if the specified bug report exists
                if (plugin.getConfig().contains("bugs." + playerUUID.toString() + "." + date)) {
                    plugin.getConfig().set("bugs." + playerUUID.toString() + "." + date, null);
                    plugin.saveConfig();

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aBug report deleted successfully!"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aBug report not found!"));
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou don't have permission to use this command!"));
            }
        }
        return true;
    }
}
