
package pet.orca.bukkit.simplewarp;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Utils {
	private static final Pattern MULTISPACE = Pattern.compile("[ \n\t]+");
	private static final Pattern SPECIAL = Pattern.compile("§[0-9a-fA-Fk-rK-R]");

	public static final Class[] getClassStack(Class clazz) {
		ArrayList<Class> classes = new ArrayList<Class>(16);
		while (clazz != null) {
			classes.add(clazz);
			clazz = clazz.getSuperclass();
		}
		return classes.toArray(new Class[classes.size()]);
	}

	public static final String cleanString(String text) {
		text = text.trim();
		text = MULTISPACE.matcher(text).replaceAll(" ");
		text = SPECIAL.matcher(text).replaceAll("");
		return text;
	}
}
