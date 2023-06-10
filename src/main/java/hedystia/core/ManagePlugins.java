package hedystia.core;

import hedystia.commands.ManagePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ManagePlugins extends JavaPlugin {
    ConsoleCommandSender mycmd = Bukkit.getConsoleSender();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("manage_plugins").setExecutor(new ManagePlugin());
        getCommand("manage_plugins").setTabCompleter(this);
        mycmd.sendMessage("&bEl plugin ha sido iniciado");
    }

    @Override
    public void onDisable() { mycmd.sendMessage("&cEl plugin ha sido desactivado"); }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("manage_plugins")) {
            List<String> completions = new ArrayList<>();
            if (args.length == 1) {
                completions.addAll(Arrays.asList("unload", "load", "reload", "list"));
            } else if (args.length == 2) {
                String subCommand = args[0].toLowerCase();
                if (subCommand.equals("unload") || subCommand.equals("load") || subCommand.equals("reload")) {
                    for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                        if(!plugin.getName().equals("ManagePlugins")) {
                            completions.add(plugin.getName());
                        }
                    }
                }
            }
            return completions;
        }
        return null;
    }
}