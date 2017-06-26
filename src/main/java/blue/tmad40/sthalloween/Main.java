
package blue.tmad40.sthalloween;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import blue.tmad40.sthalloween.commands.MainCommand;
import blue.tmad40.sthalloween.listeners.DamageByEntityEvent;
import blue.tmad40.sthalloween.listeners.InteractEvent;
import blue.tmad40.sthalloween.listeners.SpawnEvent;


public class Main extends JavaPlugin {

	// Provide instance of Main class
	private static Main instance;

	public Main() {
		instance = this;
	}

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		Config.getInstance().loadFiles();

		// Register events
		getServer().getPluginManager().registerEvents(new InteractEvent(), this);
		getServer().getPluginManager().registerEvents(new SpawnEvent(), this);
		getServer().getPluginManager().registerEvents(new DamageByEntityEvent(), this);

		// Register commands
		this.getCommand("sth").setExecutor(new MainCommand());

		// Setup Metrics
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {

		}

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {

				if (getConfig().getBoolean("sounds.enabled")) {
					// Loop through online players
					for (Player player : getServer().getOnlinePlayers()) {
						player.playSound(player.getLocation(), "ambient.cave.cave", 1.0F, 1.0F);
					}
				}

			}
		}, 0, getConfig().getInt("sounds.frequency") * 20 * 60);

	}

	public String getMessagesString(String input) {
		return ChatColor.translateAlternateColorCodes('&', Config.getInstance().messages.getString(input));
	}
}
