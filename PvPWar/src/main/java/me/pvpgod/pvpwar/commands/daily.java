package me.pvpgod.pvpwar.commands;

import me.pvpgod.pvpwar.PvPWar;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class daily implements CommandExecutor {
    private final PvPWar plugin;
    public daily(PvPWar plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("daily")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (plugin.canClaimDailyReward(p)) {
                    plugin.claimDailyReward(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDaily rewards can only be claimed once every 24 hours!"));
                }
            }
        }
        return true;
    }
}
