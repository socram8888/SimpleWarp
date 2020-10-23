
package pet.orca.bukkit.simplewarp;

public class NotAWarpSignException extends InvalidWarpSignException {
	public NotAWarpSignException() {
		super();
	}

	public NotAWarpSignException(String desc) {
		super(desc);
	}
}
