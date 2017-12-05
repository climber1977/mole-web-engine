package com.mole.webengine.resmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;

public class PageManager {

	private static HashMap<String, List<PageInfo>> pages = new HashMap<String, List<PageInfo>>();
	
	private static void put(HashMap<String, List<PageInfo>> mapPages, MyResult rs, int i, boolean clean) throws Exception{
		PageInfo info = new PageInfo();
		info.setUuid(rs.getString(i, "uuid"));
		info.setZoneUUID(rs.getString(i, "zone_uuid"));
		info.setZoneType(rs.getString(i, "zone_type"));
		List<PageInfo> lst = null;
		if(mapPages.containsKey(info.getUuid())){
			lst = mapPages.get(info.getUuid());	
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<PageInfo>();
			mapPages.put(info.getUuid(), lst);
		}
		lst.add(info);
	}
	private synchronized static void getDataFromDB(HashMap<String, List<PageInfo>> mapPages, String sql, boolean clean) throws Exception{
		MyJDBC db = SysParams.getSysdb();

		MyResult rs = db.queryReturnListList(sql);
		
		for(int i=0; i<rs.getRowCount(); i++){
			put(mapPages, rs, i, clean);
			clean = false;
		}			
		
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, List<PageInfo>> mapPages = new HashMap<String, List<PageInfo>>();	
		String sql = "select * from wb_page order by uuid, show_order";
		getDataFromDB(mapPages, sql, false);
		pages = mapPages;
	}
	
	public static List<PageInfo> getPage(String uuid) throws Exception{
		if(SysParams.isUseSysCache()){
			if(pages.containsKey(uuid)){
				return pages.get(uuid);	
			}
		}
		
		String sql = "select * from wb_page where uuid='" + uuid + 
				"' order by uuid, show_order";
		getDataFromDB(pages, sql, true);
		return pages.get(uuid);
	}
}
