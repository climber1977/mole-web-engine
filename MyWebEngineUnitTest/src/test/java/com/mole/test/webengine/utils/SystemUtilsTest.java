package com.mole.test.webengine.utils;

import com.mole.webengine.utils.SystemUtils;

public class SystemUtilsTest {
	
	public void getPath(){
		System.out.println(this.getClass().getResource("/"));
	}
	
	public static void main(String args[]) throws Exception {
		SystemUtilsTest c = new SystemUtilsTest();
		c.getPath();
		
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		System.out.println();
		
		SystemUtils.PrintProperties();
		System.out.println();
		
		System.out.println(SystemUtils.isTomcatApp());
		System.out.println();
		
		System.out.println(SystemUtils.getResourcePath());
		System.out.println();
		
	}
}
