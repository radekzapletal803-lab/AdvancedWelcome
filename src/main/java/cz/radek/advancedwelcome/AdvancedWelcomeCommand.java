package cz.radek.advancedwelcome;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class AdvancedWelcomeCommand implements CommandExecutor {

    private final AdvancedWelcome plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public AdvancedWelcomeCommand(AdvancedWelcome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        // /advancedwelcome
        if (args.length == 0) {

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gold><bold>AdvancedWelcome</bold></gold> <gray>v"
                                    + plugin.getPluginMeta().getVersion()
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<yellow>/advancedwelcome info <gray>- Informace o pluginu"
                    )
            );

            if (sender.hasPermission("advancedwelcome.reload")) {
                sender.sendMessage(
                        miniMessage.deserialize(
                                "<yellow>/advancedwelcome reload <gray>- Reload configu"
                        )
                );
            }

            return true;
        }

        // /advancedwelcome info
        if (args[0].equalsIgnoreCase("info")) {

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<dark_gray>--------------------------------"
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gold><bold>AdvancedWelcome</bold></gold>"
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gray>Verze: <yellow>"
                                    + plugin.getPluginMeta().getVersion()
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gray>Autor: <yellow>Radek"
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gray>Platforma: <yellow>Paper"
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<gray>Funkce: <green>Join, Quit, First Join, Title, Sound"
                    )
            );

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<dark_gray>--------------------------------"
                    )
            );

            return true;
        }

        // /advancedwelcome reload
        if (args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("advancedwelcome.reload")) {

                sender.sendMessage(
                        miniMessage.deserialize(
                                "<red>Na tento prikaz nemas opravneni."
                        )
                );

                return true;
            }

            plugin.reloadConfig();

            sender.sendMessage(
                    miniMessage.deserialize(
                            "<green>✔ Config byl uspesne reloadnut!"
                    )
            );

            plugin.getLogger().info(
                    "Config reloadnul: " + sender.getName()
            );

            return true;
        }

        // Neznamy argument
        sender.sendMessage(
                miniMessage.deserialize(
                        "<red>Neznamy prikaz. Pouzij <yellow>/advancedwelcome</yellow>."
                )
        );

        return true;
    }
}
