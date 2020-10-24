
package pet.orca.bukkit.simplewarp;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import com.google.common.collect.Sets;

import java.util.Set;

import lombok.experimental.Delegate;

public class MaterialFamily implements Set<Material> {

	@Delegate
	private final Set<Material> materials;

	public MaterialFamily(Set<Material> materials) {
		this.materials = Sets.immutableEnumSet(materials);
	}

	public MaterialFamily(Material ... materials) {
		this.materials = Sets.immutableEnumSet(materials[0], materials);
	}

	public boolean hasMaterial(Block block) {
		return contains(block.getType());
	}

	public boolean hasMaterial(BlockState block) {
		return contains(block.getType());
	}

	public static final MaterialFamily BUTTONS = new MaterialFamily(
		Material.ACACIA_BUTTON,
		Material.BIRCH_BUTTON,
		Material.CRIMSON_BUTTON,
		Material.DARK_OAK_BUTTON,
		Material.JUNGLE_BUTTON,
		Material.OAK_BUTTON,
		Material.POLISHED_BLACKSTONE_BUTTON,
		Material.SPRUCE_BUTTON,
		Material.STONE_BUTTON,
		Material.WARPED_BUTTON
	);

	public static final MaterialFamily PRESSURE_PLATES = new MaterialFamily(
		Material.ACACIA_PRESSURE_PLATE,
		Material.BIRCH_PRESSURE_PLATE,
		Material.CRIMSON_PRESSURE_PLATE,
		Material.DARK_OAK_PRESSURE_PLATE,
		Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
		Material.JUNGLE_PRESSURE_PLATE,
		Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
		Material.OAK_PRESSURE_PLATE,
		Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
		Material.SPRUCE_PRESSURE_PLATE,
		Material.STONE_PRESSURE_PLATE,
		Material.WARPED_PRESSURE_PLATE
	);

	public static final MaterialFamily RAILS = new MaterialFamily(
		Material.ACTIVATOR_RAIL,
		Material.DETECTOR_RAIL,
		Material.POWERED_RAIL,
		Material.RAIL
	);

	public static final MaterialFamily SIGNS = new MaterialFamily(
		Material.ACACIA_SIGN,
		Material.ACACIA_WALL_SIGN,
		Material.BIRCH_SIGN,
		Material.BIRCH_WALL_SIGN,
		Material.CRIMSON_SIGN,
		Material.CRIMSON_WALL_SIGN,
		Material.DARK_OAK_SIGN,
		Material.DARK_OAK_WALL_SIGN,
		Material.JUNGLE_SIGN,
		Material.JUNGLE_WALL_SIGN,
		Material.OAK_SIGN,
		Material.OAK_WALL_SIGN,
		Material.SPRUCE_SIGN,
		Material.SPRUCE_WALL_SIGN,
		Material.WARPED_SIGN,
		Material.WARPED_WALL_SIGN
	);
}
