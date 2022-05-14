# StaticStudios-StaticGUI
A simple Minecraft GUI API for developers to use.

# Here are some examples of how to use this API:

<b>Example 1, using the StaticGUI class:</b><br><br>
Everything is defined in the constructor and there is only ever on instance of this menu.
You should only use the StaticGUI class if there will only ever be one instance of the GUI.
```java
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
        for (int i = 2; i < 9; i++) addItem(createGrayPlaceHolder("I am a placeholder"));
        setOnCloseRun((p, t) -> {
            p.sendMessage("You closed a menu, the click type, 't' in this case, will always be null when an on close event is run");
        });
    }
}
```

<b>Example 2, using the GUICreator:</b><br><br>
It is recommended to use the GUICreator instead of the StaticGUI class however both can be used.
It is optional to extend GUIUtils however it makes the code cleaner when defining placeholders.
```java
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
```


To make a player open one of the above menus, do the following:
```java
  ExampleStaticGUI.open(player);
  ExampleGUICreator.openMainMenu(player);
```


# Support Discord:
https://discord.gg/9S6K9E5
