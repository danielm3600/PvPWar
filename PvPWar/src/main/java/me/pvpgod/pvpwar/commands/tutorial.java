package me.pvpgod.pvpwar.commands;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.pvpgod.pvpwar.PvPWar;
import net.ess3.api.MaxMoneyException;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class tutorial implements CommandExecutor {
    private final PvPWar plugin;
    public tutorial(PvPWar plugin) {
        this.plugin = plugin;
    }

    private static HashMap<Player, Boolean> session = new HashMap<>();

    public static void setSession(Player player, boolean value) {
        session.put(player, value);
    }

    public static boolean getSession(Player player) {
        return session.getOrDefault(player, false);
    }

    public static double addPlayerBalance(Player p, double amount) {
        Plugin essentials = Bukkit.getPluginManager().getPlugin("Essentials");
        if (essentials != null && essentials.isEnabled()) {
            try {
                Economy.add(p.getName(), 5);
            } catch (UserDoesNotExistException e) {
                Bukkit.getLogger().warning("User " + p.getName() + " not found in Essentials.");
            } catch (MaxMoneyException e) {
                throw new RuntimeException(e);
            } catch (NoLoanPermittedException e) {
                throw new RuntimeException(e);
            }
        }

        return 0.0;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
        if (cmd.getName().equalsIgnoreCase("tutorial")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!hasCompletedTutorial(p)) {
                    if (getSession(p)) {
                        p.sendMessage(ChatColor.RED + "You are currently in the tutorial!");
                        return true;
                    }
                    setSession(p, true);

                    if (plugin.getConfig().contains("default-location")) {
                        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("default-location");
                        if (configSection != null) {
                            Location defaultLocation = Location.deserialize(configSection.getValues(true));
                            p.teleport(defaultLocation);
                        }
                    }

                    p.sendMessage(" ");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aHey there! I'm the miner, I would like to show you around and help you get started on PvPWar!"));
                    p.sendMessage(" ");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            p.sendMessage(" ");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aHere is the beginner pickaxe! To start with, walk to the other side, and to your left mine 16 cobblestone!"));
                            p.sendMessage(" ");
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                            ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a1&7]"));
                            meta.setUnbreakable(true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                            item.setItemMeta(meta);
                            p.getInventory().addItem(item);
                        }
                    }, 20 * 5); // 20 (one second in ticks) * 5 (seconds to wait

                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            ItemStack[] inventoryContents = p.getInventory().getContents();
                            int cobblestoneCount = 0;

                            for (ItemStack item : inventoryContents) {
                                if (item != null && item.getType() == Material.COBBLESTONE) {
                                    cobblestoneCount += item.getAmount();
                                }
                            }
                            if (cobblestoneCount < 16) {
                                p.sendMessage(" ");
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &cYou failed to mine 16 cobblestone, use '/tutorial' to retry!"));
                                p.sendMessage(" ");
                                p.getInventory().clear();
                                if (plugin.getConfig().contains("default-location")) {
                                    ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("default-location");
                                    if (configSection != null) {
                                        Location defaultLocation = Location.deserialize(configSection.getValues(true));
                                        p.teleport(defaultLocation);
                                    }
                                }
                                setSession(p, false);
                            }
                            else {
                                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                    public void run() {
                                        p.sendMessage(" ");
                                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aRight-click on me to open the pickaxe shop, click 'Upgrade Current Pickaxe'! To upgrade your pickaxe to level 2!"));
                                        p.sendMessage(" ");
                                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                                        if (plugin.getConfig().contains("miner-location")) {
                                            ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("miner-location");
                                            if (configSection != null) {
                                                Location minerLocation = Location.deserialize(configSection.getValues(true));
                                                p.teleport(minerLocation);
                                            }
                                        }
                                    }
                                }, 20 * 5); // 20 (one second in ticks) * 5 (seconds to wait

                                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                    public void run() {
                                        int check = 0;
                                        for (ItemStack item : p.getInventory().getContents()) {
                                            if (item != null && item.getType() == Material.WOODEN_PICKAXE && item.getItemMeta().getDisplayName().contains("2")) {
                                                check++;
                                            }
                                        }
                                        if (check > 0) {
                                            p.sendMessage(" ");
                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aWant to get stronger? You can buy/upgrade your swords here!"));
                                            p.sendMessage(" ");
                                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                            if (plugin.getConfig().contains("swordsmith-location")) {
                                                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("swordsmith-location");
                                                if (configSection != null) {
                                                    Location swordsmithLocation = Location.deserialize(configSection.getValues(true));
                                                    p.teleport(swordsmithLocation);
                                                }
                                            }

                                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                public void run() {
                                                    p.sendMessage(" ");
                                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aNeed stronger armor? This is the place to upgrade your armor sets!"));
                                                    p.sendMessage(" ");
                                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                    if (plugin.getConfig().contains("armorsmith-location")) {
                                                        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("armorsmith-location");
                                                        if (configSection != null) {
                                                            Location armorsmithLocation = Location.deserialize(configSection.getValues(true));
                                                            p.teleport(armorsmithLocation);
                                                        }
                                                    }

                                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                        public void run() {
                                                            p.sendMessage(" ");
                                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aWant to teleport to the mines! Go through this portal to do this! (Or just simply type '/mines')"));
                                                            p.sendMessage(" ");
                                                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                            if (plugin.getConfig().contains("entrance-location")) {
                                                                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("entrance-location");
                                                                if (configSection != null) {
                                                                    Location entranceLocation = Location.deserialize(configSection.getValues(true));
                                                                    p.teleport(entranceLocation);
                                                                }
                                                            }

                                                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                public void run() {
                                                                    p.sendMessage(" ");
                                                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &ePvP is always enabled past this point, so be cautious!"));
                                                                    p.sendMessage(" ");
                                                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                    if (plugin.getConfig().contains("redline-location")) {
                                                                        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("redline-location");
                                                                        if (configSection != null) {
                                                                            Location redlineLocation = Location.deserialize(configSection.getValues(true));
                                                                            p.teleport(redlineLocation);
                                                                        }
                                                                    }

                                                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                        public void run() {
                                                                            p.sendMessage(" ");
                                                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aYou gain 5 XP and $5 from mining 100 blocks, or killing someone!"));
                                                                            p.sendMessage(" ");
                                                                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                public void run() {
                                                                                    p.sendMessage(" ");
                                                                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aUse '/daily' to claim your daily reward! Or just simply right-click the ender chest!"));
                                                                                    p.sendMessage(" ");
                                                                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                                    if (plugin.getConfig().contains("daily-location")) {
                                                                                        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("daily-location");
                                                                                        if (configSection != null) {
                                                                                            Location dailyLocation = Location.deserialize(configSection.getValues(true));
                                                                                            p.teleport(dailyLocation);
                                                                                        }
                                                                                    }

                                                                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                        public void run() {
                                                                                            p.sendMessage(" ");
                                                                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aAll your statistics will be shown on the scoreboard (on your right-hand side)!"));
                                                                                            p.sendMessage(" ");
                                                                                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                public void run() {
                                                                                                    p.sendMessage(" ");
                                                                                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aCompressing items is the only way to progress to higher leveled items, you can compress items by right-clicking on the furnace, and then selecting what item you want to compress!"));
                                                                                                    p.sendMessage(" ");
                                                                                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                                                    if (plugin.getConfig().contains("compressor-location")) {
                                                                                                        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("compressor-location");
                                                                                                        if (configSection != null) {
                                                                                                            Location compressorLocation = Location.deserialize(configSection.getValues(true));
                                                                                                            p.teleport(compressorLocation);
                                                                                                        }
                                                                                                    }

                                                                                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                        public void run() {
                                                                                                            p.sendMessage(" ");
                                                                                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &aThat's it! Good luck with your journey on PvPWar! If you have any questions or concerns, please notify a staff member! Please also report hackers with '/report'! (Tip: use '/spawn' to return back to the spawn area!"));
                                                                                                            p.sendMessage(" ");
                                                                                                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                                                                                                            setTutorialCompleted(p, true);

                                                                                                            if (plugin.getConfig().contains("spawn-location")) {
                                                                                                                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("spawn-location");
                                                                                                                if (configSection != null) {
                                                                                                                    Location spawnLocation = Location.deserialize(configSection.getValues(true));
                                                                                                                    p.teleport(spawnLocation);
                                                                                                                }
                                                                                                            }

                                                                                                            addPlayerBalance(p, 5.0);
                                                                                                        }
                                                                                                    }, 20 * 10);

                                                                                                    setSession(p, false);


                                                                                                }
                                                                                            }, 20 * 10);
                                                                                        }
                                                                                    }, 20 * 10);
                                                                                }
                                                                            }, 20 * 10);
                                                                        }
                                                                    }, 20 * 10);
                                                                }
                                                            }, 20 * 10);


                                                        }
                                                    }, 20 * 10);

                                                }
                                            }, 20 * 10);


                                        }
                                        else {
                                            p.sendMessage(" ");
                                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&eMINER&7] &cYou failed to upgrade your pickaxe to level 2, use '/tutorial' to retry!"));
                                            p.sendMessage(" ");
                                            p.getInventory().clear();
                                            if (plugin.getConfig().contains("default-location")) {
                                                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("default-location");
                                                if (configSection != null) {
                                                    Location defaultLocation = Location.deserialize(configSection.getValues(true));
                                                    p.teleport(defaultLocation);
                                                }
                                            }
                                            setSession(p, false);
                                        }
                                    }
                                }, 20 * 20);
                            }
                        }
                    }, 20 * 45); // 20 (one second in ticks) * 5 (seconds to wait

                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have already completed the tutorial!"));
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("testtutorial")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    if (!hasCompletedTutorial(p)) {
                        p.sendMessage("Not completed! so setting to true");
                        setTutorialCompleted(p, true);
                        setSession(p, false);

                        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a10&7]"));
                        meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 9", ChatColor.GRAY + "- Fortune 6"));
                        meta.setUnbreakable(true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                        meta.addEnchant(Enchantment.DIG_SPEED, 9, true);
                        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 6, true);
                        item.setItemMeta(meta);
                        p.getInventory().addItem(item);
                    }
                    else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Completed! so setting to false"));
                        setTutorialCompleted(p, false);
                        setSession(p, false);

                        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aWooden Pickaxe &7[&a10&7]"));
                        meta.setLore(java.util.Arrays.asList(" ", ChatColor.GRAY + "Enchants: ", " ", ChatColor.GRAY + "- Efficiency 9", ChatColor.GRAY + "- Fortune 6"));
                        meta.setUnbreakable(true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                        meta.addEnchant(Enchantment.DIG_SPEED, 9, true);
                        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 6, true);
                        item.setItemMeta(meta);
                        p.getInventory().addItem(item);
                    }
                }
            }
        }
        return true;
    }

    public static boolean hasCompletedTutorial(Player player) {
        FileConfiguration config = PvPWar.getPlugin().getConfig();
        return config.getBoolean("tutorial." + player.getUniqueId(), false);
    }

    // Helper method to set the tutorial completion status
    public static void setTutorialCompleted(Player player, boolean completed) {
        FileConfiguration config = PvPWar.getPlugin().getConfig();
        config.set("tutorial." + player.getUniqueId(), completed);
        PvPWar.getPlugin().saveConfig();
    }
}
