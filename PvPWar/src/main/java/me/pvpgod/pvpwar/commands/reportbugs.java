package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.*;

public class reportbugs implements CommandExecutor {
    private final PvPWar plugin;
    public reportbugs(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reportbugs")) {
            if (sender.hasPermission("command.reportbugs")) {
                Map<String, Map<String, String>> bugReports = new HashMap<>();
                if (plugin.getConfig().contains("bugs")) {
                    ConfigurationSection bugsSection = plugin.getConfig().getConfigurationSection("bugs");
                    for (String playerUUID : bugsSection.getKeys(false)) {
                        Map<String, String> playerReports = new HashMap<>();
                        ConfigurationSection playerSection = bugsSection.getConfigurationSection(playerUUID);
                        for (String date : playerSection.getKeys(false)) {
                            String bugDescription = playerSection.getString(date);
                            playerReports.put(date, bugDescription);
                        }
                        bugReports.put(playerUUID, playerReports);
                    }
                }
                if (bugReports.isEmpty()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo bugs have been reported!"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eAll Reported Bugs:"));

                    for (Map.Entry<String, Map<String, String>> entry : bugReports.entrySet()) {
                        Map<String, String> playerReports = entry.getValue();

                        for (Map.Entry<String, String> reportEntry : playerReports.entrySet()) {
                            TextComponent message = new TextComponent(" - Date: " + formatDate(reportEntry.getKey()) + ", Player: " + reportEntry.getValue());
                            message.setColor(ChatColor.GREEN);

                            TextComponent deleteButton = new TextComponent(" [Delete]");
                            deleteButton.setColor(ChatColor.RED);
                            deleteButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("Click to delete this bug report")}));
                            deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletebugreport " + entry.getKey() + " " + reportEntry.getKey()));

                            // Add the delete button to the message
                            message.addExtra(deleteButton);

                            // Send the message
                            ((Player) sender).spigot().sendMessage(message);
                        }
                    }
                }
            }
            else {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                return true;
            }
        }

        return true;
    }
    private String formatDate(String timestamp) {
        long time = Long.parseLong(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time));
    }

}
