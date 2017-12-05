package com.mole.test.webengine.dbapp;

import com.mole.test.webengine.DBIniter;
import com.mole.webengine.dbapp.ExportToExcel;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;


public class ExportToExcelTest {
	public static void main(String args[]) throws Exception {
		System.out.println("start");
		DBIniter.init();
		
		MyJDBC _db = MyDBPool.getMyJDBC("hr");
		
		ExportToExcel ex = new ExportToExcel();
		String sql = "select work_code, ch_name, en_name from wb_user";
		ex.export("test/wb_user.xls", "data/export_template.xls", _db, sql);
		
		ex.exportTable("test/wb_user_table.xls", "data/export_template.xls", _db, "wb_user");
		
		ex.export2("test/wb_user2.xls", "data/wb_user_template.xls", _db, sql);
		
		ex.exportTable2("test/wb_user_table2.xls", "data/wb_user_template.xls", _db, "wb_user");
		
		System.out.println("finish");		
	}
}