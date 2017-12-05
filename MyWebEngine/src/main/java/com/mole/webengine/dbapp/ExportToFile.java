package com.mole.webengine.dbapp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.utils.StringUtils;



public class ExportToFile {
	//导出到文件
	private boolean export(String path, MyJDBC db, String sql, String charsetName) throws Exception{
		MyResult rs = db.queryReturnListList(sql);
		MyResultSetMetaData rsmd = rs.getResultSetMetaData();
		
		FileOutputStream fis = new FileOutputStream(path); 
		OutputStreamWriter isr = new OutputStreamWriter(fis, charsetName); 
		BufferedWriter f = new BufferedWriter(isr);
		
		int columnCount = rs.getColumnCount();
		for(int i=0; i<columnCount; i++){
			f.write(rsmd.getColumnLabel(i));
			if(i < columnCount-1){
				f.write(",");
			}
		}
			
		f.write("\r\n");

		String val = "";
		String gbk = "";
		for(int i=0; i<rs.getRowCount(); i++){			
			for(int j=0; j<columnCount; j++){
				val = rs.getValue(i, j).toString();
				gbk = StringUtils.changeCharset(val, charsetName);
				f.write(gbk);
				if(j < columnCount-1){
					f.write(",");
				}
			}
			f.write("\r\n");
		}
		f.close();
		return true;
	}
	
	public boolean exportByGbk(String path, MyJDBC db, String sql) throws Exception{
		return export(path, db, sql, "GBK");
	}
	public boolean exportByUtf8(String path, MyJDBC db, String sql) throws Exception{
		return export(path, db, sql, "UTF-8");
	}
	public boolean exportTable(String path, MyJDBC db, String tableName, String charsetName) throws Exception{
		String sql = "select * from " + tableName;
		return export(path, db, sql, charsetName);
	}
	public boolean exportTableByGbk(String path, MyJDBC db, String tableName) throws Exception{
		return exportTable(path, db, tableName, "GBK");
	}
	public boolean exportTableByUtf8(String path, MyJDBC db, String tableName) throws Exception{
		return exportTable(path, db, tableName, "UTF-8");
	}
}