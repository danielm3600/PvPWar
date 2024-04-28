package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    private final PvPWar plugin;

    public SetSpawn(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location spawnLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("spawn-location", spawnLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set spawn to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setminer")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location minerLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("miner-location", minerLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set miner location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setredline")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location redlineLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("redline-location", redlineLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set redline location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setarmorsmith")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location armorsmithLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("armorsmith-location", armorsmithLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set armorsmith location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setswordsmith")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location swordsmithLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("swordsmith-location", swordsmithLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set swordsmith location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setcompressor")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location compressorLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("compressor-location", compressorLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set compressor location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setdaily")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location dailyLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("daily-location", dailyLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set daily location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setentrance")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location entranceLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("entrance-location", entranceLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set entrance location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setmines")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location minesLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("mines-location", minesLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set mines location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("setdefaultspawn")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("*")) {
                    Location defaultLocation = ((sender instanceof org.bukkit.entity.Player) ?
                            ((org.bukkit.entity.Player) sender).getLocation() :
                            Bukkit.getWorlds().get(0).getSpawnLocation());
                    plugin.getConfig().set("default-location", defaultLocation.serialize());
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully set default location to your current location!"));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission for this command!"));
                    return true;
                }
            }
        }
        return true;
    }
}
