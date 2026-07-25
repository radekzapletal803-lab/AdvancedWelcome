package cz.radek.advancedwelcome;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedWelcome extends JavaPlugin {

    @Override
    public void onEnable() {

        // Vytvoří config.yml, pokud ještě neexistuje
        saveDefaultConfig();

        // Zapnutí listeneru pro připojení a odpojení hráčů
        getServer().getPluginManager().registerEvents(
                new PlayerConnectionListener(this),
                this
        );

        // Registrace příkazu /advancedwelcome
        if (getCommand("advancedwelcome") != null) {
            getCommand("advancedwelcome").setExecutor(
                    new AdvancedWelcomeCommand(this)
            );
        }

        getLogger().info("------------------------------");
        getLogger().info("AdvancedWelcome v1.0.0");
        getLogger().info("Plugin byl uspesne zapnut!");
        getLogger().info("------------------------------");
    }

    @Override
    public void onDisable() {
        getLogger().info("AdvancedWelcome byl vypnut.");
    }
}
