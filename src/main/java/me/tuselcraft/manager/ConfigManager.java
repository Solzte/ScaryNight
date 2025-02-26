package me.tuselcraft.manager;

import me.tuselcraft.ScaryNight;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {
    private final ScaryNight plugin;
    private FileConfiguration config;

    public ConfigManager(ScaryNight plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public int getSoundInterval() {
        return config.getInt("sound-interval", 200);
    }

    public int getEffectInterval() {
        return config.getInt("effect-interval", 200);
    }

    public long getNightStartTime() {
        return config.getLong("night-start-time", 13000);
    }

    public long getNightEndTime() {
        return config.getLong("night-end-time", 0);
    }

    public boolean isDarkModeEnabled() {
        return config.getBoolean("dark-mode", true);
    }

    public boolean isScaryEffectsEnabled() {
        return config.getBoolean("enable-scary-effects", true);
    }

    public List<String> getSpecialMobs() {
        return config.getStringList("special-mobs");
    }
}
