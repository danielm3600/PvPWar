package me.pvpgod.pvpwar.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnFoodChange implements Listener {
    @EventHandler
    public void onPlayerFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
