package util;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;
/**
 * 
 * Class used to transfer information from a resource file
 * into a list
 * 
 * @author maddiebriere
 *
 */

public class ResourceToList {

	public static void addTerms(String resource, List<Entry<String, Pattern>> list) {
		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			list.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
}
