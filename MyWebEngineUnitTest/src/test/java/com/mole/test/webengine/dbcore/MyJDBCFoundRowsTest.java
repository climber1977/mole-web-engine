package com.mole.test.webengine.dbcore;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;


public class MyJDBCFoundRowsTest {
	
	final Logger logger = LoggerFactory.getLogger(MyJDBCFoundRowsTest.class);
	private static MyJDBC _db = null;
	public static void testQuery(String sql) throws Exception{
		_db.Connection();
		System.out.println("TestQuery");
		ResultSet rs = _db.executeQuery(sql);
		while(rs.next()){
			System.out.println("query " + rs.getObject(1));
		}
		rs = _db.executeQuery("SELECT FOUND_ROWS()");
		while(rs.next()){
			System.out.println("rows " + rs.getObject(1));
		}
		_db.DisconnectDB();
	}
	
	public static  void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("hr");
		
		if(!_db.isMysql()){
			System.out.println("the instance is for mysql");	
			return;
		}
		DBIniter.initPersonelMainXiyouji(_db);
		
		String sql = "select * from wb_user LIMIT 0 , 10";
		testQuery(sql);
		System.out.println("");	
		
		sql = "select SQL_CALC_FOUND_ROWS  * from wb_user LIMIT 0 , 10";
		testQuery(sql);
		System.out.println("");	
		
		System.out.println("finish");	
	}
}