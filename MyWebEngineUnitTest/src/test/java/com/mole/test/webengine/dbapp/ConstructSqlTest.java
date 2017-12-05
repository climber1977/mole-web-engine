package com.mole.test.webengine.dbapp;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConstructSqlTest {
	public static void constructSql(){		
		//文字替换（置换字符）
		Map<String,String> map=new HashMap<String,String>();    
		map.put("{workcode}", "CY000001");
		map.put("{mail}", "wang");
		map.put("{id}", "10");
		
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher("id = {id} and workcode like '%{workcode}%' or mail like '%{mail}%'");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			System.out.println(matcher.group());
			if(!map.containsKey(matcher.group())){
				continue;
			}			
		    matcher.appendReplacement(sbr, map.get(matcher.group()));
		    System.out.println(sbr.toString());
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());
	}	
	
	public static void main(String args[]) throws Exception {
		
		constructSql();
	}
	
	
}
