package com.mole.test.webengine.dbapp;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.MyExecuter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.utils.MyParams;



public class MyExecuterPageTest {
	private static MyJDBC _db;
	
	public static int TestSelectPaget(int start, int length) throws Exception{
		MyParams params = new MyParams();
		MyExecuter ex = new MyExecuter();		
		params.put("orderField", "id");
		params.put("start", start);
		params.put("length", length);
		
		MyResult rs = ex.executeByUUID(_db, "unit_test_query_page", true, true, MyJDBC.RETURN_LIST_LIST, params);
		rs.print();
		return rs.getRowCount();
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("system");
		DBIniter.initPersonelMainXiyouji(_db);
		System.out.println();
		
		int index = 0;
		int pageCount = 10;
		index += TestSelectPaget(index, pageCount);
		System.out.println();
		
		index += TestSelectPaget(index, pageCount);
		System.out.println();
		
		index += TestSelectPaget(index, pageCount);
		System.out.println();	
		
		System.out.println("finish");	
	}
}