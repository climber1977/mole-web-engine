package com.mole.test.webengine.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbapp.ExcelField;
import com.mole.webengine.utils.JsonUtils;



public class JsonUtilsTest {
	static String json = "{\"name\": \"id\",\"cellNum\": 1}";
	static String arrayAyy="[[14,\"小学语文\"],[154,\"美食\"],[72,\"高中物理\"],null,[50,\"高中化学\"],[15,\"小学数学\"],[13\"英语\"],null,[1,\"其他英语培训\"],null]";
	static ExcelField testObj;
	
	private static void init(){
		testObj = new ExcelField();
		testObj.setCellNum(1);
		testObj.setName("id");
	}
	
	private static void jstring2EntityTest() {		
		ExcelField obj = JsonUtils.jstring2Entity(json, ExcelField.class);
		System.out.println("jstring2EntityTest");
		System.out.println(obj.getName());		
		System.out.println(obj.getCellNum());
		System.out.println();
	}

	private static void jstring2JObjectTest() {
		System.out.println("jstring2JObjectTest");
		JSONObject jobj = JsonUtils.jstring2JObject(json);
		System.out.println(jobj.toString());
		System.out.println();
	}
	
	private static void entity2JStringTest() {
		System.out.println("entity2JStringTest");
		String  jstring = JsonUtils.entity2JString(testObj);
		System.out.println(jstring);	
		System.out.println();
	}
	
	private static void entity2JStringTest2() {
		System.out.println("entity2JStringTest2");
		String  jstring = JsonUtils.entity2JString(testObj, true);
		System.out.println(jstring);	
		System.out.println();
	}
	
	private static void entity2JObjectTest() {
		System.out.println("entity2JObjectTest");
		Object object = JsonUtils.entity2JObject(testObj);
		System.out.println(object.getClass());	
		System.out.println(object.toString());	
		System.out.println();
	}
	
	private static void entity2JObjectTest2() {
		System.out.println("entity2JObjectTest2");
		
		List<ExcelField> list = new ArrayList<ExcelField>();
		list.add(testObj);
		
		ExcelField obj = new ExcelField();
		obj.setName("name");
		obj.setCellNum(2);
		list.add(obj);
		
		Object object = JsonUtils.entity2JObject(list);
		System.out.println(object.getClass());	
		System.out.println(object.toString());	
		System.out.println();
	}
	
	private static void jstring2JArrayTest() {
		System.out.println("jstring2JArrayTest");
		JSONArray jarray = JsonUtils.jstring2JArray(arrayAyy);
		System.out.println(jarray.toString());	
		System.out.println();
	}
	public static void main(String args[]) throws Exception {
		init();
		
		
		jstring2EntityTest();
		
		jstring2JObjectTest();
		
		entity2JStringTest();
		entity2JStringTest2();
		
		entity2JObjectTest();
		entity2JObjectTest2();
		
		jstring2JArrayTest();

	}
	
	
}
