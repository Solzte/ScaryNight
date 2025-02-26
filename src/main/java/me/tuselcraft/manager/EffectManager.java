package me.tuselcraft.manager;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class EffectManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();
    private BukkitRunnable effectTask;

    public EffectManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void spawnRandomScaryEffect(Player player) {
        Location loc = player.getLocation().add(random.nextDouble() * 2 - 1, random.nextDouble() * 2, random.nextDouble() * 2 - 1);

        Particle[] scaryParticles = {
            Particle.END_ROD,
            Particle.SOUL_FIRE_FLAME,
            Particle.CAMPFIRE_COSY_SMOKE
        };

        Particle randomParticle = scaryParticles[random.nextInt(scaryParticles.length)];

        player.getWorld().spawnParticle(randomParticle, loc, 10, 0.5, 0.5, 0.5, 0.1);
    }

    public void scheduleRandomEffects(Player player, long delay, long interval) {
        if (effectTask != null) {
            effectTask.cancel();
        }

        effectTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                spawnRandomScaryEffect(player);
            }
        };

        effectTask.runTaskTimer(plugin, delay, interval);
    }

    public void stopRandomEffects() {
        if (effectTask != null) {
            effectTask.cancel();
            effectTask = null;
        }
    }
}
