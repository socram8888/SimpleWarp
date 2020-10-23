
package pet.orca.bukkit.simplewarp;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.Material;

import lombok.Getter;

public class WarpSign {
	@Getter private String world = null;
	@Getter private boolean positionUndefined = true;
	@Getter private int x;
	@Getter private int y;
	@Getter private int z;

	public WarpSign(Block block) throws InvalidWarpSignException {
		Material signMaterial = block.getType();
		if (!Material.SIGN_POST.equals(signMaterial) && !Material.WALL_SIGN.equals(signMaterial)) {
			throw new NotAWarpSignException("Not a sign!");
		}
		Sign sign = (Sign) block.getState();
		fromLines(sign.getLines());
	}

	public WarpSign(String[] text) throws InvalidWarpSignException {
		fromLines(text);
	}

	private void fromLines(String[] text) throws InvalidWarpSignException {
		if (!"[simplewarp]".equalsIgnoreCase(text[0])) {
			throw new NotAWarpSignException("Invalid header");
		}

		String[] params = Utils.cleanString(text[1] + "\n" + text[2] + "\n" + text[3]).split(" ");

		boolean readWorld = false;
		boolean readPos = false;

		if (params.length == 1) {
			if (params[0].length() != 0) {
				readWorld = true;
			}
		} else if (params.length == 3) {
			readPos = true;
		} else if (params.length == 4) {
			readWorld = true;
			readPos = true;
		} else {
			throw new InvalidWarpSignException("Invalid amount of parameters");
		}

		int ptr = 0;
		if (readWorld) {
			world = params[0];
			ptr++;
		}

		if (readPos) {
			positionUndefined = false;
			try {
				x = Integer.parseInt(params[ptr + 0]);
				y = Integer.parseInt(params[ptr + 1]);
				z = Integer.parseInt(params[ptr + 2]);
			} catch (NumberFormatException e) {
				throw new InvalidWarpSignException("Invalid coords");
			}
		}
	}
}
