package me.pvpgod.pvpwar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
public class Rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rules")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                TextComponent message = new TextComponent(ChatColor.AQUA + "Click here to visit the PvPWar rules page!");
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/e/2PACX-1vTtnIYuxrWIH3CguhZXeY16RTcJPpWRHwTkEQFgqcIM8Jj2CjmzIDMRWc22q0WKyL79_ikwsW1qM7xd/pub"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{
                        new TextComponent(ChatColor.YELLOW + "Click to view this page!")
                }));

                p.spigot().sendMessage(message);
            }
        }
        return true;
    }
}
