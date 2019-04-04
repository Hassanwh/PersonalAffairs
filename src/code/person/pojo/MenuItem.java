package code.person.pojo;

import java.util.HashMap;
import java.util.Map;

public class MenuItem {
	public String title;
	public String url;
	private Map<String,String> properties = null;
	public Map<String, String> getProperties() {
		if (properties == null) return java.util.Collections.emptyMap();
		return java.util.Collections.unmodifiableMap(properties);
	}
	public void setProperty(String name, String value) {
		if (properties == null) {
			properties = new HashMap<String,String>();
			properties.put(name, value);
		}
	}
}
