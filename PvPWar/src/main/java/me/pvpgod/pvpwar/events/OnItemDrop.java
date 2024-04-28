package me.pvpgod.pvpwar.events;

import me.pvpgod.pvpwar.commands.drop;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnItemDrop implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        if (!drop.getDrop(p)) {
            event.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have dropping items disabled, to switch it back on, do /drop!"));
        }
    }
}
