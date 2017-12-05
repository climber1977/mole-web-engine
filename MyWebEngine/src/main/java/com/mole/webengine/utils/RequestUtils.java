package com.mole.webengine.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {	
	private static ThreadLocal<MyParams> threadLocalParams = new ThreadLocal<MyParams>();  
	
	public static int getParamInt(HttpServletRequest request, String key, int value){
		String param = request.getParameter(key);
		if(param != null){
			return Integer.valueOf(param);
		}	
		return value;
	}	
	
	public static int getParamInt(HttpServletRequest request, String key){
		return getParamInt(request, key, 0);
	}	
	public static long getParamLong(HttpServletRequest request, String key, int value){
		String param = request.getParameter(key);
		if(param != null && !param.equals("")){
			return Integer.valueOf(param);
		}	
		return value;
	}	
	
	public static long getParamLong(HttpServletRequest httpServletRequest, String key){
		return getParamLong(httpServletRequest, key, 0);
	}	
	public static String getParamString(HttpServletRequest request, String key){
		String param = request.getParameter(key);
		if(param == null){
			return "";
		}	
		return param;
	}
	
	public static Date getParamDate(HttpServletRequest request, String key){
		String param = request.getParameter(key);
		if(param == null){
			return null;
		}
		return DateTimeUtils.StringToDate(param);
	}
	public static String getFile(HttpServletRequest request) throws Exception{
		String url = getUrl(request);
		URL u = new URL(url);
		return u.getFile();
		
	}	
	public static String getUrl(HttpServletRequest request) throws UnsupportedEncodingException{
		String url = request.getRequestURL().toString();
		if(request.getQueryString() != null)
		{
			String param = request.getQueryString();
			param = param.replace("%3D", "=");
			url = url + "?" + param;
		}		
		
		return url;
	}	
	public static void saveExportSql(HttpServletRequest request, String sql, String uuid) {
		request.getSession().setAttribute("exportsql_" + uuid, sql);
	}
	public static String getExportSql(HttpServletRequest request, String uuid) {
		Object o = request.getSession().getAttribute("exportsql_" + uuid);
		if(o == null){
			return "";
		}
		return o.toString();
	}

	public static void putSessionParam(HttpServletRequest request, String key, Object param) {
		MyParams p = (MyParams)request.getSession().getAttribute("session_params");
		if(p == null){
			p = new MyParams();  
			request.getSession().setAttribute("session_params", p);
		}
		p.put(key, param);
	}
	public static void cloneSessionParams(HttpServletRequest request, MyParams params) {
		MyParams p = (MyParams)request.getSession().getAttribute("session_params");
		if(p == null){
			return;
		}
		p.clone(params);
	}
	public static void putThreadParam(HttpServletRequest request, String key, Object param) {
		MyParams p = (MyParams)threadLocalParams.get();
		if(p == null){
			p = new MyParams();  
			threadLocalParams.set(p);
		}
		p.put(key, param);
	}
	public static void cloneThreadParams(HttpServletRequest request, MyParams params) {
		MyParams p = (MyParams)threadLocalParams.get();
		if(p == null){
			return;
		}
		p.clone(params);
		p.clean();
	}
	
	public static MyParams getParams(HttpServletRequest request, String uuid) {
		MyParams params = new MyParams();

		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			params.put(key, request.getParameter(key));
		}

		cloneSessionParams(request, params);
		cloneThreadParams(request, params);
		params.put("curDataTime", DateTimeUtils.getDateTime());
		
		//JSFileManager.callJs("append_common_params.js", "appendParams", request, response, uuid, params);
		
		return params;
	}
}
