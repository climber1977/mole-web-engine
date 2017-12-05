package com.mole.webengine.dbcore;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mole.webengine.debug.MyException;

public class MyResultSetMetaData {
	
	private List<MyDBField> fields = null;
	private HashMap<String, Integer> columnNames = new HashMap<String, Integer>();
	
	public MyResultSetMetaData(){
		fields = new ArrayList<MyDBField>();
	}
	
	//添加列描述
	public void addColumn(MyDBField field){
		columnNames.put(field.getColumnName().toUpperCase(), fields.size());
		columnNames.put(field.getColumnLabel().toUpperCase(), fields.size());		
		fields.add(field);
	}
	public MyDBField getColumn(int column){
		return fields.get(column);
	}
	//取得列名
	public String getColumnName(int column){
		return fields.get(column).getColumnName();
	}
	public String getColumnLabel(int column){
		return fields.get(column).getColumnLabel();
	}
	//取得列名
	public String getColumnTable(int column){
		return fields.get(column).getTableName();
	}
	//取得列类型
	public int getColumnType(int column){
		return fields.get(column).getColumnType();
	}
	
	
	public boolean isCanAccess(int column) {
		if(column >= fields.size()){
			return false;
		}
		return fields.get(column).isCanAccess();
	}
	public boolean isCanAccess(String columnName) {
		int column = getColumnIndexNoException(columnName);
		if(column == -1){
			return true;
		}
		return isCanAccess(column);
	}
	public void setCanAccess(int column, boolean canAccess) {
		fields.get(column).setCanAccess(canAccess);
	}
	
	public boolean isShow(int column) {
		if(column >= fields.size()){
			return false;
		}
		return fields.get(column).isShow();
	}
	public int getIndex(int column) {
		if(column >= fields.size()){
			return -1;
		}
		return fields.get(column).getIndex();
	}
	public boolean isShow(String columnName) {
		int column = getColumnIndexNoException(columnName);
		if(column == -1){
			return true;
		}
		return isShow(column);
	}
	public void setShow(int column, boolean show) {
		fields.get(column).setShow(show);
	}
	//取得列类型
	public int getColumnType(String columnName) throws Exception{
		columnName = columnName.toUpperCase();
		if(!columnNames.containsKey(columnName)){
			throw new MyException("MyResultSetMetaData::getColumnType-->columnName=", columnName);
		}
		
		int i = columnNames.get(columnName).intValue();
		return fields.get(i).getColumnType();
	}
	//是否是字符串
	public boolean columnIsString(String columnName) throws Exception{
		int i = getColumnIndex(columnName);

		return fields.get(i).getColumnType() == Types.VARCHAR;
	}	
	//取得列类型名称
	public String getColumnTypeName(int column){
		return fields.get(column).getColumnTypeName();
	}
	
	public String getColumnClassName(int column){
		return fields.get(column).getColumnClassName();
	}
	
	//取得列数量
	public int getColumnCount(){
		return fields.size();
	}
	//取得列索引
	public int getColumnIndexNoException(String columnName){
		columnName = columnName.toUpperCase();
		if(!columnNames.containsKey(columnName)){
			return -1;
		}
		
		return columnNames.get(columnName).intValue();
	}
	
	//取得列索引
	public int getColumnIndex(String columnName) throws Exception{
		int index = getColumnIndexNoException(columnName);
		if(index == -1){
			throw new MyException("MyResultSetMetaData::getColumnIndex-->columnName=", 
					columnName, "\r\nindex == -1");
		}
		
		return index;
	}
	
	public void print(){
		System.out.print("ColumnName:");
		for(int i=0; i<fields.size(); i++){
			MyDBField field = fields.get(i);
			System.out.print(field.getTableName() + ":" + field.getColumnName() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.print("ColumnLabel:");
		for(int i=0; i<fields.size(); i++){
			MyDBField field = fields.get(i);
			System.out.print(field.getTableName() + ":" + field.getColumnLabel() + " ");
		}
		System.out.println();
	}
}
