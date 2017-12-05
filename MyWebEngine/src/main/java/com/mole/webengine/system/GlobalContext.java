package com.mole.webengine.system;

import java.util.HashMap;

public class GlobalContext {
	private static HashMap<String, Object> globals = new HashMap<String, Object>();
	
	public static void setGlobal(String key, Object obj) {
		globals.put(key, obj);
	}
	public static Object getGlobal(String key) {
		return globals.get(key);
	}
}
