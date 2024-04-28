package me.pvpgod.pvpwar;

import me.pvpgod.pvpwar.board.board;
import me.pvpgod.pvpwar.commands.*;
import me.pvpgod.pvpwar.events.*;
import me.pvpgod.pvpwar.shops.armorsmith;
import me.pvpgod.pvpwar.shops.compressor;
import me.pvpgod.pvpwar.shops.miner;
import me.pvpgod.pvpwar.shops.swordsmith;
import net.luckperms.api.LuckPerms;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static me.pvpgod.pvpwar.commands.tutorial.*;

public final class PvPWar extends JavaPlugin implements Listener {
    private BukkitTask task;
    private HashMap<UUID, Long> lastClaimed = new HashMap<>();
    private FileConfiguration config;
    public HashMap<UUID, Integer> broken_blocks = new HashMap<>();
    public HashMap<UUID, Integer> player_kills = new HashMap<>();
    public HashMap<UUID, Integer> player_deaths = new HashMap<>();
    public HashMap<UUID, Integer> player_blocks100 = new HashMap<>();
    private static PvPWar instance;
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("PvPWar [" + getDescription().getVersion() + "] has started!");

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }

        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        config = getConfig();

        loadPlayerData();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new OnPlayerCraft(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemDrop(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerCommandPreprocess(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnDeathOfPlayer(this), this);
        getServer().getPluginManager().registerEvents(new OnFoodChange(), this);
        getServer().getPluginManager().registerEvents(new OnWalkOfBlock(this), this);
        getServer().getPluginManager().registerEvents(new OnDamageOfPlayer(this), this);
        getServer().getPluginManager().registerEvents(new OnRightClickOfEntity(), this);
        getServer().getPluginManager().registerEvents(new miner(), this);
        getServer().getPluginManager().registerEvents(new compressor(), this);
        getServer().getPluginManager().registerEvents(new swordsmith(), this);
        getServer().getPluginManager().registerEvents(new armorsmith(), this);
        getServer().getPluginManager().registerEvents(new ArmorUnEquiptEvent(), this);

        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("god").setExecutor(new God());
        getCommand("drop").setExecutor(new drop());
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("reportbug").setExecutor(new reportbug(this));
        getCommand("reportbugs").setExecutor(new reportbugs(this));
        getCommand("deletebugreport").setExecutor(new deletebugreport(this));
        getCommand("report").setExecutor(new report(this));
        getCommand("reports").setExecutor(new reports(this));
        getCommand("help").setExecutor(new Help());
        getCommand("me").setExecutor(new DisabledCommands());
        getCommand("rules").setExecutor(new Rules());
        getCommand("invsee").setExecutor(new Invsee());
        getCommand("level").setExecutor(new level());
        getCommand("daily").setExecutor(new daily(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
        getCommand("setminer").setExecutor(new SetSpawn(this));
        getCommand("setredline").setExecutor(new SetSpawn(this));
        getCommand("setarmorsmith").setExecutor(new SetSpawn(this));
        getCommand("setswordsmith").setExecutor(new SetSpawn(this));
        getCommand("setcompressor").setExecutor(new SetSpawn(this));
        getCommand("setdaily").setExecutor(new SetSpawn(this));
        getCommand("setentrance").setExecutor(new SetSpawn(this));
        getCommand("setdefaultspawn").setExecutor(new SetSpawn(this));
        getCommand("setmines").setExecutor(new SetSpawn(this));
        getCommand("spawn").setExecutor(new spawn(this));
        getCommand("mines").setExecutor(new spawn(this));
        getCommand("tutorial").setExecutor(new tutorial(this));
        getCommand("testtutorial").setExecutor(new tutorial(this));
        getCommand("discord").setExecutor(new discord());

        task = getServer().getScheduler().runTaskTimer(this, board.getInstance(), 0, 100);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("PvPWar [" + getDescription().getVersion() + "] has shut down!");
        savePlayerData();
    }

    @EventHandler
    public void OnInteractWithBlock(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction().name().contains("RIGHT") && clickedBlock != null
                && clickedBlock.getType() == Material.ENDER_CHEST) {
            event.setCancelled(true);
            p.performCommand("daily");
        }
    }

    public boolean canClaimDailyReward(Player player) {
        UUID playerId = player.getUniqueId();
        long lastClaimTime = lastClaimed.getOrDefault(playerId, 0L);
        long currentTime = System.currentTimeMillis();
        long timeSinceLastClaim = currentTime - lastClaimTime;
        long timeUntilNextClaim = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

        return timeSinceLastClaim >= timeUntilNextClaim;
    }

    public void claimDailyReward(Player player) {
        UUID playerId = player.getUniqueId();
        lastClaimed.put(playerId, System.currentTimeMillis());
        Random rand = new Random();
        Random rand2 = new Random();
        int xp = rand.nextInt((20 - 5) + 1) + 1;
        player.giveExp(xp);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou successfully claimed your daily reward and got " + xp + " XP!"));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }

    private void loadPlayerData() {
        if (getConfig().contains("broken_blocks")) {
            for (String uuidString : getConfig().getConfigurationSection("broken_blocks").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidString);
                int blocks = getConfig().getInt("broken_blocks." + uuidString + ".count");
                broken_blocks.put(uuid, blocks);
            }
        }
        if (getConfig().contains("player_kills")) {
            for (String uuidString : getConfig().getConfigurationSection("player_kills").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidString);
                int kills = getConfig().getInt("player_kills." + uuidString + ".count");
                player_kills.put(uuid, kills);
            }
        }
        if (getConfig().contains("player_deaths")) {
            for (String uuidString : getConfig().getConfigurationSection("player_deaths").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidString);
                int deaths = getConfig().getInt("player_deaths." + uuidString + ".count");
                player_deaths.put(uuid, deaths);
            }
        }
        if (getConfig().contains("player_blocks100")) {
            for (String uuidString : getConfig().getConfigurationSection("player_blocks100").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidString);
                int blocks100 = getConfig().getInt("player_blocks100." + uuidString + ".count");
                player_deaths.put(uuid, blocks100);
            }
        }
        for (String playerId : config.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(playerId);
                long lastClaimTime = config.getLong(playerId);
                lastClaimed.put(uuid, lastClaimTime);
            } catch (IllegalArgumentException e) { }
        }
    }

    private void savePlayerData() {
        for (Map.Entry<UUID, Integer> entry : broken_blocks.entrySet()) {
            UUID uuid = entry.getKey();
            int blocks = entry.getValue();
            getConfig().set("broken_blocks." + uuid.toString() + ".count", blocks);

            // OPTIONAL: Save player names as well
            getConfig().set("broken_blocks." + uuid.toString() + ".name", Bukkit.getOfflinePlayer(uuid).getName());
        }
        for (Map.Entry<UUID, Integer> entry : player_kills.entrySet()) {
            UUID uuid = entry.getKey();
            int kills = entry.getValue();
            getConfig().set("player_kills." + uuid.toString() + ".count", kills);

            // OPTIONAL: Save player names as well
            getConfig().set("player_kills." + uuid.toString() + ".name", Bukkit.getOfflinePlayer(uuid).getName());
        }
        for (Map.Entry<UUID, Integer> entry : player_deaths.entrySet()) {
            UUID uuid = entry.getKey();
            int deaths = entry.getValue();
            getConfig().set("player_deaths." + uuid.toString() + ".count", deaths);

            // OPTIONAL: Save player names as well
            getConfig().set("player_deaths." + uuid.toString() + ".name", Bukkit.getOfflinePlayer(uuid).getName());
        }
        for (Map.Entry<UUID, Integer> entry : player_blocks100.entrySet()) {
            UUID uuid = entry.getKey();
            int blocks100 = entry.getValue();
            getConfig().set("player_blocks100." + uuid.toString() + ".count", blocks100);

            // OPTIONAL: Save player names as well
            getConfig().set("player_blocks100." + uuid.toString() + ".name", Bukkit.getOfflinePlayer(uuid).getName());
        }
        for (UUID playerId : lastClaimed.keySet()) {
            config.set(playerId.toString(), lastClaimed.get(playerId));
        }
        saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage(null);
        if (tutorial.hasCompletedTutorial(p)) {
            if (getConfig().contains("spawn-location")) {
                ConfigurationSection configSection = getConfig().getConfigurationSection("spawn-location");
                if (configSection != null) {
                    Location spawnLocation = Location.deserialize(configSection.getValues(true));
                    p.teleport(spawnLocation);
                }
            }
        } else {
            if (getConfig().contains("default-location")) {
                ConfigurationSection configSection = getConfig().getConfigurationSection("default-location");
                if (configSection != null) {
                    Location defaultLocation = Location.deserialize(configSection.getValues(true));
                    p.teleport(defaultLocation);
                }
            }
        }
        if (getConfig().contains("broken_blocks." + p.getUniqueId().toString())) {
            int broken_blocks = getConfig().getInt("broken_blocks." + p.getUniqueId().toString() + ".count");
            this.broken_blocks.put(p.getUniqueId(), broken_blocks);
        }
        if (getConfig().contains("player_kills." + p.getUniqueId().toString())) {
            int player_kills = getConfig().getInt("player_kills." + p.getUniqueId().toString() + ".count");
            this.player_kills.put(p.getUniqueId(), player_kills);
        }
        if (getConfig().contains("player_deaths." + p.getUniqueId().toString())) {
            int player_deaths = getConfig().getInt("player_deaths." + p.getUniqueId().toString() + ".count");
            this.player_deaths.put(p.getUniqueId(), player_deaths);
        }
        if (getConfig().contains("player_blocks100." + p.getUniqueId().toString())) {
            int player_blocks100 = getConfig().getInt("player_blocks100." + p.getUniqueId().toString() + ".count");
            this.player_blocks100.put(p.getUniqueId(), player_blocks100);
        }

        if (!p.hasPlayedBefore()) {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lWELCOME TO &3PVPWAR&b!"));
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bType &7/'tutorial' &bto get started!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAsk a staff member if you have any questions or concerns!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bGood luck with your journey!"));
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cReport hackers with '/report'!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eReport any bugs with '/reportbug'!"));
            p.sendMessage(" ");
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        }

        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&b+&7] &b" + p.getName()));
    }

    public static PvPWar getPlugin() {
        return instance;
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&7[&b-&7] &b" + p.getName()));
        if (this.broken_blocks.containsKey(p.getUniqueId())) {
            int blocks = this.broken_blocks.get(p.getUniqueId());
            getConfig().set("broken_blocks." + p.getUniqueId().toString() + ".count", blocks);

            //OPTIONAL
            getConfig().set("broken_blocks." + p.getUniqueId().toString() + ".name", p.getName());
            saveConfig();
        }
        if (this.player_kills.containsKey(p.getUniqueId())) {
            int kills = this.player_kills.get(p.getUniqueId());
            getConfig().set("player_kills." + p.getUniqueId().toString() + ".count", kills);

            //OPTIONAL
            getConfig().set("player_kills." + p.getUniqueId().toString() + ".name", p.getName());
            saveConfig();
        }
        if (this.player_deaths.containsKey(p.getUniqueId())) {
            int deaths = this.player_deaths.get(p.getUniqueId());
            getConfig().set("player_deaths." + p.getUniqueId().toString() + ".count", deaths);

            //OPTIONAL
            getConfig().set("player_deaths." + p.getUniqueId().toString() + ".name", p.getName());
            saveConfig();
        }
        if (this.player_blocks100.containsKey(p.getUniqueId())) {
            int blocks100 = this.player_blocks100.get(p.getUniqueId());
            getConfig().set("player_blocks100." + p.getUniqueId().toString() + ".count", blocks100);

            //OPTIONAL
            getConfig().set("player_blocks100." + p.getUniqueId().toString() + ".name", p.getName());
            saveConfig();
        }

        if (!tutorial.hasCompletedTutorial(p)) {
            p.getInventory().clear();
            getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    tutorial.setTutorialCompleted(p, false);
                }
            }, 20 * 60);
        }

        if (getSession(p)) {
            setSession(p, false);
        }
    }

    public void addBrokenBlock(Player p, int blocksToAdd) {
        if (!this.broken_blocks.containsKey(p.getUniqueId())) {
            this.broken_blocks.put(p.getUniqueId(), blocksToAdd);
        } else {
            this.broken_blocks.put(p.getUniqueId(), Integer.valueOf(this.broken_blocks.get(p.getUniqueId())).intValue() + 1);
        }
    }

    public void addBrokenBlock100(Player p, int hundreadblocksToAdd) {
        if (!this.player_blocks100.containsKey(p.getUniqueId())) {
            this.player_blocks100.put(p.getUniqueId(), hundreadblocksToAdd);
        } else {
            this.player_blocks100.put(p.getUniqueId(), Integer.valueOf(this.player_blocks100.get(p.getUniqueId())).intValue() + 1);
        }
    }

    public void resetBrokenBlock100(Player p) {
        this.player_blocks100.put(p.getUniqueId(), 0);
    }

    public void addKill(Player p, int killstoAdd) {
        if (!this.player_kills.containsKey(p.getUniqueId())) {
            this.player_kills.put(p.getUniqueId(), killstoAdd);
        } else {
            this.player_kills.put(p.getUniqueId(), this.player_kills.get(p.getUniqueId()) + killstoAdd);
        }
    }

    public void addDeath(Player p, int deathstoAdd) {
        if (!this.player_deaths.containsKey(p.getUniqueId())) {
            this.player_deaths.put(p.getUniqueId(), deathstoAdd);
        } else {
            this.player_deaths.put(p.getUniqueId(), this.player_deaths.get(p.getUniqueId()) + deathstoAdd);
        }
    }

    private int getTotalItemCount(Player player) {
        int totalCount = 0;
        Inventory inventory = player.getInventory();
        ItemStack[] contents = inventory.getContents();

        for (ItemStack item : contents) {
            if (item != null && item.getType() != Material.AIR) {
                totalCount += item.getAmount();
            }
        }

        return totalCount;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        final Block block = event.getBlock();
        final Material initialType = block.getType();

        event.setDropItems(false);
        event.setExpToDrop(0);

        Player p = event.getPlayer();

        int old_count = getTotalItemCount(p);

        getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                if (block.getType() == Material.AIR) {
                    if (p.getGameMode() != GameMode.CREATIVE) {
                        addBrokenBlock(p, 1);
                        addBrokenBlock100(p, 1);

                        int check100 = getBrokenBlocks100(p);
                        if (check100 == 100) {
                            resetBrokenBlock100(p);
                            p.giveExp(5);
                            addPlayerBalance(p, 5);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou got 5 XP and $5 from mining 100 blocks!"));
                        }

                        Material material = initialType;
                        int multiplier = 1;

                        switch (initialType) {
                            case STONE:
                                material = Material.COBBLESTONE;
                                break;
                            case COAL_ORE:
                                material = Material.COAL;
                                break;
                            case COAL_BLOCK:
                                material = Material.COAL;
                                multiplier = 9;
                                break;
                            case IRON_ORE:
                                material = Material.IRON_INGOT;
                                break;
                            case IRON_BLOCK:
                                material = Material.IRON_INGOT;
                                multiplier = 9;
                                break;
                            case GOLD_ORE:
                                material = Material.GOLD_INGOT;
                                break;
                            case GOLD_BLOCK:
                                material = Material.GOLD_INGOT;
                                multiplier = 9;
                                break;
                            case EMERALD_ORE:
                                material = Material.EMERALD;
                                break;
                            case EMERALD_BLOCK:
                                material = Material.EMERALD;
                                multiplier = 9;
                                break;
                            case DIAMOND_ORE:
                                material = Material.DIAMOND;
                                break;
                            case DIAMOND_BLOCK:
                                material = Material.DIAMOND;
                                multiplier = 9;
                                break;
                        }

                        if (p.getItemInHand() != null) {
                            if (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) > 0) {
                                int itemCount = (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) * multiplier;
                                p.getInventory().addItem(new ItemStack(material, itemCount));
                            }
                            else {
                                int itemCount = multiplier;
                                p.getInventory().addItem(new ItemStack(material, itemCount));
                            }
                        }
                        else {
                            int itemCount = 1;
                            p.getInventory().addItem(new ItemStack(material, 1));
                        }

                        int new_count = getTotalItemCount(p);

                        if (old_count == new_count) {
                            p.sendMessage(ChatColor.YELLOW + "Your inventory is full!");
                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                        }
                    }
                }
            }
        }, 5);
    }

    public int getBrokenBlocks(Player p) {
        if(this.broken_blocks.containsKey(p.getUniqueId())) {
            return this.broken_blocks.get(p.getUniqueId());
        }
        return 0;
    }

    public int getBrokenBlocks100(Player p) {
        if(this.player_blocks100.containsKey(p.getUniqueId())) {
            return this.player_blocks100.get(p.getUniqueId());
        }
        return 0;
    }

    public int getPlayerKills(Player p) {
        if(this.player_kills.containsKey(p.getUniqueId())) {
            return this.player_kills.get(p.getUniqueId());
        }
        return 0;
    }

    public int getPlayerDeaths(Player p) {
        if(this.player_deaths.containsKey(p.getUniqueId())) {
            return this.player_deaths.get(p.getUniqueId());
        }
        return 0;
    }



}
