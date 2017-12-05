package com.mole.webengine.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtils {

	public static void write(HttpServletResponse response, String s, String charset) throws IOException {
		response.getOutputStream().write(s.getBytes(charset));
    }
	
	public static void writeUtf8(HttpServletResponse response, String s) throws IOException {
		write(response, s, "UTF-8");
    }
		
	public static JSONObject returnBoolean(boolean ret){
		JSONObject jo = new JSONObject();
		jo.put("ret", ret);
		return jo;
	}
	public static String returnBoolean2(boolean ret){
		JSONObject jo = returnBoolean(ret);		
		return jo.toJSONString();
	}
}
