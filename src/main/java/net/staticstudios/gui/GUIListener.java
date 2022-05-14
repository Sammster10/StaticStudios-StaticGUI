package net.staticstudios.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GUIListener implements Listener {

    @EventHandler
    void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (!(e.getClickedInventory().getHolder() instanceof StaticGUI)) return;
        e.setCancelled(true);
        new GUIItem(e.getCurrentItem()).getRunnable().run((Player) e.getWhoClicked(), e.getClick());
    }

    @EventHandler
    void invClosed(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder() instanceof StaticGUI gui)) return;
        Player player = (Player) e.getPlayer();
        if (gui.destroyOnClose) gui.destroy();
        if (e.getReason().equals(InventoryCloseEvent.Reason.PLAYER)) if (gui.getMenuToOpenOnClose() != null)  {
            Bukkit.getScheduler().runTaskLater(StaticGUI.getParent(), () -> gui.getMenuToOpenOnClose().open(player), 1);
        } else if (gui.getOnCloseRun() != null) {
            Bukkit.getScheduler().runTaskLater(StaticGUI.getParent(), () -> gui.getOnCloseRun().run(player, null), 1);
        }
    }
    @EventHandler
    void invOpened(InventoryOpenEvent e) {
        if (!(e.getInventory().getHolder() instanceof StaticGUI)) return;
    }
}
