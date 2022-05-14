package net.staticstudios.gui;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public record GUIItem(ItemStack item) {

    public boolean hasRunnable() {
        if (item == null) return false;
        return item.getItemMeta().getPersistentDataContainer().has(GUIUtils.RUNNABLE_UUID_NAMESPACE_KEY, PersistentDataType.STRING);
    }

    public void setRunnable(String runnableUUID, UUID guiUUID) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(GUIUtils.RUNNABLE_UUID_NAMESPACE_KEY, PersistentDataType.STRING, runnableUUID);
        meta.getPersistentDataContainer().set(GUIUtils.GUI_UUID_NAMESPACE_KEY, PersistentDataType.STRING, guiUUID.toString());
        item.setItemMeta(meta);
    }

    public GUIRunnable getRunnable() {
        if (!hasRunnable()) return (p, t) -> {};
        String runnableUUID = item.getItemMeta().getPersistentDataContainer().get(GUIUtils.RUNNABLE_UUID_NAMESPACE_KEY, PersistentDataType.STRING);
        UUID guiUUID = UUID.fromString(item.getItemMeta().getPersistentDataContainer().get(GUIUtils.GUI_UUID_NAMESPACE_KEY, PersistentDataType.STRING));
        if (!StaticGUI.allMenus.containsKey(guiUUID)) return (p, t) -> {};
        if (!StaticGUI.allMenus.get(guiUUID).callbacks.containsKey(runnableUUID)) return (p, t) -> {};
        return StaticGUI.allMenus.get(guiUUID).callbacks.get(runnableUUID);
    }
}
