package me.pvpgod.pvpwar.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static me.pvpgod.pvpwar.commands.God.godPlayers;

public class OnPlayerCommandPreprocess implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/god")) {
            event.setCancelled(true);
            Player sender = event.getPlayer();
            if (sender.hasPermission("command.god")) {
                if (godPlayers.contains(sender.getName())) {
                    godPlayers.remove(sender.getName());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are no longer in God Mode!"));
                } else {
                    godPlayers.add(sender.getName());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now in God Mode!"));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou dont have permission for this command!"));
            }
        }
        if (event.getMessage().startsWith("/bukkit:?")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("help");
        }
        if (event.getMessage().startsWith("/bukkit:help")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("help");
        }
        if (event.getMessage().startsWith("/minecraft:me")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("me");
        }
        if (event.getMessage().startsWith("/advancement")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("me");
        }
        if (event.getMessage().startsWith("/minecraft:advancement")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("me");
        }
        if (event.getMessage().startsWith("/say")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("me");
        }
        if (event.getMessage().startsWith("/minecraft:say")) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            p.performCommand("me");
        }
    }
}
