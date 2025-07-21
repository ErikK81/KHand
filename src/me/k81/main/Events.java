package me.k81.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class Events implements Listener {
    public Events() {
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            Player clicked = (Player) e.getRightClicked();
            ItemStack is = p.getInventory().getItemInMainHand();
            if (clicked.getInventory().firstEmpty() != -1) {
                if (Objects.equals(Main.getPlugin().getConfig().get(String.valueOf(p.getUniqueId())), "true")) {
                    p.getInventory().removeItem(is);
                    clicked.getInventory().addItem(is);
                }
            } else {
                if (Objects.equals(Main.getPlugin().getConfig().get(String.valueOf(p.getUniqueId())), "true")) {
                    p.sendMessage("§4Inventario do jogador esta cheio!");
                    e.setCancelled(true);
                }
            }
        }
    }
}
