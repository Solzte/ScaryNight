package me.tuselcraft.listener;

import me.tuselcraft.ScaryNight;
import me.tuselcraft.manager.EffectManager;
import me.tuselcraft.manager.SoundManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ScaryNightListener implements Listener {
    private final SoundManager soundManager;
    private final EffectManager effectManager;
    private final ScaryNight plugin;

    public ScaryNightListener(ScaryNight plugin) {
        this.plugin = plugin;
        this.soundManager = new SoundManager(plugin);
        this.effectManager = new EffectManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!event.getPlayer().isOnline()) {
                    cancel();
                    return;
                }
                int soundInterval = plugin.getConfigManager().getSoundInterval();
                int effectInterval = plugin.getConfigManager().getEffectInterval();
                
                soundManager.scheduleRandomSounds(event.getPlayer(), 0, soundInterval);
                effectManager.scheduleRandomEffects(event.getPlayer(), 0, effectInterval);
            }
        }.runTaskLater(plugin, 20L);
    }
}
