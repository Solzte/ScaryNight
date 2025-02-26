package me.tuselcraft;

import me.tuselcraft.commands.ScaryNightCommand;
import me.tuselcraft.manager.ConfigManager;
import me.tuselcraft.manager.EffectManager;
import me.tuselcraft.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ScaryNight extends JavaPlugin {

    private EffectManager effectManager;
    private SoundManager soundManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        getLogger().info("\033[38;5;214m   _____       _     _       ");
        getLogger().info("\033[38;5;214m  / ____|     | |   | |      ");
        getLogger().info("\033[38;5;214m | (___   ___ | |___| |_ ___ ");
        getLogger().info("\033[38;5;214m  \\___ \\ / _ \\| |_  / __/ _ \\");
        getLogger().info("\033[38;5;214m  ____) | (_) | |/ /| ||  __/");
        getLogger().info("\033[38;5;214m |_____/ \\___/|_/___|\\__\\___|");
        
        effectManager = new EffectManager(this);
        soundManager = new SoundManager(this);
        configManager = new ConfigManager(this);

        getCommand("scarynight").setExecutor(new ScaryNightCommand());

        scheduleEffectsAndSounds();
    }

    @Override
    public void onDisable() {
        getLogger().info("\033[38;5;214m   _____       _     _       ");
        getLogger().info("\033[38;5;214m  / ____|     | |   | |      ");
        getLogger().info("\033[38;5;214m | (___   ___ | |___| |_ ___ ");
        getLogger().info("\033[38;5;214m  \\___ \\ / _ \\| |_  / __/ _ \\");
        getLogger().info("\033[38;5;214m  ____) | (_) | |/ /| ||  __/");
        getLogger().info("\033[38;5;214m |_____/ \\___/|_/___|\\__\\___|");

        stopScaryNight();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void scheduleEffectsAndSounds() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                effectManager.scheduleRandomEffects(player, 0L, 60L);
                soundManager.scheduleRandomSounds(player, 0L, 100L);
            }
        }, 0L, 200L);
    }

    public void stopScaryNight() {
        effectManager.stopRandomEffects();
        soundManager.stopRandomSounds();
    }
}
