package cz.radek.advancedwelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class AdvancedWelcomeTabCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            @NotNull String[] args
    ) {

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {

            String input = args[0].toLowerCase();

            if ("info".startsWith(input)) {
                completions.add("info");
            }

            if (
                    sender.hasPermission("advancedwelcome.reload")
                    && "reload".startsWith(input)
            ) {
                completions.add("reload");
            }
        }

        return completions;
    }
}
