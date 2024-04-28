package me.pvpgod.pvpwar.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.pvpgod.pvpwar.commands.God.godPlayers;

public class OnPlayerDamageEvent implements Listener {
    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (godPlayers.contains(p.getName())) {
                event.setCancelled(true);
                if (event instanceof EntityDamageByEntityEvent) {
                    Entity attacker = ((EntityDamageByEntityEvent)event).getDamager();
                    attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can't hurt players who have God mode turned on!"));
                }
            }
            else {
                if (event instanceof EntityDamageByEntityEvent) {
                    Entity attacker = ((EntityDamageByEntityEvent) event).getDamager();
                    if (godPlayers.contains(attacker.getName())) {
                        event.setCancelled(true);
                        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can't hurt players when you are in God Mode!"));
                    }
                }
            }
        }
    }
}
