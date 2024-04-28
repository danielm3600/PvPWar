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

public class swordsmith implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof SwordSmithHolder) {
            event.setCancelled(true);

            Player p = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                if (clickedItem.getType() == Material.BARRIER) {
                    p.closeInventory();
                }

                if (clickedItem.getType() == Material.WOODEN_SWORD) {
                    p.closeInventory();
                    int cobblestone = 0;
                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aBuy Level 1 Sword"))) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 16) {
                            int check = 0;
                            for (ItemStack item3 : p.getInventory().getContents()) {
                                if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                    if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                        if (check == 0) {
                                            p.getInventory().removeItem(new ItemStack(item3.getType(), 16));
                                            check = 1;
                                        }
                                    }
                                }
                            }

                            for (ItemStack item3 : p.getInventory().getContents()) {
                                if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                    if (item3.getItemMeta().getDisplayName().contains("1")) {
                                        p.getInventory().remove(item3);
                                    }
                                }
                            }

                            ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                            ItemMeta meta = item4.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a1&7]"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            item4.setItemMeta(meta);
                            p.getInventory().addItem(item4);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 1 wooden sword!"));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("2")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }
                        if (cobblestone >= 32) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 5) {
                                int check = 0;
                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0) {
                                                p.getInventory().removeItem(new ItemStack(item3.getType(), 32));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("1")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 5);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a2&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 1"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 2 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                            }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("3")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                if (!item.getItemMeta().getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 64) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 10) {
                                int check = 0;
                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.COBBLESTONE) {
                                        if (!item3.getItemMeta().getDisplayName().contains("p")) {
                                            if (check == 0) {
                                                p.getInventory().removeItem(new ItemStack(item3.getType(), 64));
                                                check = 1;
                                            }
                                        }
                                    }
                                }

                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("2")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 10);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a3&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 2"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 3 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("4")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 2) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 15) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 2) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 2 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("3")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 15);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a4&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 3"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 4 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            } else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("5")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 4) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 20) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 4) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 4 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("4")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 20);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a5&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 4", ChatColor.GRAY + "- Fire Aspect 1"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
                                meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 5 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("6")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 8) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 25) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 8) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 8 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("5")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 25);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a6&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 5", ChatColor.GRAY + "- Fire Aspect 2"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
                                meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 6 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().contains("7")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 16) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 50) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 16) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 16 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("6")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 50);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a7&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 6", ChatColor.GRAY + "- Fire Aspect 3"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
                                meta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 7 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("8")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 32) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 100) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 32) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 32 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("7")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 100);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a8&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 7", ChatColor.GRAY + "- Fire Aspect 4"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
                                meta.addEnchant(Enchantment.FIRE_ASPECT, 4, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 8 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                    if (clickedItem.getItemMeta().getDisplayName().contains("9")) {
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.COBBLESTONE) {
                                ItemMeta meta = item.getItemMeta();
                                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                    cobblestone += item.getAmount();
                                }
                            }
                        }

                        if (cobblestone >= 64) {
                            double playerBalance = getPlayerBalance(p.getName());
                            if (playerBalance >= 250) {
                                int cobblestoneRemoved = 0;

                                for (ItemStack item5 : p.getInventory().getContents()) {
                                    if (cobblestoneRemoved >= 64) {
                                        break; // Stop removal if two items have already been removed
                                    }

                                    if (item5 != null && item5.getType() == Material.COBBLESTONE) {
                                        ItemMeta meta = item5.getItemMeta();
                                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("p")) {
                                            int amountToRemove = Math.min(item5.getAmount(), 64 - cobblestoneRemoved);
                                            item5.setAmount(item5.getAmount() - amountToRemove);
                                            cobblestoneRemoved += amountToRemove;
                                        }
                                    }
                                }


                                for (ItemStack item3 : p.getInventory().getContents()) {
                                    if (item3 != null && item3.getType() == Material.WOODEN_SWORD) {
                                        if (item3.getItemMeta().getDisplayName().contains("8")) {
                                            p.getInventory().remove(item3);
                                        }
                                    }
                                }

                                takePlayerBalance(p.getName(), 250);

                                ItemStack item4 = new ItemStack(Material.WOODEN_SWORD, 1);
                                ItemMeta meta = item4.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a9&7]"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness 8", ChatColor.GRAY + "- Fire Aspect 5"));
                                meta.addEnchant(Enchantment.DAMAGE_ALL, 8, true);
                                meta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                item4.setItemMeta(meta);
                                p.getInventory().addItem(item4);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 9 wooden sword!"));
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough money for this purchase!"));
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough compressed cobblestone for this purchase!"));
                        }
                    }
                }
            }
        }
    }

    public static Inventory createSwordSmithInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(new SwordSmithHolder(), 9 * 5, "Swordsmith");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(" ");
        glass.setItemMeta(glassmeta);

        for (int i = 0; i < 45; i++) {
            if (i == 22) continue;
            inventory.setItem(i, glass);
        }

        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitmeta = exit.getItemMeta();
        exitmeta.setDisplayName(ChatColor.RED + "Close Menu");
        exit.setItemMeta(exitmeta);
        inventory.setItem(36, exit);

        ItemStack prestige = new ItemStack(Material.REDSTONE);
        ItemMeta prestige_meta = prestige.getItemMeta();
        prestige_meta.setDisplayName(ChatColor.DARK_AQUA + "Presitge Upgrades");
        prestige_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Coming Soon!"));
        prestige.setItemMeta(prestige_meta);
        inventory.setItem(44, prestige);

        int check = 0;

        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == Material.WOODEN_SWORD) {
                check++;
                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                ItemMeta sword_meta = sword.getItemMeta();
                if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aWooden Sword &7[&a1&7]"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 2");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 0 " + ChatColor.GREEN + "-> 1", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Cobblestone", ChatColor.GRAY + "- $5"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a2"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 3");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 2 " + ChatColor.GREEN + "-> 3", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Cobblestone", ChatColor.GRAY + "- $10"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a3"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 4");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 3 " + ChatColor.GREEN + "-> 4", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 2 Compressed Cobblestone", ChatColor.GRAY + "- $15"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a4"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 5");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 4 " + ChatColor.GREEN + "-> 5", ChatColor.GRAY + "- Fire Aspect: 0 " + ChatColor.GREEN + "-> 1", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 4 Compressed Cobblestone", ChatColor.GRAY + "- $20"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a5"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 6");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 5 " + ChatColor.GREEN + "-> 6", ChatColor.GRAY + "- Fire Aspect: 1 " + ChatColor.GREEN + "-> 2", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 8 Compressed Cobblestone", ChatColor.GRAY + "- $25"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a6"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 7");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 6 " + ChatColor.GREEN + "-> 7", ChatColor.GRAY + "- Fire Aspect: 2 " + ChatColor.GREEN + "-> 3", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Compressed Cobblestone", ChatColor.GRAY + "- $50"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a7"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 8");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 7 " + ChatColor.GREEN + "-> 8", ChatColor.GRAY + "- Fire Aspect: 3 " + ChatColor.GREEN + "-> 4", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 32 Compressed Cobblestone", ChatColor.GRAY + "- $100"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else if (item.getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', "&a8"))) {
                    sword_meta.setDisplayName(ChatColor.GREEN + "Upgrade Current Sword To Level 9");
                    sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Sharpness: 8 " + ChatColor.GREEN + "-> 9", ChatColor.GRAY + "- Fire Aspect: 4 " + ChatColor.GREEN + "-> 5", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 64 Compressed Cobblestone", ChatColor.GRAY + "- $250"));
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                else {
                    sword_meta.setDisplayName(ChatColor.GREEN + "You have the maxed out sword!");
                    sword_meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                }
                sword.setItemMeta(sword_meta);
                inventory.setItem(22, sword);
            }
            if (check == 0) {
                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                ItemMeta sword_meta = sword.getItemMeta();
                sword_meta.setDisplayName(ChatColor.GREEN + "Buy Level 1 Sword");
                sword_meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Click to buy the level 1 sword!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None ", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Cobblestone", ChatColor.GRAY + "- $0"));
                sword_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
                sword.setItemMeta(sword_meta);
                inventory.setItem(22, sword);
            }
        }
        return inventory;
    }

    public static class SwordSmithHolder implements org.bukkit.inventory.InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
