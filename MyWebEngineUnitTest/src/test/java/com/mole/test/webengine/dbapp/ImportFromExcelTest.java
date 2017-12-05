package com.mole.test.webengine.dbapp;

import org.apache.log4j.PropertyConfigurator;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.ImportToDB;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;


public class ImportFromExcelTest {
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		PropertyConfigurator.configure("config/log4j.properties");
		
		DBIniter.init();
		MyJDBC db = MyDBPool.getMyJDBC("hr");
		
		db.truncateTable("wb_user");
		ImportToDB im = new ImportToDB();
		im.importToDB("data/wb_user.xls", db);
		
		System.out.println("finish");	
	}
}