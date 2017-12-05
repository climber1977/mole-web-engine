package com.mole.webengine.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
	private Properties _pro;
	
	public MyProperties(String path) throws IOException{
		_pro = new Properties();
		FileInputStream fInputStream = new FileInputStream(path); 
		
		_pro.load(fInputStream);
	}
	public String getString(String key) {
		return _pro.getProperty(key);
    }
	
	 public String getString(String key, String defaultValue) {
		 return _pro.getProperty(key, defaultValue);
    }
}
