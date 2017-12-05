package com.mole.test.webengine.dbcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

public class MyJDBCOracleDateTimeTest {
	
	public static void getDateColumn(Statement stmt, ResultSet rs, String sql) throws SQLException {
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsm = rs.getMetaData();
		while (rs.next()) {		
			for(int i=0; i<rsm.getColumnCount(); i++){
				int column = i+1;
				System.out.println("getColumnClassName = " + rsm.getColumnClassName(column));	
				System.out.println("getColumnType = " + rsm.getColumnType(column));
				System.out.println("getColumnTypeName = " + rsm.getColumnTypeName(column));
				System.out.println("getColumnName = " + rsm.getColumnName(column));
				System.out.println("getColumnLabel = " + rsm.getColumnLabel(column));
				
				Object o = rs.getObject(column);
				System.out.println("getObject = " + o.toString());
				
				if(rsm.getColumnClassName(column).equals("java.sql.Timestamp")){
//					java.sql.Timestamp tt1 = (java.sql.Timestamp)o;
//					System.out.println("(java.sql.Timestamp)o = " + tt1.toString());
				}
				if(rsm.getColumnClassName(column).equals("java.lang.Long") ||
					rsm.getColumnClassName(column).equals("java.math.BigDecimal") ||
					rsm.getColumnClassName(column).equals("java.lang.String")){
					System.out.println();
					continue;
				}
				if(!rsm.getColumnClassName(column).equals("oracle.sql.TIMESTAMPLTZ")){
					Object d = rs.getDate(column);
					System.out.println("getDate = " + d.toString());
					Object t = rs.getTime(column);
					System.out.println("getTime = " + t.toString());
					Object tt2 = rs.getTimestamp(column);
					System.out.println("getTimestamp = " + tt2.toString());
				} else {
					//String tzString1 = "America/Dawson";  
					String tzString1 = "GMT";  //格林尼治时间
					//String tzString1 = "ETC/GMT-8";  北京时间
					Calendar c = Calendar.getInstance(TimeZone.getTimeZone(tzString1));  
					Object d = rs.getDate(column, c);
					System.out.println("getDate  " + tzString1 + "= " + d.toString());
					Object t = rs.getTime(column, c);
					System.out.println("getTime " + tzString1 + "= " + t.toString());
					Object tt = rs.getTimestamp(column, c);
					System.out.println("getTimestamp " + tzString1 + "= " + tt.toString());
				}
				
				System.out.println();
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
			
			String sql = "select * from test_field_type";
			getDateColumn(stmt, rs, sql);
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
