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
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static me.pvpgod.pvpwar.board.board.getPlayerBalance;
import static me.pvpgod.pvpwar.board.board.takePlayerBalance;

public class armorsmith implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof ArmorSmithHolder) {
            event.setCancelled(true);

            Player p = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                if (clickedItem.getType() == Material.BARRIER) {
                    p.closeInventory();
                }

                if (clickedItem.getType() == Material.LEATHER_CHESTPLATE) {
                    p.closeInventory();
                    int cobblestone = 0;

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aBuy Leather Armor Set (Level 1)"))) {
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

                            p.getInventory().setHelmet(null);
                            p.getInventory().setChestplate(null);
                            p.getInventory().setLeggings(null);
                            p.getInventory().setBoots(null);



                            ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                            ItemMeta meta = leather.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 1)"));
                            meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            leather.setItemMeta(meta);

                            p.getInventory().setHelmet(leather);

                            leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                            meta = leather.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 1)"));
                            meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            leather.setItemMeta(meta);

                            p.getInventory().setChestplate(leather);

                            leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                            meta = leather.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 1)"));
                            meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            leather.setItemMeta(meta);

                            p.getInventory().setLeggings(leather);

                            leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                            meta = leather.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 1)"));
                            meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None"));
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            leather.setItemMeta(meta);

                            p.getInventory().setBoots(leather);

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 1 leather armor set!"));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough cobblestone for this purchase!"));
                        }
                    }

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 2"))) {
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

                                takePlayerBalance(p.getName(), 5);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 2)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 2)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 2)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 2)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 2 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 3"))) {
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

                                takePlayerBalance(p.getName(), 10);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 3)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 3)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 3)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 3)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 3 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 4"))) {
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

                                takePlayerBalance(p.getName(), 15);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 4)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 4)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 4)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 4)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 4 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 5"))) {
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

                                takePlayerBalance(p.getName(), 20);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 5)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 4", ChatColor.GRAY + "- Thorns 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                meta.addEnchant(Enchantment.THORNS, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 5)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 4", ChatColor.GRAY + "- Thorns 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                meta.addEnchant(Enchantment.THORNS, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 5)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 4", ChatColor.GRAY + "- Thorns 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                meta.addEnchant(Enchantment.THORNS, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 5)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 4", ChatColor.GRAY + "- Thorns 1"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                meta.addEnchant(Enchantment.THORNS, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 5 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 6"))) {
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

                                takePlayerBalance(p.getName(), 25);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 6)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 5", ChatColor.GRAY + "- Thorns 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                meta.addEnchant(Enchantment.THORNS, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 6)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 5", ChatColor.GRAY + "- Thorns 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                meta.addEnchant(Enchantment.THORNS, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 6)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 5", ChatColor.GRAY + "- Thorns 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                meta.addEnchant(Enchantment.THORNS, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 6)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 5", ChatColor.GRAY + "- Thorns 2"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                meta.addEnchant(Enchantment.THORNS, 2, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 6 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 7"))) {
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

                                takePlayerBalance(p.getName(), 50);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 7)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 6", ChatColor.GRAY + "- Thorns 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                                meta.addEnchant(Enchantment.THORNS, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 7)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 6", ChatColor.GRAY + "- Thorns 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                                meta.addEnchant(Enchantment.THORNS, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 7)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 6", ChatColor.GRAY + "- Thorns 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                                meta.addEnchant(Enchantment.THORNS, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 7)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 6", ChatColor.GRAY + "- Thorns 3"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                                meta.addEnchant(Enchantment.THORNS, 3, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 7 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 8"))) {
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

                                takePlayerBalance(p.getName(), 100);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 8)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 7", ChatColor.GRAY + "- Thorns 4"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
                                meta.addEnchant(Enchantment.THORNS, 4, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 8)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 7", ChatColor.GRAY + "- Thorns 4"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
                                meta.addEnchant(Enchantment.THORNS, 4, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 8)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 7", ChatColor.GRAY + "- Thorns 4"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
                                meta.addEnchant(Enchantment.THORNS, 4, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 8)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 7", ChatColor.GRAY + "- Thorns 4"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
                                meta.addEnchant(Enchantment.THORNS, 4, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 8 leather armor set!"));
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

                    if (clickedItem.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aUpgrade Current Leather Armor Set To Level 9"))) {
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

                                takePlayerBalance(p.getName(), 250);

                                p.getInventory().setHelmet(null);
                                p.getInventory().setChestplate(null);
                                p.getInventory().setLeggings(null);
                                p.getInventory().setBoots(null);


                                ItemStack leather = new ItemStack(Material.LEATHER_HELMET, 1);
                                ItemMeta meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 9)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 8", ChatColor.GRAY + "- Thorns 5"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 8, true);
                                meta.addEnchant(Enchantment.THORNS, 5, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setHelmet(leather);

                                leather = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 9)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 8", ChatColor.GRAY + "- Thorns 5"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 8, true);
                                meta.addEnchant(Enchantment.THORNS, 5, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setChestplate(leather);

                                leather = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 9)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 8", ChatColor.GRAY + "- Thorns 5"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 8, true);
                                meta.addEnchant(Enchantment.THORNS, 5, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setLeggings(leather);

                                leather = new ItemStack(Material.LEATHER_BOOTS, 1);
                                meta = leather.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLeather armor set (Level 9)"));
                                meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection 8", ChatColor.GRAY + "- Thorns 5"));
                                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 8, true);
                                meta.addEnchant(Enchantment.THORNS, 5, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                                leather.setItemMeta(meta);

                                p.getInventory().setBoots(leather);

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully brought the level 9 leather armor set!"));
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

    public static Inventory createArmorSmithInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(new ArmorSmithHolder(), 9 * 5, "Armorsmith");

        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        for (int i = 0; i < 45; i++) {
            if (i == 22) continue;
            inventory.setItem(i, glass);
        }

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrier_meta = barrier.getItemMeta();
        barrier_meta.setDisplayName(ChatColor.RED + "Close Menu");
        barrier.setItemMeta(barrier_meta);
        inventory.setItem(36, barrier);

        ItemStack presitge = new ItemStack(Material.REDSTONE);
        ItemMeta presitge_meta = presitge.getItemMeta();
        presitge_meta.setDisplayName(ChatColor.DARK_AQUA + "Prestige Upgrades");
        presitge_meta.setLore(Arrays.asList(" ", ChatColor.GRAY + "Coming Soon!"));
        presitge.setItemMeta(presitge_meta);
        inventory.setItem(44, presitge);

        ItemStack armor;
        ItemMeta armorMeta;

        ItemStack playerHelmet = p.getInventory().getHelmet();

        if (playerHelmet != null && playerHelmet.getType() == Material.LEATHER_HELMET) {
            int level = calculateArmorLevel(playerHelmet);
            if (level < 8) {
                int cost = (int) Math.pow(2, level) * 16;
                armor = createItem(Material.LEATHER_CHESTPLATE,
                        ChatColor.GREEN + "Upgrade Current Leather Armor Set To Level " + (level + 2),
                        Arrays.asList(" ", ChatColor.GRAY + "Click to upgrade your current armor set!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Protection: " + level + " -> " + (level + 1),
                                " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- " + (int) Math.pow(2, level) * 16 + " Compressed Cobblestone", ChatColor.GRAY + "- $" + cost));
            } else {
                armor = createItem(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "You have the maxed out armor set!", null);
            }
        } else {
            armor = createItem(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Buy Leather Armor Set (Level 1)",
                    Arrays.asList(" ", ChatColor.GRAY + "Click to purchase the leather armor set!", " ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- None ", " ", ChatColor.GRAY + "Costs: ", " ", ChatColor.GRAY + "- 16 Cobblestone", ChatColor.GRAY + "- $0"));
        }

        inventory.setItem(22, armor);

        return inventory;
    }

    private static ItemStack createItem(Material material, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        if (lore != null) {
            meta.setLore(lore);
        }

        meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private static int calculateArmorLevel(ItemStack helmet) {
        ItemMeta helmetMeta = helmet.getItemMeta();
        List<String> lore = helmetMeta.getLore();
        if (lore != null && !lore.isEmpty()) {
            String lastLine = lore.get(lore.size() - 1);
            if (lastLine.startsWith(ChatColor.GRAY + "- Thorns: ")) {
                return Integer.parseInt(lastLine.substring(lastLine.lastIndexOf(' ') + 1)) - 1;
            }
        }
        return 0;
    }

    public static class ArmorSmithHolder implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
