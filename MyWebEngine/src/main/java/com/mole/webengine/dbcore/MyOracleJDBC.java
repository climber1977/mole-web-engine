package com.mole.webengine.dbcore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MyOracleJDBC  extends MyJDBC{
	public MyOracleJDBC(DataSource ds, int dbtype, String dbs) {
		super(ds, dbtype, dbs);
	}

	protected String getGetColumnsSql(String tableName){
		return "select * from user_tab_columns where table_name = '" + tableName.toUpperCase() + "'";
	}
	
	protected String getColumnTypeName(MyResult rs, int row) throws Exception{
		return rs.getString(row, "DATA_TYPE");
	}
	
	public String getPagingSql(String sql, int startPageIndex, Integer pageCount){
		sql = String.format("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (%s) A  WHERE ROWNUM < %d) WHERE RN >= %d", 
				sql, startPageIndex+pageCount, startPageIndex);
		return sql;
	}
	
	public String constructSelectSql(String tableName, String sqlFields){
		return  String.format("select %s from %s", sqlFields, tableName); 	
	}
	
	public String getFoundRowsSql(String sql){
		return String.format("SELECT COUNT(*) FROM (%s)", sql);
	}
	
	public String addCalcFoundRowsOfPaginSql(String sql){
		setFoundRowsSql(sql);
		return sql;
	}
	
	public void truncateTable(String tableName) throws Exception{
		
		String sql =  "delete from " + tableName;
		
		executeUpdate(sql);
	}
	protected Object getValue(ResultSet rs, MyDBField field, MyResultSetMetaData rsm) throws SQLException{
		int columnIndex = field.getIndex()+1;
		if(field.getColumnLabel().equalsIgnoreCase("children")){
			return rs.getBoolean(columnIndex);  
		} else if(field.getColumnClassName().equals("java.sql.Timestamp") ||
				field.getColumnClassName().equals("oracle.sql.TIMESTAMPLTZ") ){
			return rs.getTimestamp(columnIndex);
		} else {
			return rs.getObject(columnIndex);  
		}
	}
	@Override
	public String getSingleQuoteTransfer() throws Exception {
		return "''";
	}
}
