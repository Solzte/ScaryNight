package me.tuselcraft.manager;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class SoundManager {
    private final JavaPlugin plugin;
    private final Random random = new Random();
    private BukkitRunnable soundTask;

    public SoundManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void playRandomScarySound(Player player) {
        Sound[] scarySounds = {
            Sound.ENTITY_WITCH_AMBIENT,
            Sound.ENTITY_ZOMBIE_AMBIENT,
            Sound.ENTITY_ENDERMAN_SCREAM,
            Sound.ENTITY_GHAST_SCREAM,
            Sound.ENTITY_PHANTOM_AMBIENT
        };

        Sound randomSound = scarySounds[random.nextInt(scarySounds.length)];

        player.playSound(player.getLocation(), randomSound, 1.0f, 0.8f);
    }

    public void scheduleRandomSounds(Player player, long delay, long interval) {
        if (soundTask != null) {
            soundTask.cancel();
        }

        soundTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                playRandomScarySound(player);
            }
        };

        soundTask.runTaskTimer(plugin, delay, interval);
    }

    public void stopRandomSounds() {
        if (soundTask != null) {
            soundTask.cancel();
            soundTask = null;
        }
    }
}
