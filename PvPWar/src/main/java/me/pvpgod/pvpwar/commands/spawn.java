package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    private final PvPWar plugin;

    public spawn(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully teleported you to spawn!"));
            if (plugin.getConfig().contains("spawn-location")) {
                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("spawn-location");
                if (configSection != null) {
                    Location spawnLocation = Location.deserialize(configSection.getValues(true));
                    p.teleport(spawnLocation);
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("mines")) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully teleported you to the mines!"));
            if (plugin.getConfig().contains("mines-location")) {
                ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("mines-location");
                if (configSection != null) {
                    Location minesLocation = Location.deserialize(configSection.getValues(true));
                    p.teleport(minesLocation);
                }
            }
        }
        return true;
    }
}
