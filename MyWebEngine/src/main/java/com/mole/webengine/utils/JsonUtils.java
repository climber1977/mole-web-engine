package com.mole.webengine.utils;

import com.alibaba.fastjson.util.TypeUtils;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;


public class JsonUtils {
	private static SerializeConfig mapping = new SerializeConfig();  
    private static String dateFormat;  
    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";  
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));  
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));  
    }  
    
    public static Integer getInteger(JSONObject json, String key, Integer def) {
        Object value = json.get(key);

        Integer v = TypeUtils.castToInt(value);
        if(v == null){
        	v = def;
        }
        return v;
    }
    public static Integer getInteger(JSONObject json, String key) {
    	return getInteger(json, key, 0);
    }
    public static int getIntValue(JSONObject json, String key, int def) {
        Object value = json.get(key);

        Integer v = TypeUtils.castToInt(value);
        if(v == null){
        	v = def;
        }
        return v.intValue();
    }
    public static int getIntValue(JSONObject json, String key) {
    	return getIntValue(json, key, 0);
    }
    
    public static String getString(JSONObject json, String key, String def) {
        String value = json.getString(key);
       
        if(value == null){
        	value = def;
        }
        return value;
    }
    public static String getString(JSONObject json, String key) {
    	return getString(json, key, null);
    }
    public static void putObject(JSONObject json, String key, Object value) {
    	if(value == null){
    		return;
    	}
    	json.put(key, value);
    }
    public static void putInt(JSONObject json, String key, int value) {    	
    	json.put(key, value);
    }
    public static void putIntNo0(JSONObject json, String key, int value) {
    	if(value == 0){
    		return;
    	}
    	json.put(key, value);
    }
    public static void putString(JSONObject json, String key, String value) {
    	if(value == null || value.isEmpty()){
    		return;
    	}
    	json.put(key, value);
    }
    
	public static final <T> T jstring2Entity(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}
	
	public static JSONObject jstring2JObject(String text) {
		return JSON.parseObject(text);
	}
	
	public static String entity2JString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}
	
	public static String entity2JString(Object object) {
		return entity2JString(object, false);
	}
	
	public static String entity2JString2(Object object) {
		return JSON.toJSONString(object, mapping);
	}
	
	public static Object entity2JObject(Object object) {
		return JSON.toJSON(object);
	}
	
	public static JSONArray jstring2JArray(String text) {
		return JSONArray.parseArray(text);
	}
	
	public static boolean isJSONArray(Object o){
		return o.getClass().equals(JSONArray.class);
	}
	public static boolean isJSONObject(Object o){
		return o.getClass().equals(JSONObject.class);
	}
}
