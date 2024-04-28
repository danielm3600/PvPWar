package me.pvpgod.pvpwar.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class OnPlayerCraft implements Listener {
    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCrafting is disabled!"));
        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
    }
}
