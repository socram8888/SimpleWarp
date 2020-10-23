
package pet.orca.bukkit.simplewarp;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class EntityListener implements Listener {
	private SWPlugin plugin;

	public EntityListener(SWPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.PHYSICAL) return;
		scanTeleport(event.getPlayer(), event.getClickedBlock());
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onEntityInteract(EntityInteractEvent event) {
		scanTeleport(event.getEntity(), event.getBlock());
	}

	private void scanTeleport(Entity entity, Block plate) {
		if (!entity.isEmpty() || entity.isInsideVehicle()) return;

		Material plateMaterial = plate.getType();
		if (
			!Material.STONE_PLATE.equals(plateMaterial) &&
			!Material.WOOD_PLATE.equals(plateMaterial)
		) return;

		WarpSign sign;
		try {
			sign = new WarpSign(plate.getRelative(0, -2, 0));
		} catch (InvalidWarpSignException e) {
			return;
		}

		World world = null;
		String signWorld = sign.getWorld();
		if (signWorld != null) {
			world = entity.getServer().getWorld(signWorld);
			if (world == null) {
				return;
			}
		} else {
			world = entity.getWorld();
		}

		Location dest = null;
		if (sign.isPositionUndefined()) {
			dest = world.getSpawnLocation();
		} else {
			dest = new Location(world, ((double) sign.getX()) + 0.5D, (double) sign.getY(), ((double) sign.getZ()) + 0.5D);
		}

		Teleporter.teleport(entity, dest);
	}
}
