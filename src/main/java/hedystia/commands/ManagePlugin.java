package hedystia.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import java.util.List;

public class ManagePlugin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("plugin.reload")) {
            sender.sendMessage("§7〔§bManage§ePlugins§7〕§cYou do not have permission to use this command.op");
            return true;
        }
        if (args.length == 0 || args[0].length() == 0) {
            sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug list §7- list of plugins");
            sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug reload <plugin> §7- reloads a plugin");
            sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug load <plugin> §7- loads a plugin");
            sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug unload <plugin> §7- unloads a plugin");
        } else {
            String subCommand = args[0];
            switch (subCommand) {
                case "reload":
                    if (args.length < 2 || args[1].length() == 0) {
                        sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlease specify the plugin to reload.");
                    } else {
                        String pluginName = args[1];
                        if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                            Plugin targetPlugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
                            Bukkit.getPluginManager().disablePlugin(targetPlugin);
                            Bukkit.getPluginManager().enablePlugin(targetPlugin);
                            sender.sendMessage("§7〔§bManage§ePlugins§7〕§7[§6" + pluginName + "§7] §aPlugin reloaded.");
                        } else {
                            sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlugin '" + pluginName + "' does not exist.");
                        }
                    }
                    break;
                case "load":
                    if (args.length < 2 || args[1].length() == 0) {
                        sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlease specify the plugin to load.");
                    } else {
                        String pluginName = args[1];
                        if(pluginName.equals("ManagePlugins")){
                            sender.sendMessage("§7〔§bManage§ePlugins§7〕§cThe ManagePlugins plugin cannot be activated as it is already activated.");
                        } else {
                            if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                                Plugin targetPlugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
                                Bukkit.getPluginManager().enablePlugin(targetPlugin);
                                sender.sendMessage("§7〔§bManage§ePlugins§7〕§7[§6" + pluginName + "§7] §aPlugin loaded.");
                            } else {
                                sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlugin '" + pluginName + "' does not exist.");
                            }
                        }
                    }
                    break;
                case "unload":
                    if (args.length < 2 || args[1].length() == 0) {
                        sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlease specify the plugin to unload.");
                    } else {
                        String pluginName = args[1];
                        if(pluginName.equals("ManagePlugins")){
                            sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlugin '" + pluginName + "' cannot be deactivated.");
                        } else {
                            if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                                Plugin targetPlugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
                                Bukkit.getPluginManager().disablePlugin(targetPlugin);
                                sender.sendMessage("§7〔§bManage§ePlugins§7〕§7[§6" + pluginName + "§7] §aPlugin unloaded.");
                            } else {
                                sender.sendMessage("§7〔§bManage§ePlugins§7〕§cPlugin '" + pluginName + "' does not exist.");
                            }
                        }
                    }
                    break;
                case "list":
                    sender.sendMessage("§b§lList of Plugins:");
                    List<Plugin> plugins = List.of(Bukkit.getServer().getPluginManager().getPlugins());
                    for (Plugin plugin : plugins) {
                        String pluginName = plugin.getName();
                        boolean isEnabled = plugin.isEnabled();
                        String status = isEnabled ? " §7(§aActivated§7)" : " §7(§cDeactivated§7)";
                        sender.sendMessage("- " + pluginName + status);
                    }
                    break;
                default:
                    sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug list §7- list of plugins");
                    sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug reload <plugin> §7- reloads a plugin");
                    sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug load <plugin> §7- loads a plugin");
                    sender.sendMessage("§7〔§bManage§ePlugins§7〕§6/mgplug unload <plugin> §7- unloads a plugin");
                    break;
            }
        }

        return true;
    }
}