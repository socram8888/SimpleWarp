
package ti.s4x8.bukkit.simplewarp;

public class NotAWarpSignException extends InvalidWarpSignException {
	public NotAWarpSignException() {
		super();
	};
	
	public NotAWarpSignException(String desc) {
		super(desc);
	};
};
