
package blue.tmad40.sthalloween.listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import blue.tmad40.sthalloween.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blue.tmad40.sthalloween.Config;


public class InteractEvent implements Listener {

	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event) {

		if (Main.getInstance().getConfig().getBoolean("trick-or-treat.enabled")) {

			// Check if the block was right-clicked
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

				// Create variables about the event
				Player player = event.getPlayer();
				Block block = event.getClickedBlock();
				Material material = block.getType();

				// Check if the block was a door
				if (material == Material.WOODEN_DOOR || material == Material.ACACIA_DOOR || material == Material.BIRCH_DOOR || material == Material.DARK_OAK_DOOR
						|| material == Material.IRON_DOOR_BLOCK || material == Material.JUNGLE_DOOR || material == Material.SPRUCE_DOOR) {

					Location blockLocation = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ());
					Location blockLocationTop = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ());

					// Loop through the config
					for (String s : Main.getInstance().getConfig().getConfigurationSection("trick-or-treat.doors").getKeys(false)) {

						// Store the location from the config
						Location configLocation = new Location(Bukkit.getWorld((Main.getInstance().getConfig().getString("trick-or-treat.doors." + s + ".world"))),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".x"),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".y"),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".z"));
						Location spawnLocation = new Location(Bukkit.getWorld((Main.getInstance().getConfig().getString("trick-or-treat.doors." + s + ".world"))),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".spawn.x"),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".spawn.y"),
								Main.getInstance().getConfig().getDouble("trick-or-treat.doors." + s + ".spawn.z"));

						// Check if the locations match
						if (blockLocation.equals(configLocation) || blockLocationTop.equals(configLocation)) {

							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date = new Date();

							if (!(Config.getInstance().storage.getBoolean("players." + player.getName() + "." + dateFormat.format(date) + "." + s))) {

								double witchChance = Math.random() * 101;

								// 10% chance for a witch to spawn
								if (witchChance < Main.getInstance().getConfig().getInt("trick-or-treat.witch-chance")) {

									// Spawn a witch, make it invulnerable and to target the player
									Witch witch = block.getWorld().spawn(spawnLocation, Witch.class);
									witch.setNoDamageTicks(Main.getInstance().getConfig().getInt("trick-or-treat.mob-invulnerable-time") * 20);
									witch.setTarget(player);

									// Send a message
									Random random = new Random();
									List<String> messageList = Config.getInstance().messages.getStringList("trick-or-treat.tricks");
									String message = messageList.get(random.nextInt(messageList.size()));
									String processedMessage = ChatColor.translateAlternateColorCodes('&', message);

									player.sendMessage(processedMessage);

									// Create a list to store the rewards
									List<String> rewards = new ArrayList<>();

									// Go through the config, add a reward to the list depending on 'chance' value
									for (String r : Main.getInstance().getConfig().getConfigurationSection("trick-or-treat.tricks").getKeys(false)) {
										int x = Main.getInstance().getConfig().getInt("trick-or-treat.tricks." + r + ".chance");

										while (x > 0) {
											rewards.add(r);

											x--;
										}
									}

									// Pick a random reward from the list
									String reward = rewards.get(random.nextInt(rewards.size()));

									// Say what reward you got
									player.sendMessage(Main.getInstance().getMessagesString("trick-or-treat.trick"));

									// Run the reward command
									String command = Main.getInstance().getConfig().getString("trick-or-treat.tricks." + reward + ".command").replaceAll("%player%",
											event.getPlayer().getName());
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

									// Make some particles
									// TODO Make these particles appear periodically
									for (int x = 0; x < 3; x++) {
										block.getWorld().playEffect(witch.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
									}

									// Remove the witch
									new BukkitRunnable() {

										@Override
										public void run() {

											block.getWorld().playEffect(witch.getLocation(), Effect.SMOKE, 1);
											witch.remove();

										}

									}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("trick-or-treat.mob-duration") * 20);

									// TODO Close the door

								} else {

									// Spawn a villager. make it invulnerable
									// TODO Disable trading
									Villager villager = block.getWorld().spawn(spawnLocation, Villager.class);
									villager.setNoDamageTicks(Main.getInstance().getConfig().getInt("trick-or-treat.mob-invulnerable-time") * 20);
									villager.setTarget(player);

									// Send a message
									Random random = new Random();
									List<String> messageList = Config.getInstance().messages.getStringList("trick-or-treat.treats");
									String message = messageList.get(random.nextInt(messageList.size()));
									String processedMessage = ChatColor.translateAlternateColorCodes('&', message);

									player.sendMessage(processedMessage);

									// Create a list to store the rewards
									List<String> rewards = new ArrayList<>();

									// Go through the config, add a reward to the list depending on 'chance' value
									for (String r : Main.getInstance().getConfig().getConfigurationSection("trick-or-treat.treats").getKeys(false)) {
										int x = Main.getInstance().getConfig().getInt("trick-or-treat.treats." + r + ".chance");

										while (x > 0) {
											rewards.add(r);

											x--;
										}
									}

									// Pick a random reward from the list
									String reward = rewards.get(random.nextInt(rewards.size()));

									// Say what reward you got
									player.sendMessage(Main.getInstance().getMessagesString("trick-or-treat.treat").replaceAll("%prize%",
											Main.getInstance().getConfig().getString("trick-or-treat.treats." + reward + ".name")));

									// Run the reward command
									String command = Main.getInstance().getConfig().getString("trick-or-treat.treats." + reward + ".command").replaceAll("%player%",
											event.getPlayer().getName());
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

									// Remove the villager
									new BukkitRunnable() {

										@Override
										public void run() {

											block.getWorld().playEffect(villager.getLocation(), Effect.SMOKE, 1);
											villager.remove();

										}

									}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("trick-or-treat.mob-duration") * 20);

									// TODO Close the door

								}

								Config.getInstance().storage.set("players." + player.getName() + "." + dateFormat.format(date) + "." + s, true);
								Config.getInstance().saveFiles("storage");

							}

						}
					}
				}
			}
		}
	}
}
