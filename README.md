# Current Version: 1.0.0
Requirements:

    - You must be using PaperMC or a fork of it (This will not work with just Spigot/Bukkit!
    - Curently this API only supports servers running 1.18.1+ (This version limit should be lowered in the near future)

# StaticStudios-StaticGUI
A simple and lightweight Minecraft GUI API for developers to use.

Not a developer but a server owner? Don't worry, there is a configurable plugin that makes use of this API and allows you to easily create GUIs using only `yml` files, you don't have to understand code to use it!

Check it out: https://github.com/Sammster10/StaticStudios-GUIPlugin

# Here are some examples of how to use this API:

<b>Example 1, using the StaticGUI class:</b><br><br>
Everything is defined in the constructor and there is only ever one instance of this menu.
You should only use the StaticGUI class if there will only ever be one instance of the GUI.
```java
public class ExampleStaticGUI extends StaticGUI {

    public final StaticGUI gui = new ExampleStaticGUI();

    private ExampleStaticGUI() {
        super(9, "Example GUI!");
        setItem(0, createButton(Material.DIAMOND, "I am a normal button",
                List.of("lore line 1!", "lore line 2!!"), (_player, clickType) -> {
            _player.sendMessage("You clicked this button with the click type: " + clickType.name());
        }));
        setItem(1, ench(createButton(Material.DIAMOND, "I am a normal button but I am enchanted!",
                List.of("lore line 1!", "lore line 2!!"), (_player, clickType) -> {
                    _player.sendMessage("You clicked this button with the click type: " + clickType.name());
                })));
        for (int i = 2; i < 9; i++) addItem(createGrayPlaceHolder("I am a placeholder"));
        setOnCloseRun((_player, clickType) -> {
            _player.sendMessage("You closed a menu, the click type, 'clickType' in this case, will always be null when an on close event is run");
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
                c.createButton(Material.EMERALD, "Your IGN is: " + player.getName(), List.of("I am lore!"), (_player, clickType) -> {
                    _player.sendMessage("You clicked a button!");
                }),
                ench(c.createButton(Material.EMERALD, "Your IGN is: " + player.getName(), List.of("I am an enchanted button!"), (_player, clickType) -> {
                    _player.sendMessage("You clicked a button!");
                }))
        );
        c.setOnCloseRun((_player, clickType) -> {
            _player.sendMessage("You closed the example menu, have fun making your own!");
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

# How to integrate this into your project(s):
1. Make sure that maven is installed
2. Clone this repository
3. Open a command prompt/terminal and cd to this project's home directory (the directory which has the src folder, the .gitignore, ect...)
4. Run the following command: `mvn install`
5. The project has now been installed in your local maven repository, you can now add the following to your `pom.xml` to use this as a dependency:
```xml
    <dependencies>
        <dependency>
            <groupId>net.staticstudios</groupId>
            <artifactId>static-gui</artifactId>
            <version>[VERSION]</version>
        </dependency>
    </dependencies>
```
6. Replace `[VERSION]` with the version specified at the top of this document
7. In your main class's onEnable method, add the following line to enable the API: `StaticGUI.enable(this);`

# Support Discord:
https://discord.gg/9S6K9E5
