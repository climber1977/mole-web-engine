package com.mole.webengine.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

public class ClsTool {
	private static Class<?> getCls(String clsName) throws Exception{
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		Class<?> cla = classLoader.getClass();
		while (cla != ClassLoader.class)
			cla = cla.getSuperclass();
		Field field = cla.getDeclaredField("classes");
		field.setAccessible(true);
		
		Class<?> c = null;
		Vector<?> v = (Vector<?>) field.get(classLoader);
		for (int i = 0; i < v.size(); i++) {
			c = (Class<?>) v.get(i);
			if(c.getName().equals(clsName)){
				break;
			}
		}
		return c;
	}
	public static void call(String clsName, String funName, Object... args) throws Exception{
		Class<?> c = getCls(clsName);
		Class<?>[] cs = new Class<?>[args.length];
		for(int i=0; i<args.length; i++){
			cs[i] = args[i].getClass();
		}
		Method m = c.getDeclaredMethod(funName, cs);
		Object o = c.newInstance();
		m.invoke(o, args);
	}
}
