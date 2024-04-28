package me.pvpgod.pvpwar.events;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

public class OnDamageOfPlayer implements Listener {

    private final PvPWar plugin;
    public OnDamageOfPlayer(PvPWar plugin) { this.plugin = plugin; }

    public static HashMap<Player, Boolean> isAttacked = new HashMap<>();
    public static HashMap<Player, Entity> attackerMap = new HashMap<>();

    public static void setAttacked(Player player, boolean value) {
        isAttacked.put(player, value);
    }

    public static boolean getAttacked(Player player) {
        return isAttacked.getOrDefault(player, false);
    }

    public static Entity getAttacker(Player player) {
        return attackerMap.get(player);
    }

    public static void setAttacker(Player player, Entity attacker) {
        attackerMap.put(player, attacker);
    }

    @EventHandler
    public void OnPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent) event;
            Entity attacker = entityEvent.getDamager();

            setAttacked(p, true);
            setAttacker(p, attacker);

            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    setAttacked(p, false);
                    setAttacker(p, null);
                }
            }, 20 * 10);
        }
    }
}
