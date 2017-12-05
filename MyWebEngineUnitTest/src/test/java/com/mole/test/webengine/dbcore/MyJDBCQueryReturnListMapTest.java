package com.mole.test.webengine.dbcore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.utils.JsonUtils;


public class MyJDBCQueryReturnListMapTest {
	
	final Logger logger = LoggerFactory.getLogger(MyJDBCQueryReturnListMapTest.class);
	private static MyJDBC _db = null;
	public static void testQuery() throws Exception{
		System.out.println("TestQuery");
		MyResult rs = _db.queryReturnMap("select id, work_code, name, en_name, ch_name from wb_user");
		
		rs.print();
		System.out.println();
		
		String result = JsonUtils.entity2JString(rs.getResultObject(), true);
		System.out.println(result);
	}

	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("hr");
		
		DBIniter.initPersonelMainTest(_db);
		System.out.println();


		testQuery();
		
		System.out.println("finish");	
	}
}