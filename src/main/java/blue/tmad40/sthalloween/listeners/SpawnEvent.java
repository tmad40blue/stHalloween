
package blue.tmad40.sthalloween.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;


public class SpawnEvent implements Listener {

	@EventHandler
	public void onSpawnEvent(CreatureSpawnEvent event) {

		if (event.getEntityType() == EntityType.ZOMBIE) {

			event.getEntity().getEquipment().setHelmet(new ItemStack(Material.PUMPKIN));

		} else if (event.getEntityType() == EntityType.SKELETON) {

			event.getEntity().getEquipment().setHelmet(new ItemStack(Material.PUMPKIN));

		}

	}

}
