package net.disk0401.paperpluginnewrelic.command;

import net.disk0401.paperpluginnewrelic.PaperPluginNewrelic;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPPNR implements CommandExecutor {
    PaperPluginNewrelic plugin;

    public CommandPPNR(PaperPluginNewrelic plugin) {
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Boolean isPlayer = sender instanceof Player;
        if (!isPlayer) {
            sender.sendMessage(ChatColor.RED + "プレイヤーのみ実行可能です。");
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("newrelic.admin")) {
            player.sendMessage(ChatColor.RED + "権限がないため、実行できませんでした。");
            return false;
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("enable")) {
                plugin.getConfig().set("enabled", true);
                plugin.saveConfig();
                player.sendMessage(ChatColor.GREEN + "New Relic plugin reporting has been enabled.");
                return true;
            } else if (args[0].equalsIgnoreCase("disable")) {
                plugin.getConfig().set("enabled", false);
                plugin.saveConfig();
                player.sendMessage(ChatColor.GREEN + "New Relic plugin reporting has been disabled.");
                return true;
            }
        }

        // とりあえず、現在の設定を返しておく
        if (args.length == 0) {
            player.sendMessage("現在の設定は、「" + plugin.getConfig().getBoolean("enabled") + "」です。");
            return true;
        }
        player.sendMessage(ChatColor.RED + "不明です。");
        return false;
    }
}
