package net.staticstudios.gui.examples;

import net.staticstudios.gui.StaticGUI;
import org.bukkit.Material;

import java.util.List;

/**
 * This is an example gui using the StaticGUI.
 *
 * Everything is defined in the constructor and there is only ever on instance of this menu.
 *
 * To make a player open this menu, call the following: ExampleStaticGUI.open(player);
 */
public class ExampleStaticGUI extends StaticGUI {

    public final StaticGUI gui = new ExampleStaticGUI();

    private ExampleStaticGUI() {
        super(9, "Example GUI!");
        getInventory().setItem(0, createButton(Material.DIAMOND, "I am a normal button",
                List.of("lore line 1!", "lore line 2!!"), (p, t) -> {
            p.sendMessage("You clicked this button with the click type: " + t.name());
        }));
        getInventory().setItem(1, ench(createButton(Material.DIAMOND, "I am a normal button but I am enchanted!",
                List.of("lore line 1!", "lore line 2!!"), (p, t) -> {
                    p.sendMessage("You clicked this button with the click type: " + t.name());
                })));
        for (int i = 2; i < 9; i++) getInventory().addItem(createGrayPlaceHolder("I am a placeholder"));
        setOnCloseRun((p, t) -> {
            p.sendMessage("You closed a menu, the click type, 't' in this case, will always be null when an on close event is run");
        });
    }
}
