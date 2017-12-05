package com.mole.webengine.net;

import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public final class HttpClientUtil {
	private static HttpClient hc = new DefaultHttpClient();
	
	private static List<NameValuePair> turnParamFromMap(Map<String, String> paramMap) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : paramMap.keySet()) {
			params.add(new BasicNameValuePair(key, paramMap.get(key)));
		}
		return params;
	}
	private static String cloneQueryString(String url, HttpServletRequest request){
		if(url.indexOf("?") > 0){
			url = url + "&" + request.getQueryString();
		}else{
			url = url + "?" + request.getQueryString();
		}
		return url;
	}
	private static Map<String, String> cloneParams(Map<String, String> paramMap, 
			HttpServletRequest request) {
		if(paramMap == null){
			paramMap = new HashMap<String, String>();
		}
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			paramMap.put(key, request.getParameter(key));			
		}
		
		return paramMap;
	}
	@SuppressWarnings("deprecation")
	public static String getBody(HttpRequestBase request) throws Exception{
		HttpResponse response = hc.execute(request);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity);
		if (entity != null) {
			entity.consumeContent();
		}
		return body;
	}
	public static String get(String url, Map<String, String> paramMap) throws Exception {
		HttpGet httpget = new HttpGet();			
		if(paramMap != null){
			String str = EntityUtils.toString(new UrlEncodedFormEntity(turnParamFromMap(paramMap)));
			if(url.indexOf("?") > 0){
				url = url + "&" + str;
			}else{
				url = url + "?" + str;
			}
			httpget.setURI(new URI(url));
		}
		
		return getBody(httpget);
	}
	public static String get(String url, HttpServletRequest request, Map<String, String> paramMap) throws Exception {
		url = cloneQueryString(url, request);
		paramMap = cloneParams(paramMap, request);
		return get(url, paramMap);
	}
	@SuppressWarnings("deprecation")
	public static String post(String url, Map<String, String> paramMap) throws Exception {
		HttpPost httppost = new HttpPost(url);
		if(paramMap != null){
			httppost.setEntity(new UrlEncodedFormEntity(turnParamFromMap(paramMap), 
					HTTP.UTF_8));
		}

		return getBody(httppost);
	}
	public static String post(String url, HttpServletRequest request, Map<String, String> paramMap) throws Exception {
		url = cloneQueryString(url, request);
		paramMap = cloneParams(paramMap, request);
		return post(url, paramMap);
	}
	public static void main(String[] args) throws Exception {		
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("rtype", "2");
		paramMap.put("act", "datatable");
		String url = "http://localhost:8080/myweb/request/ajax_datatable_get_user?dbs=hr&secretKey=123456";
		String body = get(url, paramMap);
		System.out.println(new String(body.getBytes("iso8859-1"), "utf8"));		
	}
}