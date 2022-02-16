package net.disk0401.paperpluginnewrelic;

import net.disk0401.paperpluginnewrelic.command.CommandPPNR;
import net.disk0401.paperpluginnewrelic.command.TabCompleterPPNR;
import net.disk0401.paperpluginnewrelic.event.NewRelicListener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class PaperPluginNewrelic extends JavaPlugin {

    public Permission ppnrPermission = new Permission("newrelic.admin");

    @Override
    public void onEnable() {
        getLogger().info(new Object() {
        }.getClass().getEnclosingClass().getName() + "を起動しました");
        CommandPPNR commandPPNR = new CommandPPNR(this);
        this.getCommand("newrelic").setExecutor(commandPPNR);
        this.getConfig().addDefault("enabled", true);
        this.getConfig().addDefault("track.entity.death", true);
        this.getConfig().addDefault("track.creature.spawn", true);
        this.getConfig().addDefault("track.player.death", true);
        this.getConfig().addDefault("track.player.join", true);
        this.getConfig().addDefault("track.player.kick", true);
        this.getConfig().addDefault("track.player.quit", true);
        this.getConfig().addDefault("track.player.login", true);
        this.getConfig().addDefault("track.player.respawn", true);
        this.getConfig().addDefault("track.player.teleport", true);
        this.getConfig().addDefault("track.block.place", true);
        this.getConfig().addDefault("track.block.break", true);
        this.getConfig().addDefault("track.server.command", true);
        this.getConfig().addDefault("track.server.remotecommand", true);
        this.getConfig().addDefault("track.chunk.load", true);
        this.getConfig().addDefault("track.chunk.unload", true);
        this.getConfig().addDefault("track.chunk.unload", true);
        this.getConfig().options().copyDefaults(true);
        saveConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.addPermission(ppnrPermission);

        // Eventリスナーの登録
        new NewRelicListener(this);
    }

    @Override
    public void onDisable() {
        getLogger().info(new Object() {
        }.getClass().getEnclosingClass().getName() + "を停止しました");
        saveConfig();
    }
}
