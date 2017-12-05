package com.mole.test.webengine.dbcore;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;


class MyJDBCRemoveColumnTest {	
	private static void hideColumn() throws Exception{
		String sql = "select * from wb_user";
		
		MyJDBC mydb = MyDBPool.getMyJDBC("hr");
		MyResultSetMetaData rsm = mydb.getQueryColumns(sql);
		int enNameColumn = 5;
		rsm.setCanAccess(enNameColumn, false);		
		
		MyResult rs = mydb.queryReturnListList(sql);
		rs.print();
	}
	
	private static void hideColumn2() throws Exception{
		String sql = "select * from wb_user";
		
		MyJDBC mydb = MyDBPool.getMyJDBC("hr");
		MyResultSetMetaData rsm = mydb.getQueryColumns(sql);
		int enNameColumn = 5;
		rsm.setCanAccess(enNameColumn, false);
		
		
		MyResult rs = mydb.queryReturnMap(sql);
		rs.print();
	}
	
	private static void removeColumn() throws Exception{
		String sql = "select * from wb_user";

		MyJDBC mydb = MyDBPool.getMyJDBC("hr");
		MyResultSetMetaData rsm = mydb.getQueryColumns(sql);
		int enNameColumn = 5;
		rsm.setCanAccess(enNameColumn, false);
		rsm.setShow(enNameColumn, false);
		
		
		MyResult rs = mydb.queryReturnListList(sql);
		rs.print();
	}
	
	private static void removeColumn2() throws Exception{
		String sql = "select * from wb_user";

		MyJDBC mydb = MyDBPool.getMyJDBC("hr");
		MyResultSetMetaData rsm = mydb.getQueryColumns(sql);
		int enNameColumn = 5;
		rsm.setCanAccess(enNameColumn, false);
		rsm.setShow(enNameColumn, false);
		
		
		MyResult rs = mydb.queryReturnMap(sql);
		rs.print();
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		 
		DBIniter.init();
		hideColumn();
		System.out.println();
		
		hideColumn2();
		System.out.println();
		
		removeColumn();
		System.out.println();
		
		removeColumn2();
		System.out.println();
		
		System.out.println("end");
		
	}
}
