package ro.robert.licenta.events.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	private Map<String,Object> map = new HashMap<>();
	
	public static Utils create() {
		return new Utils();
	}
	public static Utils create(String key, Object value) {
		return new Utils().add(key, value);
	}
	
	public Utils add(String key, Object value) {
		map.put(key, value);
		return this;
	}
	
	public Map<String,Object> build() {
		return map; 
	}
	

}
