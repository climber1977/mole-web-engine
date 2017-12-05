package com.mole.webengine.dbapp;

import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;

public class ExecutePageDatablesManager {
	private static HashMap<String, ExecutePageDatablesInfo> datablesByUUID = new HashMap<String, ExecutePageDatablesInfo>();
	private static MyTimer timer = new MyTimer();
	
	private static ExecutePageDatablesInfo createInfo(MyResult rs, int i) throws Exception{
		ExecutePageDatablesInfo info = new ExecutePageDatablesInfo();
		info.setId(rs.getLong(i, "id"));
		info.setUuid(rs.getString(i, "uuid"));
		info.setTableName(rs.getString(i, "table_name"));
		info.setDescGroupid(rs.getLong(i, "desc_groupid"));		
		info.setTemplet(rs.getString(i, "templet"));
		info.setJsVerifyParam(rs.getString(i, "js_verify_param"));
		info.setDisplayQuery(rs.getString(i, "display_query"));
		info.setDisplayImport(rs.getString(i, "display_import"));		
		info.setDisplayExport(rs.getString(i, "display_export"));
		info.setExecuteid(rs.getLong(i, "executeid"));
		info.setWhereGroup(rs.getLong(i, "where_group"));		
		info.setMyjs(rs.getString(i, "myjs"));
		info.setDbs(rs.getString(i, "dbs"));
		info.setCacheSecond(rs.getInt(i, "cache_second"));
		info.setRefreshTime(rs.getDate(i, "refresh_time"));
		
		return info;
	}
	private synchronized static void getDataFromDB(HashMap<String, ExecutePageDatablesInfo> mapByUUID, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		MyResult rs = db.queryReturnListList(sql);
		ExecutePageDatablesInfo info = null;
		for(int i=0; i<rs.getRowCount(); i++){
			info = createInfo(rs, i);
			mapByUUID.put(info.getUuid(), info);
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, ExecutePageDatablesInfo> mapByUUID = new HashMap<String, ExecutePageDatablesInfo>();
		
		String sql = "select * from wb_page_datatables";
		getDataFromDB(mapByUUID, sql);
		datablesByUUID = mapByUUID;
		
	}
	
	public static ExecutePageDatablesInfo getDatableByUUID(String uuid) throws Exception{
		if(SysParams.isUseSysCache() || !timer.isRefreshTable()){
			if(datablesByUUID.containsKey(uuid)){
				return datablesByUUID.get(uuid);
			}
			return null;
		} 
		String sql = "select * from wb_page_datatables where uuid='" + uuid + "'";
		getDataFromDB(datablesByUUID, sql);
		timer.startClocking();
		return datablesByUUID.get(uuid); 
	}
	
	public static MyJDBC getdb(String uuid) throws Exception{
		ExecutePageDatablesInfo info = getDatableByUUID(uuid);		
		if(info== null || info.getDbs().isEmpty()){
			return null;
		}
		return SysParams.getdb(info.getDbs());
	}
	
	public static long getDataTableDescGroupId(String uuid) throws Exception{		
		ExecutePageDatablesInfo info = ExecutePageDatablesManager.getDatableByUUID(uuid);
		if(info != null){
			return info.getDescGroupid();
		}
		return -1;
	}
	
	public static List<ExecutePageDatablesDescInfo> getDataTableDesc(String uuid) throws Exception{
		long groupid = ExecutePageDatablesManager.getDataTableDescGroupId(uuid);
		if(groupid == 0 || groupid == -1){
			return null;
		}
		return ExecutePageDatablesDescManager.getDatableDescByGroupid(groupid);
	}
}
