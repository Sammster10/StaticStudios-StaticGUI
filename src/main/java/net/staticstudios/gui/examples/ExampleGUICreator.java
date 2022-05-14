package net.staticstudios.gui.examples;

import net.staticstudios.gui.GUICreator;
import net.staticstudios.gui.GUIUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * This is an example class using the GUICreator
 *
 * The GUI creator is used instead of StaticGUI because this menu will display information that may change and is different for each player
 *
 * It is optional to extend GUIUtils however it makes the code cleaner when defining placeholders
 *
 * To open this menu, call the following: ExampleGUICreator.openMainMenu(player);
 */
public class ExampleGUICreator extends GUIUtils {

    public static void openMainMenu(Player player) {
        GUICreator c = new GUICreator(9, "This gui was created using the GUICreator class!");
        //c.setDestroyOnClose(true); you should avoid making this call (in most cases) as it will keep ths GUI in memory
        //forever. It is set to false by default. Whenever a menu is closed, it is destroyed (taken out of memory) by default.
        //If you want it to not be destroyed, maybe consider using StaticGUI (applicable in most cases)
        c.setItems(
                c.createButton(Material.EMERALD, "Your IGN is: " + player.getName(), List.of("I am lore!"), (p, t) -> {
                    p.sendMessage("You clicked a button!");
                }),
                ench(c.createButton(Material.EMERALD, "Your IGN is: " + player.getName(), List.of("I am an enchanted button!"), (p, t) -> {
                    p.sendMessage("You clicked a button!");
                }))
        );
        c.setOnCloseRun((p, t) -> {
            p.sendMessage("You closed the example menu, have fun making your own!");
        });
        c.open(player);
    }
}
