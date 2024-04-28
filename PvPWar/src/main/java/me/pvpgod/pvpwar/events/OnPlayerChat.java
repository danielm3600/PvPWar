package me.pvpgod.pvpwar.events;

import me.pvpgod.pvpwar.commands.StaffChat;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class OnPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (StaffChat.getStaffChat(player)) {
            event.setCancelled(true);
            for (Player loopplayer : Bukkit.getOnlinePlayers()) {
                if (loopplayer.hasPermission("staffchat.use")) {
                    User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
                    String group = user.getPrimaryGroup();
                    if (group.equalsIgnoreCase("owner")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&cOWNER&7] &c" + player.getName() + ": &f" + message));
                    }
                    else if (group.equalsIgnoreCase("admin")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&cADMIN&7] &c" + player.getName() + ": &f" + message));
                    }
                    else if (group.equalsIgnoreCase("builder")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&6BUILDER&7] &6" + player.getName() + ": &f" + message));
                    }
                    else if (group.equalsIgnoreCase("mod")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&eMOD&7] &e" + player.getName() + ": &f" + message));
                    }
                    else if (group.equalsIgnoreCase("helper")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&9HELPER&7] &9" + player.getName() + ": &f" + message));
                    }
                    else if (group.equalsIgnoreCase("tester")) {
                        loopplayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[STAFFCHAT] &7[&bTESTER&7] &b" + player.getName() + ": &f" + message));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat2(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (message.contains("%")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot use this symbol!"));
            event.setCancelled(true);
        }
        else {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
            String group = user.getPrimaryGroup();
            if (user.getPrimaryGroup().equalsIgnoreCase("owner")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&cOWNER&7] &c" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("admin")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&cADMIN&7] &c" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("builder")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&6BUILDER&7] &6" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("mod")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&eMOD&7] &e" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("helper")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9HELPER&7] &9" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("tester")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&bTESTER&7] &b" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("vip")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&aVIP&7] &a" + player.getName() + ": &f" + message));
            }
            else if (user.getPrimaryGroup().equalsIgnoreCase("default")) {
                event.setFormat(ChatColor.translateAlternateColorCodes('&', "&7" + player.getName() + ": " + message));
            }
        }
    }
}
