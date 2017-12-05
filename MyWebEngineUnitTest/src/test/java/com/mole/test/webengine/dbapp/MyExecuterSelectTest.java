package com.mole.test.webengine.dbapp;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.MyExecuter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.utils.MyParams;


public class MyExecuterSelectTest {
	private static MyJDBC _db;
	
	public static void TestSelectAnd() throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();
		params.put("workcode", "CY000001");
		params.put("id", 1);
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_where_and", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	
	public static void TestSelectAnd2() throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();
		params.put("id", 1);
		params.put("name", "张");
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_where_and2", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	public static void TestSelectAnd3() throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();
		params.put("name", "张");
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_where_and2", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	public static void TestSelectOr() throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();
		params.put("workcode", "CY000002");
		params.put("id", 1);
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_where_or", false, false, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
	}
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("system");		
		DBIniter.initPersonelMainTest(_db);
		System.out.println();
		
		TestSelectAnd();		
		System.out.println();
		
		TestSelectAnd2();
		System.out.println();
		
		TestSelectAnd3();
		System.out.println();
		
		TestSelectOr();
		System.out.println();
		
		System.out.println("finish");	
	}
}