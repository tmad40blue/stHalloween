
package blue.tmad40.sthalloween.listeners;

import blue.tmad40.sthalloween.Main;
import org.bukkit.Effect;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class DamageByEntityEvent implements Listener {

	@EventHandler
	public void onDamageByEntityEvent(EntityDamageByEntityEvent event) {

		if (Main.getInstance().getConfig().getBoolean("bats.enabled")) {

			Entity entity = event.getEntity();

			if (event.getDamager() instanceof Player) {
				if (entity.getType() == EntityType.ZOMBIE) {

					Bat bat1 = entity.getWorld().spawn(entity.getLocation(), Bat.class);
					Bat bat2 = entity.getWorld().spawn(entity.getLocation(), Bat.class);
					Bat bat3 = entity.getWorld().spawn(entity.getLocation(), Bat.class);

					bat1.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));
					bat2.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));
					bat3.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));

					// Remove the bats after 6 seconds
					// TODO Make the time configurable
					new BukkitRunnable() {

						@Override
						public void run() {

							bat1.getWorld().playEffect(bat1.getLocation(), Effect.SMOKE, 1);
							bat1.getWorld().playEffect(bat2.getLocation(), Effect.SMOKE, 1);
							bat1.getWorld().playEffect(bat3.getLocation(), Effect.SMOKE, 1);

							bat1.remove();
							bat2.remove();
							bat3.remove();

						}

					}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("bats.bat-duration") * 20);

				} else if (entity.getType() == EntityType.SKELETON) {

					Bat bat1 = entity.getWorld().spawn(entity.getLocation(), Bat.class);
					Bat bat2 = entity.getWorld().spawn(entity.getLocation(), Bat.class);
					Bat bat3 = entity.getWorld().spawn(entity.getLocation(), Bat.class);

					bat1.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));
					bat2.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));
					bat3.setNoDamageTicks(Main.getInstance().getConfig().getInt("bats.bat-invulnerable-time"));

					// Remove the bats after 6 seconds
					// TODO Make the time configurable
					new BukkitRunnable() {

						@Override
						public void run() {

							bat1.remove();
							bat2.remove();
							bat3.remove();

						}

					}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("bats.bat-duration") * 20);

				}

			}
		}

	}

}
