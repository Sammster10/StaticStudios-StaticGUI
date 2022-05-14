package net.staticstudios.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This class is persistent as any GUI that is created with this (instead of GUICreator) will be kept in memory forever and will not be destroyed
 * Use this class for any static menus that will never be created more than once (Ex: How To Play Menu, its content is static)
 */
public abstract class StaticGUI extends GUIUtils implements InventoryHolder {

    private static JavaPlugin parent;
    public static JavaPlugin getParent() { return parent; }
    public static void enable(JavaPlugin parent) {
        StaticGUI.parent = parent;
        GUIUtils.init();
        parent.getServer().getPluginManager().registerEvents(new GUIListener(), parent);
    }

    public static Map<UUID, StaticGUI> allMenus = new HashMap<>();

    public static boolean checkIfPlayerIsViewingAMenu(Player player) {
        return player.getOpenInventory().getTopInventory().getHolder() instanceof StaticGUI;
    }
    public static StaticGUI getMenuBeingViewedByPlayer(Player player) {
        if (!checkIfPlayerIsViewingAMenu(player)) return null;
        return (StaticGUI) player.getOpenInventory().getTopInventory().getHolder();
    }

    private final UUID guiUUID = UUID.randomUUID();

    private final Inventory inventory;
    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private StaticGUI openOnClose = null;
    private GUIRunnable onCloseRun = null;
    private GUIRunnable onOpenRun = null;


    public StaticGUI getMenuToOpenOnClose() { return openOnClose; }
    public void setInventoryToOpenOnClose(StaticGUI menu) { openOnClose = menu; }
    public GUIRunnable getOnCloseRun() { return onCloseRun; }
    public void setOnCloseRun(GUIRunnable r) { onCloseRun = r; }
    public GUIRunnable getOnOpenRun() { return onOpenRun; }
    public void setOnOpenRun(GUIRunnable r) { onOpenRun = r; }



    private String menuID = ""; //An ID that can be used to check what menu a player has opened. For example, the ID could be "shop-food"and if you called the getMenuID() method on the menu that the player has opened, then you would know that they are looking at the food shop
    public String getMenuID() { return menuID; }
    public void setMenuID(String menuID) { this.menuID = menuID; }
    public boolean destroyOnClose = false;
    public void setDestroyOnClose(boolean destroy) {
        destroyOnClose = destroy;
    }
    public Map<String , GUIRunnable> callbacks = new HashMap<>();


    public StaticGUI(int size, String title) {
        inventory = Bukkit.createInventory(this, Math.max(1, (size / 9)) * 9, title);
        allMenus.put(guiUUID, this);
    }

    public StaticGUI(String title, InventoryType inventoryType) {
        inventory = Bukkit.createInventory(this, inventoryType, title);
        allMenus.put(guiUUID, this);
    }

    public void destroy() { allMenus.remove(guiUUID); }

    public void open(Player player) {
        player.openInventory(inventory);
        if (onOpenRun != null) onOpenRun.run(player, null);
    }

    public StaticGUI setItems(ItemStack... items) {
        inventory.clear();
        int i = 0;
        for (ItemStack item : items) {
            inventory.setItem(i, item);
            i++;
        }
        return this;
    }
    public StaticGUI setItem(int index, ItemStack item) {
        inventory.setItem(index, item);
        return this;
    }
    public StaticGUI addItem(ItemStack... item) {
        inventory.addItem(item);
        return this;
    }

    /**
     * Fills all empty slots with the item specified
     * @param item to replace empty slots
     */
    public StaticGUI fill(ItemStack item) {
        while (inventory.firstEmpty() != -1) inventory.setItem(inventory.firstEmpty(), item);
        return this;
    }

    public void applyCallbackToItem(ItemStack itemStack, GUIRunnable runnable) {
        String runnableUUID = UUID.randomUUID().toString();
        new GUIItem(itemStack).setRunnable(runnableUUID, guiUUID);
        callbacks.put(runnableUUID, runnable);
    }






    public ItemStack createButton(Material icon, String name, List<String> lore) {
        if (lore != null) lore = new ArrayList<>(lore); //Some lists are immutable
        ItemStack itemStack = new ItemStack(icon);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r" + name));
        if (lore != null) {
            for (int i = 0; i < lore.size(); i++)
                lore.set(i, ChatColor.translateAlternateColorCodes('&', "&7" + lore.get(i)));
            meta.setLore(lore);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createButton(ItemStack item, GUIRunnable callback) {
        applyCallbackToItem(item, callback);
        return item;
    }
    public ItemStack createButton(Material icon, String name, List<String> lore, GUIRunnable callback) {
        if (lore != null) lore = new ArrayList<>(lore); //Some lists are immutable
        ItemStack itemStack = createButton(icon, name, lore);
        applyCallbackToItem(itemStack, callback);
        return itemStack;
    }

    public ItemStack createButtonOfPlayerSkull(OfflinePlayer player, String name, List<String> lore, GUIRunnable callback) {
        if (lore != null) lore = new ArrayList<>(lore); //Some lists are immutable
        ItemStack itemStack = createButtonOfPlayerSkull(player, name, lore);
        applyCallbackToItem(itemStack, callback);
        return itemStack;
    }

    public ItemStack createButtonOfPlayerSkull(OfflinePlayer player, String name, List<String> lore) {
        if (lore != null) lore = new ArrayList<>(lore); //Some lists are immutable
        ItemStack itemStack = GUIUtils.createCustomSkull(player);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r" + name));
        for (int i = 0; i < lore.size(); i++) lore.set(i, ChatColor.translateAlternateColorCodes('&', "&7" + lore.get(i)));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
