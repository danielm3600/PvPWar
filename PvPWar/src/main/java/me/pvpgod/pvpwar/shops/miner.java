package me.pvpgod.pvpwar.shops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.pvpgod.pvpwar.board.board.getPlayerBalance;
import static me.pvpgod.pvpwar.board.board.takePlayerBalance;

public class miner implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof MinerShopHolder) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                if (clickedItem.getType() == Material.BARRIER) {
                    player.closeInventory();
                }

                if (clickedItem.getType() == Material.WOODEN_PICKAXE) {

                    int cobblestone = 0;
                    if (clickedItem.getItemMeta().getDisplayName().contains("2")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 16) {
                            int check = 0;
                            for (ItemStack item3 : player.getInventory().getContents()) {
                                if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                    if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                        if (check == 0)
                                        {
                                            player.getInventory().removeItem(new ItemStack(item3.getType(), 16));
                                            check = 1;
                                        }
                                    }
                                }
                            }

                            for (ItemStack item3 : player.getInventory().getContents()) {
                                if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                    if (item3.getItemMeta().getDisplayName().contains("1")) {
                                        player.getInventory().remove(item3);
                                    }
                                }
                            }

                            ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a2&7]"));
                            meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 1"));
                            meta.setUnbreakable(true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                            item.setItemMeta(meta);
                            player.getInventory().addItem(item);
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 2 wooden pickaxe!"));
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("3")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 32) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 5) {
                                int check = 0;
                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0)
                                            {
                                                player.getInventory().removeItem(new ItemStack(item3.getType(), 32));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 5);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("2")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a3&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 2"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 3 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("4")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 64) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 10) {
                                int check = 0;
                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0)
                                            {
                                                player.getInventory().removeItem(new ItemStack(item3.getType(), 64));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 10);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("3")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a4&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 3"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 4 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("5")) {

                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 2) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 30) {
                                int cobblestoneRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 2) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 2 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 30);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("4")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a5&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 4", ChatColor.GRAY + "- Fortune 1"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 5 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("6")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 4) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 50) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 4) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 4 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }
                                takePlayerBalance(player.getName(), 50);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("5")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a6&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 5", ChatColor.GRAY + "- Fortune 2"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 6 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("7")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 8) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 100) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 8) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 8 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }
                                takePlayerBalance(player.getName(), 100);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("6")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a7&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 6", ChatColor.GRAY + "- Fortune 3"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 6, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 7 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("8")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 16) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 150) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 16) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 16 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }
                                takePlayerBalance(player.getName(), 150);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("7")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a8&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 7", ChatColor.GRAY + "- Fortune 4"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 4, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 8 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("9")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 32) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 200) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 32) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 32 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }
                                takePlayerBalance(player.getName(), 200);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("8")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a9&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 8", ChatColor.GRAY + "- Fortune 5"));
                                meta.setUnbreakable(true);

                                meta.addEnchant(Enchantment.DIG_SPEED, 8, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 9 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("10")) {
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone+= item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 64) {
                            double playerBalance = getPlayerBalance(player.getName());
                            if (playerBalance >= 250) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 64) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 64 - cobblestoneRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }
                                takePlayerBalance(player.getName(), 250);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("9")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a10&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 9", ChatColor.GRAY + "- Fortune 6"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 9, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 6, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 10 wooden pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                }

                else if (clickedItem.getType() == Material.STONE_PICKAXE) {
                    int coal = 0;

                    if (clickedItem.getItemMeta().getDisplayName().contains("1")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 5) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (!item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }

                            if (coal >= 16) {
                                int check = 0;
                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COAL) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0) {
                                                player.getInventory().removeItem(new ItemStack(item3.getType(), 16));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 5);

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("10")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a1&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 1", ChatColor.GRAY + "- Fortune 1"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 1 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("2")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 10) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (!item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 32) {
                                int check = 0;
                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COAL) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0) {
                                                player.getInventory().removeItem(new ItemStack(item3.getType(), 32));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("1")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 10);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a2&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 2", ChatColor.GRAY + "- Fortune 2"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 2 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("3")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 25) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (!item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 64) {
                                int check = 0;
                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COAL) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0) {
                                                player.getInventory().removeItem(new ItemStack(item3.getType(), 64));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("2")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 25);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a3&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 3", ChatColor.GRAY + "- Fortune 3"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 3 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("4")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 50) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal+= item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 2) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 2) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 2 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("3")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 50);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a4&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 4", ChatColor.GRAY + "- Fortune 4"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 4, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 4 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("5")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 100) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 4) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 4) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 4 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("4")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 100);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a5&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 5", ChatColor.GRAY + "- Fortune 5"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 5 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("6")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 200) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 8) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 8) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 8 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("5")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 200);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a6&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 6", ChatColor.GRAY + "- Fortune 6"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 6, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 6, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 6 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("7")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 300) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 16) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 16) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 16 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("6")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 300);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a7&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 7", ChatColor.GRAY + "- Fortune 7"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 7, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 7 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("8")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 400) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 32) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 32) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 32 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("7")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 400);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a8&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 8", ChatColor.GRAY + "- Fortune 8"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 8, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 8, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 8 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("9")) {
                        double playerBalance = getPlayerBalance(player.getName());
                        if (playerBalance >= 500) {
                            for (ItemStack item : player.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.COAL) {
                                    if (item.getItemMeta().getDisplayName().contains("p")) {
                                        coal += item.getAmount();
                                    }
                                }
                            }
                            if (coal >= 64) {
                                int coalRemoved = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (coalRemoved >= 64) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item != null && item.getType() == Material.COAL) {
                                        ItemMeta meta = item.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item.getAmount(), 64 - coalRemoved);
                                            item.setAmount(item.getAmount() - amountToRemove);
                                            coalRemoved += amountToRemove;
                                        }
                                    }
                                }

                                for (ItemStack item3 : player.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.STONE_PICKAXE) {
                                        if (item3.getItemMeta().getDisplayName().contains("8")) {
                                            player.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(player.getName(), 500);

                                ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aStone Pickaxe &7[&a9&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 9", ChatColor.GRAY + "- Fortune 9"));
                                meta.setUnbreakable(true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                meta.addEnchant(Enchantment.DIG_SPEED, 9, true);
                                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 9, true);
                                item.setItemMeta(meta);
                                player.getInventory().addItem(item);
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 9 stone pickaxe!"));
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                player.closeInventory();
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough coal for this purchase!"));
                            }
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                        }
                    }
                }
            }
        }
    }

    public static Inventory createMinerShopInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(new MinerShopHolder(), 9 * 5, "Miner's Shop");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(" ");
        glass.setItemMeta(glassmeta);
        for (int i = 0; i < 22; i++) {
            inventory.setItem(i, glass);
        }
        for (int i = 23; i < 45; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitmeta = exit.getItemMeta();
        exitmeta.setDisplayName(ChatColor.RED + "Close Menu");
        exit.setItemMeta(exitmeta);
        inventory.setItem(36, exit);

        ItemStack prestige = new ItemStack(Material.REDSTONE);
        ItemMeta presitgemeta = prestige.getItemMeta();
        presitgemeta.setDisplayName(ChatColor.DARK_AQUA + "Prestige Upgrades");
        presitgemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Coming Soon!"));
        prestige.setItemMeta(presitgemeta);
        inventory.setItem(44, prestige);

        int check = 0;

        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == Material.WOODEN_PICKAXE) {
                check++;
                ItemStack pickaxe = new ItemStack(Material.WOODEN_PICKAXE);
                ItemMeta pickaxemeta = pickaxe.getItemMeta();
                if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a1&7]"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 2");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 0 " + ChatColor.GREEN + "-> 1", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Cobblestone", ChatColor.GRAY + "- $0"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a2"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 3");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 1 " + ChatColor.GREEN + "-> 2", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Cobblestone", ChatColor.GRAY + "- $5"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a3"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 4");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 2 " + ChatColor.GREEN + "-> 3", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Cobblestone", ChatColor.GRAY + "- $10"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a4"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 5");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 3 " + ChatColor.GREEN + "-> 4", ChatColor.GRAY + "- Fortune: 0 " + ChatColor.GREEN + "-> 1", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 2 Compressed Cobblestone", ChatColor.GRAY + "- $30"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a5"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 6");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 4 " + ChatColor.GREEN + "-> 5", ChatColor.GRAY + "- Fortune: 1 " + ChatColor.GREEN + "-> 2", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 4 Compressed Cobblestone", ChatColor.GRAY + "- $50"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a6"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 7");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 5 " + ChatColor.GREEN + "-> 6", ChatColor.GRAY + "- Fortune: 2 " + ChatColor.GREEN + "-> 3", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 8 Compressed Cobblestone", ChatColor.GRAY + "- $100"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a7"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 8");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 6 " + ChatColor.GREEN + "-> 7", ChatColor.GRAY + "- Fortune: 3 " + ChatColor.GREEN + "-> 4", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Compressed Cobblestone", ChatColor.GRAY + "- $150"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a8"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 9");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 7 " + ChatColor.GREEN + "-> 8", ChatColor.GRAY + "- Fortune: 4 " + ChatColor.GREEN + "-> 5", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Compressed Cobblestone", ChatColor.GRAY + "- $200"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a9"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 10 (Max Level)");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 8 " + ChatColor.GREEN + "-> 9", ChatColor.GRAY + "- Fortune: 5 " + ChatColor.GREEN + "-> 6", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Compressed Cobblestone", ChatColor.GRAY + "- $250"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a10&7]"))) {
                    ItemStack spickaxe = new ItemStack(Material.STONE_PICKAXE);
                    ItemMeta spickaxemeta = spickaxe.getItemMeta();
                    spickaxemeta.setDisplayName(ChatColor.GREEN + "Buy Stone Pickaxe Level 1");
                    spickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to buy!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 9 " + ChatColor.RED + "-> 1", ChatColor.GRAY + "- Fortune: 5 " + ChatColor.RED + "-> 1", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Coal", ChatColor.GRAY + "- $5"));
                    spickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    spickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                    spickaxe.setItemMeta(spickaxemeta);
                    inventory.setItem(22, spickaxe);
                } else {
                    pickaxe.setItemMeta(pickaxemeta);
                    inventory.setItem(22, pickaxe);
                }

            } else if (item != null && item.getType() == Material.STONE_PICKAXE) {
                check++;
                ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
                ItemMeta pickaxemeta = pickaxe.getItemMeta();
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a1"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 2");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 1 " + ChatColor.GREEN + "-> 2", ChatColor.GRAY + "- Fortune: 1 " + ChatColor.GREEN + "-> 2", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Coal", ChatColor.GRAY + "- $10"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a2"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 3");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 2 " + ChatColor.GREEN + "-> 3", ChatColor.GRAY + "- Fortune: 2 " + ChatColor.GREEN + "-> 3",  " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Coal", ChatColor.GRAY + "- $25"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a3"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 4");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 3 " + ChatColor.GREEN + "-> 4", ChatColor.GRAY + "- Fortune: 3 " + ChatColor.GREEN + "-> 4",  " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 2 Compressed Coal", ChatColor.GRAY + "- $50"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a4"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 5");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 4 " + ChatColor.GREEN + "-> 5", ChatColor.GRAY + "- Fortune: 4 " + ChatColor.GREEN + "-> 5",  ChatColor.GRAY + "- Fortune: 1 " + ChatColor.GREEN + "-> 2", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 4 Compressed Coal", ChatColor.GRAY + "- $100"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a5"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 6");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 5 " + ChatColor.GREEN + "-> 6", ChatColor.GRAY + "- Fortune: 5 " + ChatColor.GREEN + "-> 6",  ChatColor.GRAY + "- Fortune: 2 " + ChatColor.GREEN + "-> 3", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 8 Compressed Coal", ChatColor.GRAY + "- $200"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a6"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 7");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 6 " + ChatColor.GREEN + "-> 7", ChatColor.GRAY + "- Fortune: 6 " + ChatColor.GREEN + "-> 7",  ChatColor.GRAY + "- Fortune: 3 " + ChatColor.GREEN + "-> 4", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Compressed Coal", ChatColor.GRAY + "- $300"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a7"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 8");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 7 " + ChatColor.GREEN + "-> 8", ChatColor.GRAY + "- Fortune: 7 " + ChatColor.GREEN + "-> 8",  ChatColor.GRAY + "- Fortune: 4 " + ChatColor.GREEN + "-> 5", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Compressed Coal", ChatColor.GRAY + "- $400"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a8"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "Upgrade Current Pickaxe To Level 9");
                    pickaxemeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current pickaxe!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency: 8 " + ChatColor.GREEN + "-> 9", ChatColor.GRAY + "- Fortune: 8 " + ChatColor.GREEN + "-> 9",  ChatColor.GRAY + "- Fortune: 5 " + ChatColor.GREEN + "-> 6", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Compressed Coal", ChatColor.GRAY + "- $500"));
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a9"))) {
                    pickaxemeta.setDisplayName(ChatColor.GREEN + "You currently have the best pickaxe in the game!");
                    pickaxemeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                pickaxe.setItemMeta(pickaxemeta);
                inventory.setItem(22, pickaxe);
            }

            if (check == 0) {
                ItemStack nopick = new ItemStack(Material.BEDROCK);
                ItemMeta nopickmeta = nopick.getItemMeta();
                nopickmeta.setDisplayName(ChatColor.RED + "You have no pickaxes in your inventory!");
                nopickmeta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "If you have just started, use '/tutorial'!", ChatColor.GRAY + "But, if you have lost your pickaxe, please contact a staff member to get it replaced!"));
                nopick.setItemMeta(nopickmeta);
                inventory.setItem(22, nopick);
            }
        }

        return inventory;
    }

    public static class MinerShopHolder implements org.bukkit.inventory.InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
