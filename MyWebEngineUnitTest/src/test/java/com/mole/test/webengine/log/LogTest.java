package com.mole.test.webengine.log;

import java.io.IOException;

import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	final Logger logger = LoggerFactory.getLogger(LogTest.class);
	int age;
	String name;
	Date birthday;
	
	private static org.apache.log4j.Logger logger2 = org.apache.log4j.Logger.getLogger(LogTest.class);
	
	public void setInfo(int age,String name){	
		this.age = age;
		this.name = name;
		Object[] obj = {this.age, this.name,new Date()};
		
		logger.debug("this is log of debug,age is {} and name is {} and birthday is {}",obj);
		logger.trace("this is log of trace,age is {} ",obj);
		logger.info("this is log of info, age is {}",obj);
		logger.warn("this is log of worn,age is {} and name is {}",obj);
		logger.error("this is log of error,age is {} and name is {}  and birthday is {} and shcool is {} ",obj);
		//slf4j记录日志时可以传递参数，一个{}对应obj数组中的一个值，最后打印的美誉trace跟debug，这跟默认设置的记录等级有关，默认为info。
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("start");
		
		PropertyConfigurator.configure("config/log4j.properties");
		
		LogTest log = new LogTest();
		log.setInfo(20, "unj");
		
		logger2.error("logger2 this is log of trace,age is {} ");
		
		System.out.println("finish");	
	}

}
