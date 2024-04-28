package me.pvpgod.pvpwar.board;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.pvpgod.pvpwar.PvPWar;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;

public class board implements Runnable {
    private static final board instance = new board();
    private final PvPWar plugin;
    private board() {
        this.plugin = PvPWar.getPlugin(PvPWar.class);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard().getObjectives().size() > 1) {
                updateScoreboard(player);
            } else {
                createNewScoreboard(player);
            }
        }
    }
    public static double getPlayerBalance(String playerName) {
        Plugin essentials = Bukkit.getPluginManager().getPlugin("Essentials");
        if (essentials != null && essentials.isEnabled()) {
            try {
                return Economy.getMoneyExact(playerName).doubleValue();
            } catch (UserDoesNotExistException e) {
                Bukkit.getLogger().warning("User " + playerName + " not found in Essentials.");
            }
        }
        return 0.0;
    }
    public static double takePlayerBalance(String playerName, double balance) {
        Plugin essentials = Bukkit.getPluginManager().getPlugin("Essentials");
        if (essentials != null && essentials.isEnabled()) {
            try {
                Economy.subtract(playerName, balance);
            } catch (UserDoesNotExistException | NoLoanPermittedException | MaxMoneyException e) {
                Bukkit.getLogger().warning("User " + playerName + " not found in Essentials.");
            }

        }
        return 0.0;
    }
    private void createNewScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.AQUA + "PvPWar " + ChatColor.DARK_AQUA + "(Beta)");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int totalPlayers = Bukkit.getOfflinePlayers().length + Bukkit.getOnlinePlayers().size();
        int brokenBlocks = plugin.getBrokenBlocks(p);
        int kills = plugin.getPlayerKills(p);
        int deaths = plugin.getPlayerDeaths(p);
        double kd = (double) kills / deaths;

        if (deaths == 0) {
            kd = 0;
        }

        String convertedkd = String.format("%.3f", kd);
        String playerName = p.getName(); // Replace with the actual player name
        double playerBalance = getPlayerBalance(playerName);

        objective.getScore(ChatColor.BLACK + " ").setScore(11);
        objective.getScore(ChatColor.DARK_AQUA + "Level: " + ChatColor.AQUA + p.getLevel()).setScore(10);
        objective.getScore(ChatColor.DARK_AQUA + "Balance: " + ChatColor.AQUA + "$" + playerBalance).setScore(9);
        objective.getScore(ChatColor.DARK_AQUA + "Mined Blocks: " + ChatColor.AQUA + brokenBlocks).setScore(8);
        objective.getScore(ChatColor.DARK_AQUA + "Kills: " + ChatColor.AQUA + kills).setScore(7);
        objective.getScore(ChatColor.DARK_AQUA + "Deaths: " + ChatColor.AQUA + deaths).setScore(6);
        objective.getScore(ChatColor.DARK_AQUA + "K/D: " + ChatColor.AQUA + convertedkd).setScore(5);
        objective.getScore(ChatColor.YELLOW + " ").setScore(4);
        Score onlinePlayers = objective.getScore(ChatColor.DARK_AQUA + "Online Players: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
        onlinePlayers.setScore(3);
        objective.getScore(ChatColor.DARK_AQUA + "Unique Joins: " + ChatColor.AQUA + totalPlayers).setScore(2);
        objective.getScore(ChatColor.WHITE + " ").setScore(1);
        objective.getScore(ChatColor.GOLD + "PvPWar.shockbyte.games").setScore(0);

        p.setScoreboard(scoreboard);
    }
    private void updateScoreboard(Player p) {
        Scoreboard scoreboard = p.getScoreboard();
        p.setScoreboard(scoreboard);
    }
    public static board getInstance() {
        return instance;
    }
}
