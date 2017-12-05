package com.mole.webengine.resmanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.RequestUtils;


public class I18n {
	private static HashMap<String, ArrayList<String>> i18n = new HashMap<String, ArrayList<String>>();
	private final static String I18N_TABLE = "wb_i18n_dict";
	
	final static int LNG_CHINESE = 0;
	final static int LNG_ENLIGISH = 1;
	final static int LNG_OTHER = 2;
	
	public synchronized static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, ArrayList<String>> mapI18n = new HashMap<String, ArrayList<String>>();
		
		String sql = "select * from " + I18N_TABLE;
		MyJDBC db = SysParams.getSysdb();
		db.Connection();
		try {
			ResultSet rs = db.executeQuery(sql);
			String key = "";
			
			while(rs.next()){
				ArrayList<String> v = new ArrayList<String>();
				key = rs.getString("key");  
				v.add(rs.getString("chinese"));
				v.add(rs.getString("engilish"));
				v.add(rs.getString("other"));
				mapI18n.put(key.toLowerCase(), v);
			}
			i18n = mapI18n;
		} finally {
			db.DisconnectDB();
		}	
	}
	public static String getLangue(String word, PageContext pageContext){
		long lng = getLanague(pageContext);
		return getLangue(word, lng);
	}
	public static String getLangue(String word, long langueIndex){
		if(langueIndex < 0){
			langueIndex = 0;
		}
		if(!i18n.containsKey(word)){
			return word;
		}
		return i18n.get(word).get((int)langueIndex);
	}
	//获取全部国际化对应的值
	private static HashMap<String, String> getAllDict(List<String> words, int langueIndex){
		HashMap<String, String> kv = new HashMap<String, String>();
		Iterator<Map.Entry<String, ArrayList<String>>> iter = i18n.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = (Map.Entry<String, ArrayList<String>>) iter.next();
			ArrayList<String> v = entry.getValue();
			kv.put(entry.getKey(), v.get(langueIndex));
		}
		return kv;
	}
	//取得国际化对应的值
	public static HashMap<String, String> getDict(List<String> words, int langueIndex) throws Exception{
		if(langueIndex < 0){
			langueIndex = 0;
		}
		
		if(words.size() == 0){
			return getAllDict(words, langueIndex);
		}
		
		HashMap<String, String> kv = new HashMap<String, String>();
		String key = "";
		for(int i=0; i<words.size(); i++){
			key = words.get(i);
			if(i18n.containsKey(key.toLowerCase())){
				kv.put(key, i18n.get(key.toLowerCase()).get(langueIndex));
			} else {
				kv.put(key, key);
			}
		}
		return kv;
	}
	
	//取得国际化对应的值
	public static String getDict(HttpServletRequest request, 
			HttpServletResponse response, int lng) throws Exception{
		ArrayList<String> words= new ArrayList<String>();
		
		Enumeration<?> e = request.getParameterNames();
		while(e.hasMoreElements()){
			String key = e.nextElement().toString();
			words.add(key);
		}
		HashMap<String, String> kv = getDict(words, lng);
		String result = JsonUtils.entity2JString(kv);
		
		return result;		
	}
	
	public static void setLanague(HttpServletRequest request, long lng){
		request.getSession().setAttribute("i18nLng", lng);
	}
	
	public static long getLanague(HttpServletRequest request){
		return RequestUtils.getParamLong(request, "i18nLng", 0);
	}
	public static long getLanague(PageContext pageContext){
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return RequestUtils.getParamLong(request, "i18nLng", 0);
	}
}
