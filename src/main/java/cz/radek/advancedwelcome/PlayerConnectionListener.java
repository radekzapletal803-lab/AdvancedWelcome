package cz.radek.advancedwelcome;

import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.Duration;

public final class PlayerConnectionListener implements Listener {

    private final AdvancedWelcome plugin;

    public PlayerConnectionListener(AdvancedWelcome plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // JOIN ZPRÁVA
        String joinMessage = plugin.getConfig().getString(
                "messages.join",
                "<gray>[<green>+</green>] <yellow>%player%</yellow> se připojil."
        );

        event.joinMessage(MessageUtil.parse(joinMessage, player));

        // PRVNÍ PŘIPOJENÍ
        if (!player.hasPlayedBefore()) {

            String firstJoinMessage = plugin.getConfig().getString(
                    "messages.first-join",
                    "<gold>★ <yellow>%player%</yellow> je na serveru poprvé!"
            );

            plugin.getServer().broadcast(
                    MessageUtil.parse(firstJoinMessage, player)
            );
        }

        // SOUKROMÁ WELCOME ZPRÁVA
        String welcomeMessage = plugin.getConfig().getString(
                "messages.welcome",
                "<green>Vítej na serveru, <yellow>%player%</yellow>!"
        );

        player.sendMessage(
                MessageUtil.parse(welcomeMessage, player)
        );

        // TITLE
        if (plugin.getConfig().getBoolean("title.enabled", true)) {

            String titleText = plugin.getConfig().getString(
                    "title.title",
                    "<gold>Vítej!"
            );

            String subtitleText = plugin.getConfig().getString(
                    "title.subtitle",
                    "<yellow>%player%</yellow>"
            );

            int fadeIn = plugin.getConfig().getInt("title.fade-in", 500);
            int stay = plugin.getConfig().getInt("title.stay", 3000);
            int fadeOut = plugin.getConfig().getInt("title.fade-out", 1000);

            Title title = Title.title(
                    MessageUtil.parse(titleText, player),
                    MessageUtil.parse(subtitleText, player),
                    Title.Times.times(
                            Duration.ofMillis(fadeIn),
                            Duration.ofMillis(stay),
                            Duration.ofMillis(fadeOut)
                    )
            );

            player.showTitle(title);
        }

        // ZVUK
        if (plugin.getConfig().getBoolean("sound.enabled", true)) {

            String soundName = plugin.getConfig().getString(
                    "sound.name",
                    "ENTITY_PLAYER_LEVELUP"
            );

            float volume = (float) plugin.getConfig().getDouble(
                    "sound.volume",
                    1.0
            );

            float pitch = (float) plugin.getConfig().getDouble(
                    "sound.pitch",
                    1.0
            );

            try {
                Sound sound = Sound.valueOf(soundName.toUpperCase());

                player.playSound(
                        player.getLocation(),
                        sound,
                        volume,
                        pitch
                );

            } catch (IllegalArgumentException exception) {
                plugin.getLogger().warning(
                        "Neplatny zvuk v config.yml: " + soundName
                );
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String quitMessage = plugin.getConfig().getString(
                "messages.quit",
                "<gray>[<red>-</red>] <yellow>%player%</yellow> se odpojil."
        );

        event.quitMessage(
                MessageUtil.parse(quitMessage, player)
        );
    }
}
