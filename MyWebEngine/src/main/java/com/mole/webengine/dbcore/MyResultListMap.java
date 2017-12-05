package com.mole.webengine.dbcore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mole.webengine.utils.ObjectUtils;

public class MyResultListMap extends MyResult{

	private List<HashMap<String, Object>> resultListMap = new ArrayList<HashMap<String, Object>>();	
	
	public MyResultListMap(String sql) {
		super(sql);
	}

	public List<HashMap<String, Object>> getResultObject() {
		return resultListMap;
	}
	
	//取得字段值
	public Object getValue(int row, String columnName){
		
		columnName = columnName.toLowerCase();
		if(resultListMap.get(row).containsKey(columnName)){
			return resultListMap.get(row).get(columnName);
		}
			
		return null;
	}
	
	public boolean getBoolean(int row, String columnName){		
		Object o = getValue(row, columnName);
		return ObjectUtils.obj2Boolean(o);
	}
	
	//int
	public int getInt(int row, int column) throws Exception{
		throw new Exception("no the getInt(int row, int column)  function");
	}
	
	public int getInt(int row, String columnName){		
		Object o = getValue(row, columnName);
		return ObjectUtils.obj2Integer(o);
		
	}
	//long	
	public long getLong(int row, String columnName){
		Object o = getValue(row, columnName);
		return ObjectUtils.obj2Long(o);
	}	

	public Date getDate(int row, String columnName) throws Exception{
		Object o = getValue(row, columnName);
		return ObjectUtils.obj2Date(o);
	}
	
	public String getStringNotExistToEmpty(int row, String columnName) throws Exception{
		int column = resultSetMetaData.getColumnIndexNoException(columnName);
		if(column == -1){
			return "";
		}
		return getString(row, columnName);
	}
	
	//取得字段值	
	public String getString(int row, String columnName){
		Object o = getValue(row, columnName);
		return ObjectUtils.obj2String(o);
	}
	
	protected void printResult(){		
		System.out.println();
		for(int i=0; i<getRowCount(); i++){
			Iterator<Map.Entry<String, Object>> iter = null;
			iter = resultListMap.get(i).entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
				Object obj = entry.getValue();
				System.out.print(entry.getKey() + ":" + obj + "; ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
