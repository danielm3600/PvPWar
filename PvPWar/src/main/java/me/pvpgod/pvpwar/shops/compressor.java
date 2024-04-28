package me.pvpgod.pvpwar.shops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.pvpgod.pvpwar.board.board.getPlayerBalance;
import static me.pvpgod.pvpwar.board.board.takePlayerBalance;
import static me.pvpgod.pvpwar.shops.miner.createMinerShopInventory;

public class compressor implements Listener {

    @EventHandler
    public void onRightClickOnFurnace(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.FURNACE) {
                event.setCancelled(true);
                Player p = event.getPlayer();
                p.openInventory(createCompressorInventory(p));
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof CompressorHolder)) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        Material itemType = clickedItem.getType();

        if (itemType == Material.BARRIER) {
            player.closeInventory();
            return;
        }

        int requiredAmount = 64;
        double requiredAmountMoney = 0;

        switch (itemType) {
            case COBBLESTONE:
                requiredAmountMoney = 5;
                break;
            case COAL:
                requiredAmountMoney = 10;
                break;
            case IRON_INGOT:
                requiredAmountMoney = 15;
                break;
            case GOLD_INGOT:
                requiredAmountMoney = 20;
                break;
            case EMERALD:
                requiredAmountMoney = 25;
                break;
            case DIAMOND:
                requiredAmountMoney = 30;
                break;
            default:
                return;
        }

        int itemCount = getItemCount(player.getInventory().getContents(), itemType);

        if (itemCount < requiredAmount) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough " + itemType.toString().toLowerCase().replace("_", " ") + " for this purchase!"));
            player.closeInventory();
            return;
        }

        double playerBalance = getPlayerBalance(player.getName());

        if (playerBalance < requiredAmountMoney) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
            player.closeInventory();
            return;
        }

        ItemStack compressedItem = new ItemStack(itemType, 1);
        ItemMeta meta = compressedItem.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCompressed " + itemType.toString().toLowerCase().replace("_", " ")));
        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        compressedItem.setItemMeta(meta);

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == itemType) {
                if (!item.getItemMeta().getDisplayName().contains("p")) {
                    player.getInventory().removeItem(new ItemStack(item.getType(), requiredAmount));
                    break;
                }
            }
        }

        takePlayerBalance(player.getName(), requiredAmountMoney);
        player.getInventory().addItem(compressedItem);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully compressed the " + itemType.toString().toLowerCase().replace("_", " ") + "!"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }

    private int getItemCount(ItemStack[] items, Material material) {
        int count = 0;
        for (ItemStack item : items) {
            if (item != null && item.getType() == material && !item.getItemMeta().getDisplayName().contains("p")) {
                count += item.getAmount();
            }
        }
        return count;
    }


    public static Inventory createCompressorInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(new CompressorHolder(), 9 * 5, "Compressor");

        ItemStack glass = createItem(Material.BLACK_STAINED_GLASS_PANE, " ", null);
        for (int i = 0; i < 45; i++) {
            if ((i >= 12 && i < 15) || (i >= 21 && i < 24)) continue;
            inventory.setItem(i, glass);
        }

        inventory.setItem(36, createItem(Material.BARRIER, ChatColor.RED + "Close Menu", null));
        inventory.setItem(44, createItem(Material.REDSTONE, ChatColor.DARK_AQUA + "Elite Compressor", " ", ChatColor.GRAY + "Coming Soon!"));
        inventory.setItem(12, createCompressibleItem(Material.COBBLESTONE, ChatColor.GREEN + "Compress Cobblestone", "$5"));
        inventory.setItem(13, createCompressibleItem(Material.COAL, ChatColor.GREEN + "Compress Coal", "$10"));
        inventory.setItem(14, createCompressibleItem(Material.IRON_INGOT, ChatColor.GREEN + "Compress Iron", "$15"));
        inventory.setItem(21, createCompressibleItem(Material.GOLD_INGOT, ChatColor.GREEN + "Compress Gold", "$20"));
        inventory.setItem(22, createCompressibleItem(Material.EMERALD, ChatColor.GREEN + "Compress Emerald", "$25"));
        inventory.setItem(23, createCompressibleItem(Material.DIAMOND, ChatColor.GREEN + "Compress Diamond", "$30"));

        return inventory;
    }

    private static ItemStack createItem(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        if (lore != null && lore.length > 0) {
            meta.setLore(Arrays.asList(lore));
        }
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createCompressibleItem(Material material, String displayName, String cost) {
        ItemStack item = createItem(material, displayName, null);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        List<String> itemLore = new ArrayList<>();
        itemLore.add(" ");
        itemLore.add(ChatColor.GRAY + "Click to compress 64 " + material.toString().toLowerCase().replace("_", " ") + " into 1!");
        itemLore.add(" ");
        itemLore.add(ChatColor.GRAY + "Costs: ");
        itemLore.add(" ");
        itemLore.add(ChatColor.GRAY + "- 64 " + material.toString().toLowerCase().replace("_", " "));
        itemLore.add(ChatColor.GRAY + "- " + cost);
        meta.setLore(itemLore);
        item.setItemMeta(meta);

        return item;
    }

    public static class CompressorHolder implements org.bukkit.inventory.InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
