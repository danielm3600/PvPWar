package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class report implements CommandExecutor {
    private final PvPWar plugin;
    public report(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (sender instanceof Player) {
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /report <player> <reason>"));
                    return true;
                }
            }

            String playerName = args[0];

            if (playerName.equals(sender.getName())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot report yourself!"));
                return true;
            }

            String targetName = args[0];
            UUID targetUUID = getUUIDFromName(targetName);

            if (targetUUID != null) {
                Player targetPlayer = Bukkit.getPlayer(targetUUID);
                if (targetPlayer != null && targetPlayer.isOnline()) {
                    String reason = String.join(" ", args).substring(playerName.length() + 1);

                    long timestamp = System.currentTimeMillis();
                    plugin.getConfig().set("reports." + targetUUID  + "." + System.currentTimeMillis(), reason);
                    plugin.getConfig().set("reporters." + targetUUID    + "." + timestamp, sender.getName());
                    plugin.saveConfig();

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aYou have successfully reported " + playerName + " for: " + reason + "! &bThank you for helping out the server! :)"));

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("command.reports")) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + sender.getName() + "&a has reported &6" + playerName + "&a for: &e" + reason));
                        }
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&c" + args[0] + " &cis offline!"));
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&c" + args[0] + " is offline!"));
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
