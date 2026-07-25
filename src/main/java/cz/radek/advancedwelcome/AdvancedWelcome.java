package cz.radek.advancedwelcome;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedWelcome extends JavaPlugin {

    @Override
    public void onEnable() {

        // Vytvori config.yml, pokud jeste neexistuje
        saveDefaultConfig();

        // Registrace join/quit listeneru
        getServer().getPluginManager().registerEvents(
                new PlayerConnectionListener(this),
                this
        );

        // Registrace prikazu
        PluginCommand command = getCommand("advancedwelcome");

        if (command != null) {
            command.setExecutor(new AdvancedWelcomeCommand(this));
            command.setTabCompleter(new AdvancedWelcomeTabCompleter());
        } else {
            getLogger().severe(
                    "Prikaz advancedwelcome nebyl nalezen v plugin.yml!"
            );
        }

        getLogger().info("--------------------------------");
        getLogger().info("AdvancedWelcome v" + getPluginMeta().getVersion());
        getLogger().info("Plugin byl uspesne zapnut!");
        getLogger().info("--------------------------------");
    }

    @Override
    public void onDisable() {
        getLogger().info("AdvancedWelcome byl vypnut.");
    }
}
