package com.mole.test.webengine;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.log4j.PropertyConfigurator;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mole.webengine.dbapp.ImportToDB;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.utils.SystemUtils;


public class DBIniter {
	
	private static HashMap<String, DataSource> getDBPools(){
		HashMap<String, DataSource> pools = new HashMap<String, DataSource>();
		ComboPooledDataSource ds = null;
		ds = new ComboPooledDataSource("web_engine");	
		pools.put("system", ds);
		pools.put("hr", ds);
		ds = new ComboPooledDataSource("web_test");	
		pools.put("test", ds);
		ds = new ComboPooledDataSource("oa");
		pools.put("oa", ds);
		ds = new ComboPooledDataSource("erp");
		pools.put("erp", ds);
		
		return pools;
	}
	
	public static void init() {
		PropertyConfigurator.configure("config/log4j.properties");
		
		try {
			//当前运行目录的，的相对目录
			String path = "config/dbconfig.xml";
			if(SystemUtils.isTomcatApp()){
				path = SystemUtils.getResourcePath() + path;
			} 
			System.setProperty("com.mchange.v2.c3p0.cfg.xml",path);
			
			HashMap<String, DataSource> pools = getDBPools();
			
			MyDBPool.init(pools);
			
		} catch (Exception e) {
			MyDebug.dealException("MyController", "init", e);
		}
	}
	
	public static void importData(MyJDBC db, String tableName, String path) throws Exception{
		db.truncateTable(tableName);
		ImportToDB im = new ImportToDB();	
		im.importToDB(path, db);
	}
	
	public static void initPersonelMainTest(MyJDBC db) throws Exception{
		importData(db, "wb_user", "data/wb_user.xls");
	}
	
	public static void initPersonelMainXiyouji(MyJDBC db) throws Exception{
		importData(db, "wb_user", "data/wb_user_西游记.xls");
	}
}
