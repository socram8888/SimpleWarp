
package ti.s4x8.bukkit.simplewarp;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import lombok.Getter;

public class SWPlugin extends JavaPlugin {
	@Getter private Permission createPermission = new Permission("simplewarp.create", "Allows creation of simple warps", PermissionDefault.OP);
	
	public void onEnable() {
		new SignListener(this);
		new EntityListener(this);
	};
};
