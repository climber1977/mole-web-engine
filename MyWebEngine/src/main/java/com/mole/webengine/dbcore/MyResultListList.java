package com.mole.webengine.dbcore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mole.webengine.utils.ObjectUtils;

public class MyResultListList extends MyResult{
	private List<List<Object>> resultListList = new ArrayList<List<Object>>();	
	
	public MyResultListList(String sql) {
		super(sql);
	}
	
	//取得结果集
	public Object getResultObject(){
		return resultListList;
	}
	
	public Object getValue(int row, int column){
		return resultListList.get(row).get(column);
	}
	
	//取得字段值
	public Object getValue(int row, String columnName) throws Exception{		
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getValue(row, column);		
	}
	
	public boolean getBoolean(int row, int column){
		Object o = getValue(row, column);
		return ObjectUtils.obj2Boolean(o);
	}
	public boolean getBoolean(int row, String columnName) throws Exception {		
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getBoolean(row, column);		
	}
	
	//int
	public int getInt(int row, int column){
		Object o = getValue(row, column);
		return ObjectUtils.obj2Integer(o);
	}
	
	public int getInt(int row, String columnName) throws Exception{		
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getInt(row, column);
	}
	
	//long
	public long getLong(int row, int column){		
		Object o = getValue(row, column);
		return ObjectUtils.obj2Long(o);
	}
	public long getLong(int row, String columnName) throws Exception{		
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getLong(row, column);
	}
	public Date getDate(int row, int column) throws Exception{
		Object o = getValue(row, column);
		return ObjectUtils.obj2Date(o);
	}
	public Date getDate(int row, String columnName) throws Exception{
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getDate(row, column);
	}
	//取得字段值
	public String getString(int row, int column){
		Object o =getValue(row, column);
		return ObjectUtils.obj2String(o);
	}
	public String getStringNotExistToEmpty(int row, String columnName) throws Exception{
		int column = resultSetMetaData.getColumnIndexNoException(columnName);
		if(column == -1){
			return "";
		}
		return getString(row, column);
	}
	public String getString(int row, String columnName) throws Exception{
		int column = resultSetMetaData.getColumnIndex(columnName);
		return getString(row, column);
	}
	
	protected void printResult(){		
		System.out.println();
		for(int i=0; i<getRowCount(); i++){
			for(int j=0; j<getColumnCount(); j++){
				System.out.print(getValue(i, j) + " ");
			}
			System.out.println();
		}
	}
}
