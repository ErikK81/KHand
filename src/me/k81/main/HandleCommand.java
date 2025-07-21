package me.k81.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class HandleCommand implements CommandExecutor {

    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    private BukkitTask task;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("handle")) {
        	 if (player.hasPermission("khand.handle")) {
	            String playerId = player.getUniqueId().toString();
	            String configValue = Main.getPlugin().getConfig().getString(playerId);
	
	            if (args.length == 0) {
	                if ("true".equals(configValue)) {
	                    player.sendMessage("§4Item delivery mode canceled!");
	                    Main.getPlugin().getConfig().set(playerId, "false");
	
	                    if (task != null) {
	                        task.cancel();
	                        task = null;
	                    }
	                } else {
	                    Main.getPlugin().getConfig().set(playerId, "true");
	                    player.sendMessage("§4Item delivery mode enabled!");

	                    task = scheduler.runTaskTimer(Main.getPlugin(), () -> {
	                        player.sendTitle("", "§4Handle the item!!", 0, 20, 10);
	                    }, 5L, 20L);
	
	                    Main.getPlugin().saveConfig();
	                }
	                return true;
	            }
        	 }
        }

        return false;
    }
}
