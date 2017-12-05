package com.mole.test.webengine.dbcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;



public class MyJDBCTest {
	
	final Logger logger = LoggerFactory.getLogger(MyJDBCTest.class);
	private MyJDBC _db = null;
	public void testQuery() throws Exception{
		System.out.println("TestQuery");
		MyResult rs = _db.queryReturnListList("select id, work_code, name, en_name, ch_name from wb_user order by id");
		
		rs.print();
	}
	public void testUpdate() throws Exception{
		MyResult rs = _db.executeUpdate("update wb_user set en_name='tom' where id=1");
		rs.print();
	}
	
	public void testTransation() throws Exception{
		_db.beginTransaction();
		MyResult rs = _db.executeUpdate("update wb_user set en_name='zhangsan' where id=1");
		rs.print();
		rs = _db.executeUpdate("update wb_user set en_name='jack' where id=2");
		rs.print();
		_db.commit();
	}
	public void testUpdate2() throws Exception{
		MyResult rs = _db.executeUpdate("update wb_user set en_name='lisi' where id=2");
		rs.print();
	}
	
	public void testDB(String configName) throws Exception{
		System.out.println();
		System.out.println(configName);
		_db = MyDBPool.getMyJDBC("hr");
		
		DBIniter.initPersonelMainTest(_db);
		System.out.println();
		
		testQuery();
		System.out.println();
		
		testUpdate();
		System.out.println();
		
		testQuery();
		System.out.println();
		
		testTransation();
		System.out.println();
		
		testQuery();
		System.out.println();
		
		testUpdate2();
		System.out.println();
		
		testQuery();
		System.out.println();
	}
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		MyJDBCTest test = new MyJDBCTest();
		test.testDB("mysql_hrdb");
		//test.TestDB("oracle_hrdb");
		
		System.out.println("finish");	
	}
}