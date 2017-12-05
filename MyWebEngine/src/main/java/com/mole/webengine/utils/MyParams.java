package com.mole.webengine.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyParams {
	private Map<String, Object> params=new HashMap<String,Object>();  
	
	public void put(String key, Object value){
		String name = "{" + key.toUpperCase() + "}";
		params.put(name, value);
	}
	
	public void put2(String key, Object value){
		String name = key.toUpperCase();
		params.put(name, value);
	}
	
	public void putString(String key, String value){
		if(value == null || value.equals("")){
			return;
		}
		put(key, (Object)value);
	}
	
	public boolean containsKey(String key){
		String name = key.toUpperCase();
		if(params.containsKey(name)){
			return true;
		}			
		return false;
	}
	public Object getValue(String key){
		String name =  key.toUpperCase();
		if(!name.startsWith("{")){
			name = "{" + name + "}";
		}
		return params.get(name);
	}
	public String getString(String key){
		Object o =  getValue(key);
		if(o == null){
			return null;
		}
		return String.valueOf(o);
	}
	public Integer getInt(String key){
		String v =  getString(key);
		if(v == null){
			return null;
		}
		return Integer.valueOf(v);
	}
	
	public void clone(MyParams outparams){
		if(outparams == null){
			return;
		}
		
		Iterator<Map.Entry<String,Object>> iter = params.entrySet().iterator();
		Map.Entry<String, Object> entry = null;
		while (iter.hasNext()) {
			entry = (Map.Entry<String, Object>) iter.next();
			outparams.put2(entry.getKey(), entry.getValue());
		}
	}
	
	public void clean(){
		params.clear();
	}
	public String toString(){
		Iterator<Map.Entry<String,Object>> iter = params.entrySet().iterator();		
		Map.Entry<String, Object> entry = null;
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		boolean first = true;
		String key = "";
		while (iter.hasNext()) {
			entry = (Map.Entry<String, Object>) iter.next();
			if(!first){
				builder.append(",");
			}
			key = entry.getKey();
			builder.append(key.substring(1,key.length()-1));
			builder.append(":\"");
			builder.append(entry.getValue());
			builder.append("\"");
			first = false;			
		}
		builder.append("}");
		return builder.toString();
	}
}
