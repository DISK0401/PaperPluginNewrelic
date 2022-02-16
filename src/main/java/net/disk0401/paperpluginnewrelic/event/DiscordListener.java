package net.disk0401.paperpluginnewrelic.event;

import net.disk0401.paperpluginnewrelic.PaperPluginNewrelic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageBuilder;

public class DiscordListener implements Listener {
    PaperPluginNewrelic plugin;

    String channelId = "";
    DiscordApi api = null;

    public DiscordListener(PaperPluginNewrelic plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        String token = this.plugin.getConfig().getString("discord.token");
        this.api = new DiscordApiBuilder().setToken(token).login().join();
        this.channelId = this.plugin.getConfig().getString("discord.notice.channel.id");
        plugin.getLogger().info(token);
        plugin.getLogger().info(channelId);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (plugin.getConfig().getBoolean("enabled") && plugin.getConfig().getBoolean("track.player.join")) {
            Player player = e.getPlayer();
            if (!player.hasPlayedBefore()) {
                new MessageBuilder()
                        .append("「")
                        .append(player.getName())
                        .append("」がminecraftに参加しました。")
                        .send(api.getTextChannelById(channelId).get());
//                        .send(api.getTextChannelsByName("通知先のチャンネル名").iterator().next());
            } else {
            }
        } else {
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (plugin.getConfig().getBoolean("enabled") && plugin.getConfig().getBoolean("track.player.login")) {
            Player player = e.getPlayer();
            if (!player.hasPlayedBefore()) {
                new MessageBuilder()
                        .append("「")
                        .append(player.getName())
                        .append("」がminecraftにログインしました。")
                        .send(api.getTextChannelById(channelId).get());
//                        .send(api.getTextChannelsByName("通知先のチャンネル名").iterator().next());
            } else {
            }
        } else {
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (plugin.getConfig().getBoolean("enabled") && plugin.getConfig().getBoolean("track.player.quit")) {
            Player player = e.getPlayer();
            new MessageBuilder()
                    .append("「")
                    .append(player.getName())
                    .append("」がminecraftにログアウトしました。")
                    .send(api.getTextChannelById(channelId).get());
        }
    }
}
