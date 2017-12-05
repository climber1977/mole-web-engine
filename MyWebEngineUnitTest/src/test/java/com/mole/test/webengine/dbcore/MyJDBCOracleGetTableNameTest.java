package com.mole.test.webengine.dbcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyJDBCOracleGetTableNameTest {
	
	public static void GetTableName(Statement stmt, ResultSet rs, String sql) throws SQLException {
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsm = rs.getMetaData();
		System.out.println("getColumnClassName = " + rsm.getColumnClassName(1));	
		System.out.println("getColumnTypeName = " + rsm.getColumnTypeName(1));
		System.out.println("getColumnName = " + rsm.getColumnName(1));
		System.out.println("getColumnLabel = " + rsm.getColumnLabel(1));
		System.out.println("getTableName = " + rsm.getTableName(1));
		System.out.println("getTableName = " + rsm.getTableName(0));
		System.out.println("getCatalogName = " + rsm.getCatalogName(1));
		System.out.println("getSchemaName = " + rsm.getSchemaName(1));
		
		System.out.println();	
		while (rs.next()) {	
			Object value = rs.getObject(2);
			System.out.println("value=" + value);	
		}
	}
	public static void main(String[] args) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// new oracle.jdbc.driver.OracleDriver();
			
			Properties info = new java.util.Properties(); 
			info.put ("user","root");   
			info.put ("password","123456"); 
			info.put ("ResultSetMetaDataOptions","1");
			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@10.1.9.18:1521:itdev11g", "root", "123456");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.9.18:1521:itdev11g", info);
			stmt = conn.createStatement();
			
			GetTableName(stmt, rs, "select wb_user.id, wb_user.name from wb_user where id<2");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
