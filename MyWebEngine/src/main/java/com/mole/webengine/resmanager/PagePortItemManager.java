package com.mole.webengine.resmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;


public class PagePortItemManager {
	private static HashMap<Long, List<JSONObject>> items = new HashMap<Long, List<JSONObject>>();

	private static JSONObject createItemInfo(MyResult rs, int i) throws Exception{
		JSONObject jitem = null;
		String myjson = rs.getString(i, "myjson");
		if(myjson != null && !myjson.equals("")){
			jitem = JsonUtils.jstring2JObject(myjson);
		}else{
			jitem = new JSONObject();
		}
		
		JsonUtils.putInt(jitem, "groupid", rs.getInt(i, "groupid"));
		JsonUtils.putString(jitem, "lable", rs.getString(i, "lable"));
		JsonUtils.putString(jitem, "caption", rs.getString(i, "lable"));
		JsonUtils.putString(jitem, "name", rs.getString(i, "name"));
		JsonUtils.putString(jitem, "content", rs.getString(i, "content"));
		JsonUtils.putIntNo0(jitem, "lablecolumn", rs.getInt(i, "lable_column"));
		JsonUtils.putIntNo0(jitem, "itemcolumn", rs.getInt(i, "item_column"));
		JsonUtils.putString(jitem, "style", rs.getString(i, "style"));
		JsonUtils.putString(jitem, "templet", rs.getString(i, "templet"));
		JsonUtils.putString(jitem, "cssClass", rs.getString(i, "css_class"));
		JsonUtils.putInt(jitem, "selectitem_groupid", rs.getInt(i, "selectitem_groupid"));
		JsonUtils.putString(jitem, "item_templet", rs.getString(i, "item_templet"));
		
		JsonUtils.putString(jitem, "database_source", rs.getString(i, "dbs"));
		JsonUtils.putString(jitem, "data_source_sql", rs.getString(i, "data_source_sql"));
		
		return jitem;
	}
	private static void put(HashMap<Long, List<JSONObject>> mapItems, MyResult rs, int i, boolean clean) throws Exception{
		
		long portGroupid = rs.getLong(i, "port_group_id");
		JSONObject jobj = createItemInfo(rs, i);
		List<JSONObject> lst = null;
		if(mapItems.containsKey(portGroupid)){
			lst = mapItems.get(portGroupid);	
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<JSONObject>();
			mapItems.put(portGroupid, lst);
		}
		lst.add(jobj);
	}
	private synchronized static void getDataFromDB(HashMap<Long, List<JSONObject>> mapItems, String sql, boolean clean) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		
		MyResult rs = db.queryReturnListList(sql);			
		for(int i=0; i<rs.getRowCount(); i++){
			put(mapItems, rs, i, clean);
			clean = false;
		}
	}
	public static void init() throws Exception{	
		//不使用clear，避免多线程冲突
		HashMap<Long, List<JSONObject>> mapItems = new HashMap<Long, List<JSONObject>>();
		String sql = "select * from wb_page_portal_item order by groupid, show_order";
		getDataFromDB(mapItems, sql, false);
		items = mapItems;
	}
	private static void putGroupJson(JSONArray portalChild, JSONArray jgroupChild, 
			String groupTemplate, int index) throws Exception{
		if(jgroupChild == null){
			return ;
		}
		
		JSONObject jgroup = new JSONObject();
		JsonUtils.putString(jgroup, "name", "groups_" + index);
		JsonUtils.putString(jgroup, "content", "elements");
		JsonUtils.putString(jgroup, "templet", groupTemplate);
		JsonUtils.putObject(jgroup, "child", jgroupChild);
		portalChild.add(jgroup);
		
	}
	public static JSONArray getGroupJson(long portGroupid, String groupTemplate) throws Exception{
		List<JSONObject> lst = null;
		JSONArray portalChild = new JSONArray();
		if(SysParams.isUseSysCache()){
			if(items.containsKey(portGroupid)){
				lst = items.get(portGroupid);
			}
		} 
		if(lst == null){		
			String sql = "select * from wb_page_portal_item where port_group_id=" + portGroupid + " order by groupid, show_order";
			getDataFromDB(items, sql, true);			
			if(!items.containsKey(portGroupid)){
				return portalChild;
			}
			lst = items.get(portGroupid);
		}
		JSONArray jgroupChild = new JSONArray();
		int pregroupid = -1;
		
		JSONObject jitem = null;
		int selectitemGroupid = -1;
		String itemTemplet = null;
		String databaseSource = null;
		String dataSourceSql = null;
		JSONArray selectitemjson = null;
		
		int i=0;
		for(i=0; i<lst.size(); i++){
			jitem = lst.get(i);			
			if(pregroupid !=-1 && pregroupid != JsonUtils.getIntValue(jitem, "groupid")){				
				putGroupJson(portalChild, jgroupChild, groupTemplate, i);
				jgroupChild = new JSONArray();
			}
			selectitemGroupid = JsonUtils.getInteger(jitem, "selectitem_groupid");
			itemTemplet = JsonUtils.getString(jitem, "item_templet");
			databaseSource = JsonUtils.getString(jitem, "database_source");	
			dataSourceSql = JsonUtils.getString(jitem, "data_source_sql");	
			selectitemjson = PagePortItemSelectItemManager.getSelectItemJson(selectitemGroupid, 
					databaseSource, dataSourceSql,itemTemplet);
			if(selectitemjson != null){
				JsonUtils.putObject(jitem, "child", selectitemjson);
			} 
			
			jgroupChild.add(jitem);
			pregroupid = JsonUtils.getIntValue(jitem, "groupid");
		}
		putGroupJson(portalChild, jgroupChild, groupTemplate, i);
		return portalChild;
		
	}
}
