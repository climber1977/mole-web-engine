package com.mole.webengine.dbcore;

import java.util.Date;

import com.mole.webengine.debug.MyException;

public class MyResult {
	protected MyResultSetMetaData resultSetMetaData = null;

	private String sql;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	private String noPagingSql = "";
	
	public String getNoPagingSql() {
		return noPagingSql;
	}
	public void setNoPagingSql(String noPagingSql) {
		this.noPagingSql = noPagingSql;
	}

	private int foundRows;	
	public int getFoundRows() {
		return foundRows;
	}
	public void setFoundRows(int foundRows) {
		this.foundRows = foundRows;
	}

	public MyResult(String sql){
		this.sql = sql;
	}

	//影响的行数
	private int rowCount;
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getRowCount(){
		return rowCount;
	}
	//取得列数
	public int getColumnCount(){	
		if(resultSetMetaData == null){
			return 0;
		}
		return resultSetMetaData.getColumnCount();
	}

	//取得字段描述
	public void setResultSetMetaData(MyResultSetMetaData rsm){		
		this.resultSetMetaData = rsm;
	}
	public MyResultSetMetaData getResultSetMetaData(){		
		return resultSetMetaData;
	}
	
	//取得结果集
	public Object getResultObject() throws Exception{
		return null;
	}
	//取得字段值
	public Object getValue(int row, int column) throws Exception{
		throw new MyException("no the getValue(int row, int column)  function");
	}
	//取得字段值
	public Object getValue(int row, String columnName) throws Exception{
		throw new MyException("no the getValue(int row, String columnName)  function");
	}
	public boolean getBoolean(int row, int column) throws Exception{
		throw new MyException("no the getBoolean(int row, int column)  function");
	}
	public boolean getBoolean(int row, String columnName) throws Exception{
		throw new MyException("no the getBoolean(int row, String columnName)  function");
	}
	
	//int
	public int getInt(int row, int column) throws Exception{
		throw new MyException("no the getInt(int row, int column)  function");
	}
	public int getInt(int row, String columnName) throws Exception{
		throw new MyException("no the getInt(int row, String columnName)  function");
	}
	//long
	public long getLong(int row, int column) throws Exception{
		throw new MyException("no the getLong(int row, int column)  function");
	}
	public long getLong(int row, String columnName) throws Exception{
		throw new MyException("no the getLong(int row, String columnName)  function");
	}
	//Date
	public Date getDate(int row, int column) throws Exception{
		throw new MyException("no the getLong(int row, int column)  function");
	}
	public Date getDate(int row, String columnName) throws Exception{
		throw new MyException("no the getLong(int row, String columnName)  function");
	}
	//取得字段值
	public String getStringNotExistToEmpty(int row, String columnName) throws Exception{
		throw new MyException("no the getStringNotExistToEmpty(int row, String columnName)  function");
	}
	
	public String getString(int row, int column) throws Exception{
		throw new MyException("no the getString(int row, int column)  function");
	}
	public String getString(int row, String columnName) throws Exception{
		throw new MyException("no the getString(int row, String columnName)  function");
	}
	
	public String getColumnName(int column){
		return resultSetMetaData.getColumnName(column);
	}
	public String getColumnLabel(int column){
		return resultSetMetaData.getColumnLabel(column);
	}
	//取得列索引
	public int getColumnIndex(String columnName){
		return resultSetMetaData.getColumnIndexNoException(columnName);
	}
	public boolean columnIsExist(String columnName){
		return resultSetMetaData.getColumnIndexNoException(columnName) != -1;
	}
	
	//取得0行，0列的值
	public Object getR0C0() throws Exception{
		return getValue(0, 0);
	}
	//取得0行，0列的值
	public long getR0C0Long() throws Exception{
		return getLong(0, 0);
	}
	
	private void printColumn(){
		System.out.print("ColumnName");
		for(int i=0; i<getColumnCount(); i++){
			System.out.print(resultSetMetaData.getColumnName(i) + " ");
		}
		System.out.println();
		System.out.print("ColumnLabel");
		for(int i=0; i<getColumnCount(); i++){
			System.out.print(resultSetMetaData.getColumnLabel(i) + " ");
		}
	}
	protected void printResult(){
		
	}
		
	public void print(){
		System.out.println(sql);
		printColumn();
		System.out.println("rowCount=" + getRowCount());
		printResult();
	}
	
	long timestamp;
	public long getTimestamp() {
		return timestamp;
	}
	public void initTimestamp() {
		this.timestamp = System.currentTimeMillis();
	}
	
}
