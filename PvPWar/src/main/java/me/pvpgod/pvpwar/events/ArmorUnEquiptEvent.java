package me.pvpgod.pvpwar.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;


public class ArmorUnEquiptEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && isArmor(clickedItem.getType())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Check if the player is right-clicking and holding armor
        if (event.getAction().name().contains("RIGHT") && item != null && isArmor(item.getType())) {
            event.setCancelled(true);
        }
    }

    private boolean isArmor(Material material) {
        return material.toString().endsWith("_HELMET") ||
                material.toString().endsWith("_CHESTPLATE") ||
                material.toString().endsWith("_LEGGINGS") ||
                material.toString().endsWith("_BOOTS");
    }

    private void removeAllArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.updateInventory();
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();

        if (isArmor(item.getType())) {
            removeAllArmor(player);
        }
    }

}
