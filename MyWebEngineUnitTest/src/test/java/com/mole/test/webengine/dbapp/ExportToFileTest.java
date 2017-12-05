package com.mole.test.webengine.dbapp;

import org.apache.log4j.PropertyConfigurator;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.ExportToFile;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;


public class ExportToFileTest {
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		PropertyConfigurator.configure("config/log4j.properties");
				
		DBIniter.init();
		MyJDBC _db = MyDBPool.getMyJDBC("hr");
		
		ExportToFile ex = new ExportToFile();
		String sql = "select work_code, ch_name, en_name from wb_user";
		ex.exportByGbk("test/wb_user.csv", _db, sql);
		
		ex.exportTableByGbk("test/wb_user_gbk.csv", _db, "wb_user");
		ex.exportTableByGbk("test/wb_user_gbk.txt", _db, "wb_user");
		ex.exportTableByUtf8("test/wb_user_utf8.txt", _db, "wb_user");
		
		System.out.println("finish");	
	}
}