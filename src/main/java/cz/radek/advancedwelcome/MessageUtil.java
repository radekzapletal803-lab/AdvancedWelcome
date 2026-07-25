package cz.radek.advancedwelcome;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public final class MessageUtil {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private MessageUtil() {
        // Tato trida se nema vytvaret pomoci "new".
    }

    public static Component parse(String message, Player player) {
        if (message == null || message.isBlank()) {
            return Component.empty();
        }

        String parsedMessage = message
                .replace("%player%", player.getName())
                .replace("%displayname%", player.getName())
                .replace("%online%", String.valueOf(player.getServer().getOnlinePlayers().size()))
                .replace("%max_players%", String.valueOf(player.getServer().getMaxPlayers()));

        return MINI_MESSAGE.deserialize(parsedMessage);
    }
}
