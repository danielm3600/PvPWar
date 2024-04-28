package me.pvpgod.pvpwar.events;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.pvpgod.pvpwar.commands.tutorial.addPlayerBalance;
import static me.pvpgod.pvpwar.events.OnDamageOfPlayer.attackerMap;

public class OnWalkOfBlock implements Listener {
    private final PvPWar plugin;
    public OnWalkOfBlock(PvPWar plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalkOfBlock(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK) {
            if (event.getTo().getBlockY() == 193) {
                if (plugin.getConfig().contains("mines-location")) {
                    ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("mines-location");
                    if (configSection != null) {
                        Location minesLocation = Location.deserialize(configSection.getValues(true));
                        p.teleport(minesLocation);
                    }
                }
            } else if (event.getTo().getBlockY() == 198) {
                if (plugin.getConfig().contains("spawn-location")) {
                    ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("spawn-location");
                    if (configSection != null) {
                        Location spawnLocation = Location.deserialize(configSection.getValues(true));
                        p.teleport(spawnLocation);
                    }
                }
            }
        } else if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.LIGHT_BLUE_STAINED_GLASS) {
            if (event.getTo().getBlockY() == 156) {
                if (plugin.getConfig().contains("mines-location")) {
                    ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("mines-location");
                    if (configSection != null) {
                        Location minesLocation = Location.deserialize(configSection.getValues(true));
                        p.teleport(minesLocation);
                    }
                }
                p.setHealth(20);

                if (OnDamageOfPlayer.getAttacked(p) && OnDamageOfPlayer.getAttacker(p) instanceof Player) {
                    Player attacker = (Player) OnDamageOfPlayer.getAttacker(p);
                    addPlayerBalance(attacker, 5.0);
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7" + p.getName() + "&6 got knocked into the void by &c" + attacker.getName() + "&6!"));
                    attacker.giveExp(5);
                    attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou knocked " + p.getName() + " into the void and gained 5 XP and $5!"));
                    plugin.addKill(attacker, 1);
                    plugin.addDeath(p, 1);
                } else {
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6" + p.getName() + " fell in the void!"));
                    plugin.addDeath(p, 1);
                }
            }
        }
    }
}
