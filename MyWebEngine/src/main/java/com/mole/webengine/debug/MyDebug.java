package com.mole.webengine.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDebug {
	final static Logger logger = LoggerFactory.getLogger(MyDebug.class);
	public static String dealException(String clsName, String funName, Exception e, Object... args){
		StringBuffer result = new StringBuffer();
		
		logger.error(clsName + ":" + funName);
		for (Object v : args) {
			logger.error(v.toString() + " ");
			result.append(v.toString());
			result.append(" ");
        }
		logger.error(e.getMessage(), e);
		return result.append(e.getMessage()).toString();
	}
	
	public static void println(String clsName, String funName, Object... args){	
		System.out.print(clsName + "::" + funName + "-->");
		for (Object v : args) {
            System.out.print(v.toString() + " ");
        }
        System.out.println();
	}
	
	public static void println(Class<?> cls, String funName, Object... args){	
		println(cls.getName(), funName, args);
	}
	
	public static void println(Object obj, String funName, Object... args){	
		println(obj.getClass(), funName, args);
	}
}
