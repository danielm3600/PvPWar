package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.*;

public class reports implements CommandExecutor {
    private final PvPWar plugin;

    public reports(PvPWar plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reports")) {
            if (sender.hasPermission("command.reports")) {
                if (args.length != 1) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /reports <player>"));
                    return true;
                }

                String playerName = args[0];
                UUID targetUUID = getUUIDFromName(playerName);

                if (plugin.getConfig().contains("reports." + targetUUID)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eReports for " + playerName + ":"));

                    Set<String> reportTimes = plugin.getConfig().getConfigurationSection("reports." + targetUUID).getKeys(false);

                    List<String> sortedTimes = new ArrayList<>(reportTimes);
                    sortedTimes.sort(Comparator.naturalOrder());

                    int reportID = 0;

                    for (String time : reportTimes) {
                        reportID++;
                        long timestamp = Long.parseLong(time);
                        String reason = plugin.getConfig().getString("reports." + targetUUID + "." + time);
                        String reporter = plugin.getConfig().getString("reporters." + targetUUID + "." + time);

                        // Format the timestamp to a readable date
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setTimeZone(TimeZone.getDefault());
                        String formattedDate = sdf.format(new Date(timestamp));

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a - Report #" + reportID + " - Date: " + formattedDate + ", Reporter: " + reporter + ", Reason: " + reason));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + playerName + " has not been reported."));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                return true;
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
