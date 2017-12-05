package com.mole.test.webengine.dbcore;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResultSetMetaData;


public class MyJDBCGetQueryColumnsTest {

	private static MyJDBC _db = null;
	
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		
		DBIniter.init();
		_db = MyDBPool.getMyJDBC("hr");
		
		MyResultSetMetaData rsm = _db.getQueryColumns("select * from wb_user");
		rsm.print();
		System.out.println("end");
		
		_db = MyDBPool.getMyJDBC("test");
		String sql = "";
		if(_db.isMysql()){
			sql = "select id, t.short_name as text, 'root' as type, (select if(count(t2.id)>=1, 1, 0) as haschild from depart t2 where t2.parentid = t.id) as children from depart t";
		} else {
			sql = "select t.short_name as text, 'root' as type, (case when (select count(t2.id) as haschild";
			sql += " from depart t2 where t2.parentid = t.id) > 0  then";
			sql += " 1 else 0";
			sql += " end) as children from depart t";
		}
		rsm = _db.getQueryColumns(sql);
		rsm.print();
		System.out.println("end");
		
	}
}
