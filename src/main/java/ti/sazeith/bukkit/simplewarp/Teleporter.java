
package ti.sazeith.bukkit.simplewarp;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

import java.lang.reflect.Method;

public final class Teleporter {
	public static final void teleport(Entity entity, Location dest) {
		if (entity instanceof Player || dest.getWorld().equals(entity.getWorld())) {
			entity.teleport(dest);
			return;
		}

		Server server = entity.getServer();
		try {
			Method nmsEntityGetter = entity.getClass().getMethod("getHandle");
			Object nmsEntity = nmsEntityGetter.invoke(entity);

			Method nmsPlayerListGetter = server.getClass().getMethod("getHandle");
			Object nmsPlayerList = nmsPlayerListGetter.invoke(server);

			Class[] entityClasses = Utils.getClassStack(nmsEntity.getClass());
			Method repositionEntity = nmsPlayerList.getClass().getMethod("repositionEntity", entityClasses[entityClasses.length - 2], Location.class, boolean.class);

			dest.getChunk();
			repositionEntity.invoke(nmsPlayerList, nmsEntity, dest, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
