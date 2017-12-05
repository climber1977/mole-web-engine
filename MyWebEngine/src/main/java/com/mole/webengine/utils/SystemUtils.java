package com.mole.webengine.utils;

import java.util.Enumeration;
import java.util.Properties;

public class SystemUtils {
	public static boolean isTomcatApp(){
		Properties p = System.getProperties();
		return p.get("catalina.useNaming") != null;
	}
	
	public static void PrintProperties() {		
		Properties p = System.getProperties();
		Enumeration<Object> e = p.keys();
		while(e.hasMoreElements()){
			String key = e.nextElement().toString();
			System.out.println(key + ":" + p.getProperty(key));
		}
	}
	
	public static String getResourcePath(){
		return new SystemUtils().getClass().getResource("/").getPath();
	}
}
