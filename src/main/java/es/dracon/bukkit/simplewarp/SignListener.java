
package es.dracon.bukkit.simplewarp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SignListener implements Listener {
	private SWPlugin plugin;

	public SignListener(SWPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true)
	public void onSignChange(SignChangeEvent event) {
		WarpSign sign = null;
		InvalidWarpSignException invalid = null;
		try {
			sign = new WarpSign(event.getLines());
		} catch (NotAWarpSignException e) {
			return;
		} catch (InvalidWarpSignException e) {
			invalid = e;
		}

		Player player = event.getPlayer();
		if (!player.hasPermission(plugin.getCreatePermission())) {
			player.sendMessage(ChatColor.RED + "You're not allowed to create warps");
			event.setCancelled(true);
			return;
		}

		if (invalid != null) {
			player.sendMessage(ChatColor.RED + "Error creating warp: " + invalid.getMessage());
			event.setCancelled(true);
			return;
		}

		player.sendMessage(ChatColor.GREEN + "Warp created!");
	}
}
