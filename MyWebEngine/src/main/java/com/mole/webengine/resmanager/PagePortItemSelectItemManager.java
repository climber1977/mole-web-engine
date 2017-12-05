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
import com.mole.webengine.utils.MyTimer;


public class PagePortItemSelectItemManager {
	private static HashMap<Integer, List<JSONObject>> selectitems = null;
	private static HashMap<String, List<JSONObject>> selectitemsBySql = new HashMap<String, List<JSONObject>>();
	private static MyTimer timer = new MyTimer();
	
	private static JSONObject createItemInfo(MyResult rs, int i) throws Exception{
		JSONObject jitem = new JSONObject();
		JsonUtils.putString(jitem, "name", rs.getString(i, "name"));
		JsonUtils.putString(jitem, "value", rs.getString(i, "value"));
		JsonUtils.putString(jitem, "caption", rs.getString(i, "caption"));	
		
		return jitem;
	}
	
	private static void put2(HashMap<String, List<JSONObject>> mapSelectitemsBySql,
			String sql, MyResult rs, int i, boolean clean) throws Exception{
		JSONObject jobj = createItemInfo(rs, i);
		List<JSONObject> lst = null;
		if(mapSelectitemsBySql.containsKey(sql)){
			lst = mapSelectitemsBySql.get(sql);
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<JSONObject>();
			mapSelectitemsBySql.put(sql, lst);
		}
		lst.add(jobj);
	}
	private synchronized static void getDataFromDB2(HashMap<String, List<JSONObject>> mapSelectitemsBySql,
		MyJDBC db, String sql, boolean clean) throws Exception{	
		MyResult rs = db.queryReturnListList(sql, false);	
		for(int i=0; i<rs.getFoundRows(); i++){
			put2(mapSelectitemsBySql, sql, rs, i, clean);
			clean = false;
		}
		timer.startClocking();
	}
	private static void put1(HashMap<Integer, List<JSONObject>> mapSelectitems, MyResult rs, int i, boolean clean) throws Exception{
		int selectitemGroupid = rs.getInt(i, "selectitem_groupid");
		JSONObject jobj = createItemInfo(rs, i);
		List<JSONObject> lst = null;
		
		if(mapSelectitems.containsKey(selectitemGroupid)){
			lst = mapSelectitems.get(selectitemGroupid);	
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<JSONObject>();
			mapSelectitems.put(selectitemGroupid, lst);
		}
		lst.add(jobj);	
	}
	private synchronized static void getDataFromDB1(HashMap<Integer, List<JSONObject>> mapSelectitems, 
			MyJDBC db, String sql, boolean clean) throws Exception{	
		MyResult rs = db.queryReturnListList(sql, false);	
		for(int i=0; i<rs.getFoundRows(); i++){
			put1(mapSelectitems, rs, i, clean);
			clean = false;
		}
		timer.startClocking();
	}
	public static void init() throws Exception{		
		//不使用clear，避免多线程冲突
		HashMap<Integer, List<JSONObject>> mapSelectitems = new HashMap<Integer, List<JSONObject>>();
		String sql = "select * from wb_select_item order by show_order";
		MyJDBC db = SysParams.getSysdb();
		getDataFromDB1(mapSelectitems,db, sql, false);
		selectitems = mapSelectitems;		
	}
	private static JSONArray assemble(List<JSONObject> lst, String itemTemplet){
		JSONArray jitems = new JSONArray();
		JSONObject jobj = null;
		for(int i=0; i<lst.size(); i++){
			jobj = lst.get(i);
			JsonUtils.putString(jobj, "templet", itemTemplet);		
			jitems.add(jobj);
			
		}
		return jitems;
	}
	private static JSONArray getSelectItemJsonByGroupid(int selectItemGroupid, String itemTemplet) throws Exception{
		if(selectItemGroupid == -1){
			return null;
		}
		
		List<JSONObject> lst = null;
		if(SysParams.isUseSysCache() || !timer.isRefreshTable()){			
			if(selectitems.containsKey(selectItemGroupid)){
				lst = selectitems.get(selectItemGroupid);
			}
		} 
		if(lst == null){
			String sql = "select * from wb_select_item where selectitem_groupid=" +
					selectItemGroupid + " order by show_order";
			MyJDBC db = SysParams.getSysdb();
			getDataFromDB1(selectitems, db, sql, true);
			if(!selectitems.containsKey(selectItemGroupid)){
				return null;
			}
			
			lst = selectitems.get(selectItemGroupid);
		}
		return assemble(lst, itemTemplet);
		
	}
	
	private static JSONArray getSelectItemJsonBySql(String databaseSource,String sql, String itemTemplet) throws Exception{
		if(sql == null){
			return null;
		}
		
		List<JSONObject> lst = null;
		if(SysParams.isUseSysCache() || !timer.isRefreshTable()){
			if(selectitemsBySql.containsKey(sql)){
				lst = selectitemsBySql.get(sql);
			}
		} 
		if(lst == null){
			MyJDBC db = SysParams.getdb(databaseSource);
			getDataFromDB2(selectitemsBySql, db, sql, true);			
			if(!selectitemsBySql.containsKey(sql)){
				return null;
			}
			lst = selectitemsBySql.get(sql);
		}
		
		return assemble(lst, itemTemplet);
	}
	public static JSONArray getSelectItemJson(int selectItemGroupid, 
			String databaseSource,String dataSourceSql, String itemTemplet) throws Exception{
		JSONArray jitems = getSelectItemJsonByGroupid(selectItemGroupid, itemTemplet);
		if(jitems != null){
			return jitems;
		}
		return getSelectItemJsonBySql(databaseSource, dataSourceSql, itemTemplet);
		
	}
}
