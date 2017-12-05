package com.mole.test.webengine.utils;

import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.mole.webengine.file.FileUtils;
import com.mole.webengine.utils.JsonUtils;


public class JsonLoopScanTest {
	private static void printtab(int n){
		for(int i=0; i<n; i++){
			System.out.print("  ");
		}
	}
	private static void myprint(int n, String key, Object o){
		printtab(n);
		System.out.print("\"" + key + "\":\"" + o.toString() + "\"");
	}
	private static void dealObject(String key, Object o, int deep){
		if(o.getClass().equals(JSONObject.class)){
			scanJson(key, (JSONObject)o, deep+1);				
		} else if(o.getClass().equals(JSONArray.class)){
			scanArray(key, (JSONArray)o, deep+1);
		} else {
			myprint(deep+1, key, o);
		}
	}
	private static void scanArray(String key, JSONArray jarray, int deep){
		printtab(deep);
		if(!key.equals("")){
			System.out.print("\"" + key + "\":");
		}
		System.out.println("[");
		for(int i=0; i<jarray.size(); i++){		
			if(i != 0){
				System.out.println(",");
			}
			dealObject("", jarray.get(i), deep+1);
		}
		System.out.println();
		printtab(deep);
		System.out.print("]");
	}
	private static void scanJson(String key, JSONObject json, int deep){
		printtab(deep);
		if(!key.equals("")){
			System.out.print("\"" + key + "\":");
		}
		System.out.println("{");
		Set<String> keys = json.keySet();
		Iterator<String> ite = keys.iterator();
		int i = 0;
		while(ite.hasNext()){
			if(i != 0){
				System.out.println(",");
			}
			key = ite.next();
			Object o = json.get(key);
			dealObject(key, o, deep);
			i++;
		}
		System.out.println();
		printtab(deep);
		System.out.print("}");
	}
	public static void main(String args[]) throws Exception {
		String json = FileUtils.readToStringDefaultChartSet("./data/form_controls.json");
		//json = "{\"itemtype\":\"default\"}";
		JSONObject jmain = JsonUtils.jstring2JObject(json);
		
		scanJson("", jmain, 0);
	}
	
	
}
