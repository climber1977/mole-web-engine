package com.mole.webengine.resmanager;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;


public class PageChartManager {	
	private static HashMap<String, JSONObject> charts = new HashMap<String, JSONObject>();
	
	private static JSONObject createChartInfo(MyResult rs, int i) throws Exception{
		JSONObject jobj = new JSONObject();				
		JsonUtils.putString(jobj, "id", rs.getString(i, "chartid"));
		JsonUtils.putString(jobj, "uuid", rs.getString(i, "uuid"));
		JsonUtils.putString(jobj, "height", rs.getString(i, "height"));
		JsonUtils.putString(jobj, "title", rs.getString(i, "title"));
		JsonUtils.putString(jobj, "yTitle", rs.getString(i, "y_itle"));
		JsonUtils.putString(jobj, "subtitle", rs.getString(i, "subtitle"));	
		JsonUtils.putString(jobj, "templet", rs.getString(i, "templet"));
		JsonUtils.putString(jobj, "tooltip", rs.getString(i, "tooltip"));
		JsonUtils.putString(jobj, "func", rs.getString(i, "func"));
		JsonUtils.putString(jobj, "uuid", rs.getString(i, "excute_uuid"));
		JsonUtils.putString(jobj, "dbs", rs.getString(i, "database"));
		
		return jobj;
	}
	private synchronized static void getDataFromDB(HashMap<String, JSONObject> mapCharts, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();

		MyResult rs = db.queryReturnListList(sql);
		JSONObject jobj = null;
		for(int i=0; i<rs.getRowCount(); i++){
			jobj = createChartInfo(rs, i);
			mapCharts.put(rs.getString(i, "uuid"), jobj);
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, JSONObject> mapCharts = new HashMap<String, JSONObject>();
		String sql = "select * from wb_page_chart";
		getDataFromDB(mapCharts, sql);
		charts = mapCharts;
	}
	
	public static JSONObject getChartJson(String uuid) throws Exception{
		if(SysParams.isUseSysCache()){
			if(charts.containsKey(uuid)){
				return charts.get(uuid);
			}
		}
		
		String sql = "select * from wb_page_chart where uuid='" + uuid + "'";
		getDataFromDB(charts, sql);
		
		if(!charts.containsKey(uuid)){
			return new JSONObject();
		}
		return charts.get(uuid);		
		
	}
}
