package com.mole.test.webengine.dbcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBCOracleBooleanTest {
	
	public static void ToNumber1(Statement stmt, ResultSet rs, String sql) throws SQLException {
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsm = rs.getMetaData();
		System.out.println("getColumnClassName = " + rsm.getColumnClassName(1));	
		System.out.println("getColumnType = " + rsm.getColumnType(1));
		System.out.println("getColumnTypeName = " + rsm.getColumnTypeName(1));
		System.out.println("getColumnName = " + rsm.getColumnName(1));
		System.out.println("getColumnLabel = " + rsm.getColumnLabel(1));
		System.out.println("getPrecision = " + rsm.getPrecision(1));
		System.out.println("getColumnDisplaySize = " + rsm.getColumnDisplaySize(1));
		while (rs.next()) {		
			
			if(rsm.getColumnName(1).equalsIgnoreCase("children")){
				boolean value = rs.getBoolean(1);
				System.out.println("value=" + value);	
			} else{
				Object value = rs.getObject(1);
				System.out.println("value=" + value);			
			}
						
		}
	}
	public static void main(String[] args) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.9.18:1521:itdev11g", "root", "123456");
			stmt = conn.createStatement();
			
			ToNumber1(stmt, rs, "select  To_number(1, '9') as b from dual");
			System.out.println();
			ToNumber1(stmt, rs, "select  To_number(1, '9') as children from dual");
			System.out.println();
			
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
