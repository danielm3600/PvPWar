package me.pvpgod.pvpwar.events;


import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static me.pvpgod.pvpwar.shops.armorsmith.createArmorSmithInventory;
import static me.pvpgod.pvpwar.shops.miner.createMinerShopInventory;
import static me.pvpgod.pvpwar.shops.swordsmith.createSwordSmithInventory;

public class OnRightClickOfEntity implements Listener {

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();

        if (clickedEntity.getCustomName() != null && clickedEntity.getCustomName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&aArmorsmith"))) {
            p.openInventory(createArmorSmithInventory(p));
        } else if (clickedEntity.getCustomName() != null && clickedEntity.getCustomName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&aSwordsmith"))) {
            p.openInventory(createSwordSmithInventory(p));
        } else if (clickedEntity.getCustomName() != null && clickedEntity.getCustomName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&aMiner"))) {
            p.openInventory(createMinerShopInventory(p));
        }
    }
}
