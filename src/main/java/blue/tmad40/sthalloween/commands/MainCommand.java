
package blue.tmad40.sthalloween.commands;

import blue.tmad40.sthalloween.Config;
import blue.tmad40.sthalloween.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MainCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("sth")) { // Start sth command
			if (sender instanceof Player) { // Check if the sender is a player

				Player player = (Player) sender;

				if (args.length > 0) { // Check if the argument length is greater than 0

					if (args[0].equalsIgnoreCase("reload")) { // Reload Command
						if (player.hasPermission("sthalloween.reload")) { // Check if player has permission to use this command

							// Reload the config, send a message
							Main.getInstance().saveDefaultConfig();
							Main.getInstance().reloadConfig();
							Config.getInstance().loadFiles();

							String prefix = Main.getInstance().getMessagesString("prefix.server");

							sender.sendMessage(prefix + "Config reloaded");

							return true;

						} else {

							// Send an error message
							String prefix = Main.getInstance().getMessagesString("prefix.server");
							String noPerms = Main.getInstance().getMessagesString("messages.no-perms");

							sender.sendMessage(prefix + noPerms);

							return true;

						}

					} else { // If the argument doesn't match any of the options

						// Send an error message
						String prefix = Main.getInstance().getMessagesString("prefix.server");
						String invalidArg = Main.getInstance().getMessagesString("messages.invalid-arg").replaceAll("%arg%", args[0]).replaceAll("%args%", "<tips|ads>");

						sender.sendMessage(prefix + invalidArg);

						return true;

					}
				} else { // If there are no arguments

					// Send an error message
					String prefix = Main.getInstance().getMessagesString("prefix.server");
					String noArgs = Main.getInstance().getMessagesString("messages.no-args").replaceAll("%args%", "<tips|ads>");

					sender.sendMessage(prefix + noArgs);

					return true;

				}
			} else { // If the sender isn't a player, IE console
				if (args.length > 0) { // Check if the argument length is greater than 0
					if (args[0].equalsIgnoreCase("reload")) { // Reload Command

						// Reload the config, send a message
						Main.getInstance().reloadConfig();
						Config.getInstance().loadFiles();

						Main.getInstance().getLogger().info("Config reloaded");

						return true;

					} else { // If the argument doesn't match any of the options

						// Send an error message
						String invalidArg = Main.getInstance().getMessagesString("messages.invalid-arg").replaceAll("%arg%", args[0]).replaceAll("%args%", "<reload>");

						Main.getInstance().getLogger().info(invalidArg);

						return true;

					}
				} else { // If there are no arguments

					// Send an error message
					String noArgs = Main.getInstance().getMessagesString("messages.no-args").replaceAll("%args%", "<reload>");

					Main.getInstance().getLogger().info(noArgs);

					return true;

				}
			}
		}

		return false;

	}
}
