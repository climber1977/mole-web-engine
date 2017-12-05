package com.mole.webengine.resmanager;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;


public class PagePortManager {
	private static HashMap<String, JSONObject> ports = new HashMap<String, JSONObject>();

	private static JSONObject createPortInfo(MyResult rs, int i) throws Exception{
		JSONObject jobj = null;
		String params = rs.getString(i, "params");
		if(!params.isEmpty()){
			jobj = JsonUtils.jstring2JObject(params);
		} else {
			jobj = new JSONObject();
		}
		JsonUtils.putString(jobj, "uuid", rs.getString(i, "uuid"));
		JsonUtils.putString(jobj, "name", rs.getString(i, "uuid"));
		JsonUtils.putString(jobj, "content", rs.getString(i, "content"));
		JsonUtils.putIntNo0(jobj, "lablecolumn", rs.getInt(i, "lable_column"));
		JsonUtils.putIntNo0(jobj, "itemcolumn", rs.getInt(i, "item_column"));
		JsonUtils.putString(jobj, "templet", rs.getString(i, "portal_templet"));	
		String myjs = rs.getString(i, "myjs");
		if(myjs == null){
			myjs = "";
		} else {
			myjs = myjs.replace("$", "\\$");
		}
		JsonUtils.putString(jobj, "myjs", myjs);
		
		JsonUtils.putString(jobj, "port_group_id", rs.getString(i, "port_group_id"));
		JsonUtils.putString(jobj, "group_templet", rs.getString(i, "group_templet"));
		
		return jobj;
	}
	private synchronized static void getDataFromDB(HashMap<String, JSONObject> mapPorts, String sql) throws Exception{	
		MyJDBC db = SysParams.getSysdb();

		MyResult rs = db.queryReturnListList(sql);
		JSONObject jobj = null;
	
		for(int i=0; i<rs.getRowCount(); i++){
			jobj = createPortInfo(rs, i);
			
			mapPorts.put(rs.getString(i, "uuid"), jobj);
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, JSONObject> mapPorts = new HashMap<String, JSONObject>();
		String sql = "select * from wb_page_portal";
		getDataFromDB(mapPorts, sql);
		ports = mapPorts;
	}
	
	public static JSONObject getPortalJson(String uuid) throws Exception{
		JSONObject jobj = null;
		if(SysParams.isUseSysCache()){
			if(ports.containsKey(uuid)){
				jobj = ports.get(uuid);
			}
		}
		if(jobj == null){
			String sql = "select * from wb_page_portal where uuid='" + uuid +"'";
			getDataFromDB(ports, sql);
		}

		if(!ports.containsKey(uuid)){
			return new JSONObject();
		}
		jobj = ports.get(uuid);
		long portGroupid = JsonUtils.getIntValue(jobj, "port_group_id");	
		String groupTemplate = JsonUtils.getString(jobj, "group_templet");	
		JSONArray portalChild = PagePortItemManager.getGroupJson(portGroupid, groupTemplate);
		JsonUtils.putObject(jobj, "child", portalChild);
		return jobj;
		
	}
}
