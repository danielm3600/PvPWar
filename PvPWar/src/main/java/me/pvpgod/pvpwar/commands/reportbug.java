package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class reportbug implements CommandExecutor {
    private final PvPWar plugin;
    public reportbug(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("reportbug")) {
                Player p = (Player) sender;
                if (args.length > 0) {
                    StringBuilder message = new StringBuilder();

                    for (String arg : args) {
                        message.append(arg).append(" ");
                    }

                    String bugDescription = String.join(" ", args);
                    UUID playerUUID = p.getUniqueId();
                    String playerName = ((Player) sender).getName();

                    // Save bug report to configuration file
                    long timestamp = System.currentTimeMillis();
                    plugin.getConfig().set("bugs." + playerUUID.toString() + "." + timestamp, playerName + ": " + bugDescription);
                    plugin.saveConfig();

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBug Report successfully sent to all online staff, thank you for helping out the server! :)"));

                    for(Player loopplayer : Bukkit.getOnlinePlayers()) {
                        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(loopplayer);
                        String group = user.getPrimaryGroup();
                        if (group.equalsIgnoreCase("owner")) {
                            loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + p.getName() + " has submitted a bug report, the report: &6" + message.toString().trim()));
                        }
                        else if (group.equalsIgnoreCase("admin")) {
                            loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + p.getName() + " has submitted a bug report, the report: &6" + message.toString().trim()));
                        }
                        else if (group.equalsIgnoreCase("builder")) {
                            loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + p.getName() + " has submitted a bug report, the report: &6" + message.toString().trim()));
                        }
                        else if (group.equalsIgnoreCase("mod")) {
                            loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + p.getName() + " has submitted a bug report, the report: &6" + message.toString().trim()));
                        }
                        else if (group.equalsIgnoreCase("heler")) {
                            loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + p.getName() + " has submitted a bug report, the report: &6" + message.toString().trim()));
                        }

                    }
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid Usage! Use: /reportbug <bug>!"));
                }
            }
        }
        return true;
    }
}
