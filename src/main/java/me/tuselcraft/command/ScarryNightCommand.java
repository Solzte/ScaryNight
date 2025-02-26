package me.tuselcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScaryNightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only available to players.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length > 0 && args[0].equalsIgnoreCase("start")) {
            startScaryNight(player);
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("stop")) {
            stopScaryNight(player);
            return true;
        } else {
            player.sendMessage("You can type '/scarynight start' to start the night or '/scarynight stop' to stop it.");
            return false;
        }
    }

    private void startScaryNight(Player player) {
        player.sendMessage("The scary night has begun! The night will be darker and more dangerous...");
        player.getWorld().setTime(13000);
    }

    private void stopScaryNight(Player player) {
        player.sendMessage("The scary night is over.");
        player.getWorld().setTime(0);
    }
}
