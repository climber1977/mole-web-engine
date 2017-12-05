package com.mole.test.webengine.dbapp;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.MyExecuter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.utils.MyParams;


public class MyExecuterUpdateTest {
	private static MyJDBC _db;
	
	public static void TestSelect() throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();		
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_where_and", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	
	public static void TestUpdate() throws Exception{
		MyParams params = new MyParams();
		params.put("id", 1);
		params.put("name", "jack");
		params.put("en_name", "jack");
		
		MyExecuter ex = new MyExecuter();		
		MyResult rs = ex.executeByUUID(_db, "unit_test_update", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	
	public static void TestUpdate2() throws Exception{
		MyParams params = new MyParams();
		params.put("id", "1");
		params.put("work_code", "CY000001");
		params.put("name", "张三");
		params.put("en_name", "zhangsan");
		
		MyExecuter ex = new MyExecuter();		
		MyResult rs = ex.executeByUUID(_db, "unit_test_update2", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("system");
		DBIniter.initPersonelMainTest(_db);
		System.out.println();
		
		TestSelect();		
		System.out.println();
		
		TestUpdate();
		TestSelect();
		System.out.println();
		
		TestUpdate2();
		TestSelect();	
		System.out.println();
		
		System.out.println("finish");	
	}
}