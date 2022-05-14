package net.staticstudios.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIUtils {
    public static NamespacedKey RUNNABLE_UUID_NAMESPACE_KEY;
    public static NamespacedKey GUI_UUID_NAMESPACE_KEY;

    public static void init() {
        RUNNABLE_UUID_NAMESPACE_KEY = new NamespacedKey(StaticGUI.getParent(), "runnableUUID");
        GUI_UUID_NAMESPACE_KEY = new NamespacedKey(StaticGUI.getParent(), "guiUUID");
    }

    public static ItemStack createCustomSkull(OfflinePlayer player) {
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullItem.setItemMeta(skullMeta);
        return skullItem;
    }

    public static ItemStack ench(ItemStack item) {
        item = item.clone();
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.LURE, 1);
        return item;
    }

    public static ItemStack ench(Material mat) {
        return ench(new ItemStack(mat));
    }

    private static ItemStack createPlaceHolder(Material mat) {
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(ChatColor.GREEN + "");
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    private static ItemStack createPlaceHolder(Material mat, String name) {
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack createWhitePlaceHolder() {
        return createPlaceHolder(Material.WHITE_STAINED_GLASS_PANE);
    }
    public static ItemStack createWhitePlaceHolder(String name) {
        return createPlaceHolder(Material.WHITE_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createOrangePlaceHolder() {
        return createPlaceHolder(Material.ORANGE_STAINED_GLASS_PANE);
    }
    public static ItemStack createOrangePlaceHolder(String name) {
        return createPlaceHolder(Material.ORANGE_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createMagentaPlaceHolder() {
        return createPlaceHolder(Material.MAGENTA_STAINED_GLASS_PANE);
    }
    public static ItemStack createMagentaPlaceHolder(String name) {
        return createPlaceHolder(Material.MAGENTA_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createLightBluePlaceHolder() {
        return createPlaceHolder(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
    }
    public static ItemStack createLightBluePlaceHolder(String name) {
        return createPlaceHolder(Material.LIGHT_BLUE_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createYellowPlaceHolder() {
        return createPlaceHolder(Material.YELLOW_STAINED_GLASS_PANE);
    }
    public static ItemStack createYellowPlaceHolder(String name) {
        return createPlaceHolder(Material.YELLOW_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createLimePlaceHolder() {
        return createPlaceHolder(Material.LIME_STAINED_GLASS_PANE);
    }
    public static ItemStack createLimePlaceHolder(String name) {
        return createPlaceHolder(Material.LIME_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createPinkPlaceHolder() {
        return createPlaceHolder(Material.PINK_STAINED_GLASS_PANE);
    }
    public static ItemStack createPinkPlaceHolder(String name) {
        return createPlaceHolder(Material.PINK_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createGrayPlaceHolder() {
        return createPlaceHolder(Material.GRAY_STAINED_GLASS_PANE);
    }
    public static ItemStack createGrayPlaceHolder(String name) {
        return createPlaceHolder(Material.GRAY_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createLightGrayPlaceHolder() {
        return createPlaceHolder(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    }
    public static ItemStack createLightGrayPlaceHolder(String name) {
        return createPlaceHolder(Material.LIGHT_GRAY_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createCyanPlaceHolder() {
        return createPlaceHolder(Material.CYAN_STAINED_GLASS_PANE);
    }
    public static ItemStack createCyanPlaceHolder(String name) {
        return createPlaceHolder(Material.CYAN_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createBluePlaceHolder() {
        return createPlaceHolder(Material.BLUE_STAINED_GLASS_PANE);
    }
    public static ItemStack createBluePlaceHolder(String name) {
        return createPlaceHolder(Material.BLUE_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createBrownPlaceHolder() {
        return createPlaceHolder(Material.BROWN_STAINED_GLASS_PANE);
    }
    public static ItemStack createBrownPlaceHolder(String name) {
        return createPlaceHolder(Material.BROWN_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createGreenPlaceHolder() {
        return createPlaceHolder(Material.GREEN_STAINED_GLASS_PANE);
    }
    public static ItemStack createGreenPlaceHolder(String name) {
        return createPlaceHolder(Material.GREEN_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createRedPlaceHolder() {
        return createPlaceHolder(Material.RED_STAINED_GLASS_PANE);
    }
    public static ItemStack createRedPlaceHolder(String name) {
        return createPlaceHolder(Material.RED_STAINED_GLASS_PANE, name);
    }
    public static ItemStack createBlackPlaceHolder() {
        return createPlaceHolder(Material.BLACK_STAINED_GLASS_PANE);
    }
    public static ItemStack createBlackPlaceHolder(String name) {
        return createPlaceHolder(Material.BLACK_STAINED_GLASS_PANE, name);
    }
}
