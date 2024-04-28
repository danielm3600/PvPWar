package me.pvpgod.pvpwar.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class discord implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
        if (cmd.getName().equalsIgnoreCase("discord")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                TextComponent message = new TextComponent(ChatColor.AQUA + "Click here to join the official PvPWar discord server!");
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/juV85SPQJZ"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{
                        new TextComponent(ChatColor.YELLOW + "Click to view this page!")
                }));

                p.spigot().sendMessage(message);
            }
        }
        return false;
    }
}
