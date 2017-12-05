package com.mole.webengine.dbcore;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Types;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.webengine.debug.MyException;
import com.mole.webengine.utils.StringUtils;


public abstract class MyJDBC {
	private static final Logger logger = LoggerFactory.getLogger(MyJDBC.class);
	
	private Connection _con;
	private Statement _statement;	
	private boolean _isOutCon = false;
	
	//当期是表格查询，或需要安全控制时，从外面设置用于控制每一列的显示效果
	Method getColumnPrivilegeMethod = null;
	boolean isShowColumn = true;	
	private String HIDE_COLUMN_CHAR = "******";
	
	private boolean addPagingSql = false;	//是否添加过了分页Sql
	private String foundRowsSql = "";
	public void setFoundRowsSql(String sql) {
		String foundRowsSql = getFoundRowsSql(sql);
		
		this.foundRowsSql = StringUtils.modifySql(foundRowsSql);
	}
	public void setInitColumnPrivilegeMethod(boolean isShowColumn, Method m){
		this.isShowColumn = isShowColumn;
		this.getColumnPrivilegeMethod = m;
	}
	
	public static final int DB_TYPE_MYSQL = 0;
	public static final int DB_TYPE_ORACLE = 1;
	
	public static final int SELECT_SQL = 0;
	public static final int UPDATE_SQL = 1;
	
	public static final int RETURN_INT = 0;
	public static final int RETURN_LIST_LIST = 1;
	public static final int RETURN_LIST_MAP = 2;
	
	private DataSource _ds = null;  
	private int _dbtype = 0;
	private String _dbs = "";

	public String getDBS(){
		return _dbs;
	}
	public DataSource getDataSource(){
		return _ds;
	}
	
	public MyJDBC(DataSource ds, int dbtype, String dbs){
		this._ds = ds;
		this._dbtype = dbtype;
		this._dbs = dbs;
	}
	//内部链接
	private void InConnection() throws Exception { 
	   if(_con != null){
		   return;
	   }	  
	   try{
		   _con = _ds.getConnection();
	   }catch (Exception e) {
		   throw new MyException(e, "MyJDBC::InConnection-->dbs=", _ds.toString());
		}
	   _statement = _con.createStatement();
	}  
	//链接到数据库
	public void Connection() throws Exception {		
		InConnection(); 
		_isOutCon = true;
	}  
	//断开数据库连接
	private void InDisconnectDB() throws Exception{		
		if(_con == null){
			return;
		}
		if(!_isOutCon){
			_statement.close();
			_statement = null;
			_con.close();
			_con = null;
		}
	}
	//断开数据库连接
	public void DisconnectDB() throws Exception{
		_isOutCon = false;
		InDisconnectDB();
	}
	
	//执行查询Sql
	public ResultSet executeQuery(String sql) throws Exception{
		logger.info("executeQuery:" + sql);
		try {
			return _statement.executeQuery(sql);
		} catch (SQLException e) {
			throw new MyException(e, "MyJDBC::executeQuery-->", sql, 
					"\r\ndbs=", _ds.toString());
		}
	}	
	private void setResultSetMetaData(MyResult myResult, ResultSet rs) throws Exception{
		//取得列信息
		MyResultSetMetaData myrsmd =  new MyResultSetMetaData();
		ResultSetMetaData rsmd = rs.getMetaData() ; 
		int columnCount = rsmd.getColumnCount();
		for(int i=0; i<columnCount; i++){
			MyDBField field = new MyDBField();
			field.setIndex(i);
			field.setColumnName(rsmd.getColumnName(i+1));	
			field.setColumnLabel(rsmd.getColumnLabel(i+1));	
			field.setTableName(rsmd.getTableName(i+1));				
			field.setColumnType(rsmd.getColumnType(i+1));
			field.setColumnTypeName(rsmd.getColumnTypeName(i+1));
			field.setColumnClassName(rsmd.getColumnClassName(i+1));			
			
			myrsmd.addColumn(field);
		}
		if(getColumnPrivilegeMethod != null){
			myrsmd = (MyResultSetMetaData)getColumnPrivilegeMethod.invoke(null, isShowColumn, myrsmd);
		}
		myResult.setResultSetMetaData(myrsmd);
	}
	protected abstract Object getValue(ResultSet rs, MyDBField field, MyResultSetMetaData rsm) throws SQLException;
	
	private Object getValue(MyResult myResult, ResultSet rs, MyDBField field) throws SQLException{		
		Object obj = null;
		MyResultSetMetaData rsm = myResult.getResultSetMetaData();
		if(!field.isCanAccess()){
			if(!field.isShow()){
				return null;
			} else {
				obj = HIDE_COLUMN_CHAR;  
			}
		} else {
			obj = getValue(rs, field, rsm);
		}
		return obj;
	}

	private int setReturnListList(MyResult myResult, ResultSet rs) throws Exception{
		@SuppressWarnings("unchecked")
		List<List<Object>> myrs =  (List<List<Object>>)myResult.getResultObject();
		
		int column = myResult.getColumnCount();		
		MyResultSetMetaData rsm = myResult.getResultSetMetaData();
		
    	while(rs.next()){
    		List<Object> row = new ArrayList<Object>();    		
    		for(int i=0; i<column; i++){
    			if(!rsm.isShow(i)){
    				continue;
    			}
    			Object obj = getValue(myResult, rs, rsm.getColumn(i));
    			row.add(obj);
    		}
    		myrs.add(row);
    	}
    	return myrs.size();
	}
	private int setReturnListMap(MyResult myResult, ResultSet rs) throws Exception{
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> myrs =  (List<HashMap<String, Object>>)myResult.getResultObject();
		
		String columnName = "";
    	while(rs.next()){   
    		HashMap<String, Object> m = new HashMap<String, Object>();
    		for(int col=0; col<myResult.getColumnCount(); col++){
    			if(!myResult.getResultSetMetaData().isShow(col)){
    				continue;
    			}
    			Object obj = getValue(myResult, rs, myResult.getResultSetMetaData().getColumn(col));
    			if(obj == null){
    				continue;
    			}  
    			
    			columnName = myResult.getColumnLabel(col);    				
    			
    			m.put(columnName.toLowerCase(), obj);
    		}
    		myrs.add(m);
    	}
    	return myrs.size();
	}
	
	private int getFoundRows() throws Exception{
		if(foundRowsSql.equals("")){
			throw new Exception("foundRowsSql is empty.");
		}
		ResultSet rs = executeQuery(foundRowsSql);
		while(rs.next()){
			return rs.getInt(1);
		}

		return 0;
	}
	private MyResult query(String sql, int returnType, boolean paging) throws Exception{		
		MyResult myResult = null;
		InConnection();
		
		try {			
			myResult = MyResultFactory.createMyResult(returnType, sql);
			ResultSet rs = executeQuery(sql);			
			setResultSetMetaData(myResult, rs);
			int rowCount = 0;
			switch(returnType){
			case RETURN_LIST_LIST:
				rowCount = setReturnListList(myResult, rs);
				break;
			case RETURN_LIST_MAP:
				rowCount = setReturnListMap(myResult, rs);
				break;
			}
			
	    	myResult.setRowCount(rowCount);
	    	
	    	int foundRows = rowCount;
	    	if(paging){
	    		foundRows = getFoundRows();
	    	}
	    	myResult.setFoundRows(foundRows);
	    	myResult.initTimestamp();	    	
	    	return myResult;
		} catch (SQLException e) {
			throw new MyException(e, "MyJDBC::query-->", sql, 
					"\r\ndbs=", _ds.toString());
		} finally {
			InDisconnectDB();
		}	
	}
	public MyResult queryReturnListList(String sql, boolean paging) throws Exception{
		return query(sql, RETURN_LIST_LIST, paging);
	}
	public MyResult queryReturnListList(String sql) throws Exception{
		return queryReturnListList(sql, false);
	}
	public MyResult queryReturnMap(String sql, boolean paging) throws Exception{
		return query(sql, RETURN_LIST_MAP, paging);
	}
	public MyResult queryReturnMap(String sql) throws Exception{
		return queryReturnMap(sql, false);
	}

	//执行修改sql并提交
	public MyResult executeUpdate(String sql) throws Exception{
		logger.info(sql);
		InConnection();		
		try {
			MyResult rs = MyResultFactory.createMyResult(RETURN_INT, sql);			
			int n = _statement.executeUpdate(sql);	
			rs.setSql(sql);
			rs.setRowCount(n);
			return rs;
		} catch (SQLException e) {
			throw new MyException(e, "MyJDBC::executeUpdate-->", sql, 
					"\r\ndbs=", _ds.toString());
		} finally {
			InDisconnectDB();
		}
	}
	
	public void beginTransaction() throws Exception{
		_isOutCon = true;
		InConnection();		
		_con.setAutoCommit(false);
	}
	public void commit() throws Exception{		
		_isOutCon = false;
		_con.commit();
		_con.setAutoCommit(true);
		InDisconnectDB();
	}
	public void rollback() throws Exception{
		_con.setAutoCommit(true);
		_isOutCon = false;
		_con.rollback();
		InDisconnectDB();
	}
	
	protected abstract String getGetColumnsSql(String tableName);
	protected abstract String getColumnTypeName(MyResult rs, int row) throws Exception;
	//取得long字段数值, 
	private MyResultSetMetaData getMysqlTableColumns(String tableName) throws Exception{
		
		String sql = getGetColumnsSql(tableName);		
		MyResult rs = queryReturnListList(sql);  
		 
		MyResultSetMetaData rsmd = new MyResultSetMetaData();
		String columnTypeName = "";
		
    	for(int i=0; i<rs.getRowCount(); i++){
    		MyDBField field = new MyDBField();
    		field.setIndex(i);
    		field.setTableName(tableName);
    		
    		field.setColumnName(rs.getString(i, "COLUMN_NAME"));
    		columnTypeName = getColumnTypeName(rs, i);
    		
    		field.setColumnTypeName(columnTypeName);
    		
    		if(columnTypeName.startsWith("bigint") ||
    				columnTypeName.equals("NUMBER")){
    			field.setColumnType(Types.BIGINT);
    		}
    		else if(columnTypeName.startsWith("varchar") ||
    				columnTypeName.startsWith("VARCHAR")){
    			field.setColumnType(Types.VARCHAR);
    		}
    		else if(columnTypeName.startsWith("int")){
    			field.setColumnType(Types.INTEGER);
    		}
    		rsmd.addColumn(field);
    	}
    	return rsmd;
	}
	public MyResultSetMetaData getTableColumns(String tabName) throws Exception{		
		return getMysqlTableColumns(tabName);
	}
		
	//取得long字段数值
	public long getR0C0Long(String sql) throws Exception{		
		MyResult rs = queryReturnListList(sql);  
    	return rs.getR0C0Long();    	
	}
	//取最大值
	public long getMaxValue(String tabName, String fieldName) throws Exception{
		String sql = "select nvl(max(" + fieldName + "), 0) as MAXID from "+ tabName;
		return getR0C0Long(sql);
	}
	//取最大ID
	public long getMaxID(String tabName) throws Exception{
		return getMaxValue(tabName, "id");
	}
	//取字符数值
	public String getR0C0String(String sql) throws Exception{	
		MyResult rs = queryReturnListList(sql);   
    	return (String)rs.getR0C0();
	}
	//取字符数值
	public MyResultSetMetaData getQueryColumns(String sql) throws Exception{
		sql = "select * from (" + sql +")  t3t where 1=2";
		MyResult rs = queryReturnListList(sql);   
    	return rs.getResultSetMetaData();
	}
	
	public int getDBType(){		
		return _dbtype;
	}
	public boolean isMysql(){
		return getDBType() == DB_TYPE_MYSQL;
	}
	public boolean isOracle(){
		return getDBType() == DB_TYPE_ORACLE;
	}
	
	public abstract String getPagingSql(String sql, int startPageIndex, Integer pageCount);
	public abstract String constructSelectSql(String tableName, String sqlFields);
	
	public abstract String getFoundRowsSql(String sql);
	
	public abstract String addCalcFoundRowsOfPaginSql(String sql);
	
	public abstract void truncateTable(String sql) throws Exception;
	
	public abstract String getSingleQuoteTransfer() throws Exception;
	
	public boolean isAddPagingSql() {
		return addPagingSql;
	}
	public void setAddPagingSql(boolean addPagingSql) {
		this.addPagingSql = addPagingSql;
	}
}
