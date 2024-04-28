package me.pvpgod.pvpwar.events;

import me.pvpgod.pvpwar.PvPWar;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

import static me.pvpgod.pvpwar.commands.tutorial.addPlayerBalance;

public class OnDeathOfPlayer implements Listener {
    private final PvPWar plugin;

    public OnDeathOfPlayer(PvPWar plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Player killer = p.getKiller();
        if (killer != null && killer instanceof Player) {
            plugin.addKill(killer, 1);
            Random rand = new Random();
            int randomNum = rand.nextInt((5 - 1) + 1) + 1;
            if (randomNum == 1) {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&c got slained by &6" + killer.getName() + "&c!"));
            }
            else if (randomNum == 2) {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&c got demolished by &6" + killer.getName() + "&c!"));
            }
            else if (randomNum == 3) {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&c got rekt by &6" + killer.getName() + "&c!"));
            }
            else if (randomNum == 4) {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&c got killed by &6" + killer.getName() + "&c!"));
            }
            else if (randomNum == 5) {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&c got murdered by &6" + killer.getName() + "&c!"));
            }

            killer.giveExp(5);
            addPlayerBalance(killer, 5.0);
            killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou gained 3 XP for killing &7" + p.getName() + "&a!"));

        }
        else {
            event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&6" + p.getName() + " died by himself, somehow..."));
        }
        plugin.addDeath(p, 1);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (plugin.getConfig().contains("spawn-location")) {
                    ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("spawn-location");
                    if (configSection != null) {
                        Location spawnLocation = Location.deserialize(configSection.getValues(true));
                        p.teleport(spawnLocation);
                    }
                }
            }
        }, 20);
    }
}
