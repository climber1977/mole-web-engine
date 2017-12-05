package com.mole.webengine.dbcore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MyMysqlJDBC extends MyJDBC{
	public MyMysqlJDBC(DataSource ds, int dbtype, String dbs) {
		super(ds, dbtype, dbs);
	}

	protected String getGetColumnsSql(String tableName){
		return "show columns from " + tableName;
	}
	
	protected String getColumnTypeName(MyResult rs, int row) throws Exception{
		return rs.getString(row, "COLUMN_TYPE");
	}
	
	public String getPagingSql(String sql, int startPageIndex, Integer pageCount){
		sql += " LIMIT " + startPageIndex + "," + pageCount;
		return sql;
	}
	
	public String constructSelectSql(String tableName, String sqlFields){
		return String.format("select %s from %s", sqlFields, tableName); 
	}
	
	public String getFoundRowsSql(String sql){
		return "SELECT FOUND_ROWS()";
	}
	
	public String addCalcFoundRowsOfPaginSql(String sql){
		String[] strs = sql.split("\\s+");
		if(strs.length > 0 && strs[0].equalsIgnoreCase("select")){
			int i = 0;
			for(i=0; i<strs.length; i++){
				if(strs[i].equalsIgnoreCase("SQL_CALC_FOUND_ROWS")){
					return sql;
				}
			}
			if(i == strs.length){
				setFoundRowsSql(sql);
				sql = sql.replaceFirst(strs[0], "select SQL_CALC_FOUND_ROWS ");
			}
		}
		return sql;
	}
	
	public void truncateTable(String tableName) throws Exception{
		
		String sql =  "TRUNCATE " + tableName;
		
		executeUpdate(sql);
	}
	protected Object getValue(ResultSet rs, MyDBField field, MyResultSetMetaData rsm) throws SQLException{
		int columnIndex = field.getIndex()+1;
		if(field.getColumnLabel().equalsIgnoreCase("children")){
			return rs.getBoolean(columnIndex);  
		} else {
			return rs.getObject(columnIndex);  
		}
	}

	@Override
	public String getSingleQuoteTransfer() throws Exception {
		return "\\\\'";
	}
}
