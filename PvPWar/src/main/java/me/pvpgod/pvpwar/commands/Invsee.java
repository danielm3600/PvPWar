package me.pvpgod.pvpwar.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("invsee.use")) {
                    if (args.length == 1) {
                        Player target;
                        target = Bukkit.getPlayer(args[0]);
                        String targetName = args[0];
                        UUID targetUUID = getUUIDFromName(targetName);
                        if (targetUUID != null) {
                            Player targetPlayer = Bukkit.getPlayer(targetUUID);
                            if (targetPlayer != null && targetPlayer.isOnline()) {
                                p.openInventory(target.getInventory());
                                p.getOpenInventory().setTitle(target.getName() + "'s Inventory");
                            }
                            else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + "&c is offline!"));
                                return true;
                            }
                        }
                        else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " is offline!"));
                            return true;
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /invsee <player>"));
                        return true;
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
    private UUID getUUIDFromName(String playerName) {
        // Attempt to get player UUID using Bukkit's offline player method
        try {
            return Bukkit.getOfflinePlayer(playerName).getUniqueId();
        } catch (Exception e) {
            // Handle exceptions (e.g., player not found)
            return null;
        }
    }
}
