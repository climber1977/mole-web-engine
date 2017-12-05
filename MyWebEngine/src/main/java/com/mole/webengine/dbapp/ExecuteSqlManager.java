package com.mole.webengine.dbapp;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;

public class ExecuteSqlManager {
	private static HashMap<String, ExecuteSqlInfo> sqlsByUUID = new HashMap<String, ExecuteSqlInfo>();	
	private static HashMap<Long, ExecuteSqlInfo> sqlsById = new HashMap<Long, ExecuteSqlInfo>();
	private static MyTimer timer = new MyTimer();
	
	private static ExecuteSqlInfo createInfo(MyResult rs, int i) throws Exception{
		ExecuteSqlInfo info = new ExecuteSqlInfo();
		info.setId(rs.getLong(i, "id"));
		info.setUuid(rs.getString(i, "uuid"));
		info.setExpression(rs.getString(i, "expression"));
		info.setDbs(rs.getString(i, "dbs"));
		info.setWhereGroup(rs.getLong(i, "where_group"));
		info.setRequestType(rs.getInt(i, "request_type"));
		info.setKeepOriginal(rs.getBoolean(i, "keep_original"));
		info.setCacheSecond(rs.getInt(i, "cache_second"));
		info.setRefreshTime(rs.getDate(i, "refresh_time"));
		
		return info;
	}
	private synchronized static void getDataFromDB(HashMap<String, ExecuteSqlInfo> mapSqlsByUUID, 
			HashMap<Long, ExecuteSqlInfo> mapSqlsById, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		
		MyResult rs = db.queryReturnListList(sql);
		for(int i=0; i<rs.getRowCount(); i++){
			ExecuteSqlInfo info = createInfo(rs, i);
			mapSqlsByUUID.put(info.getUuid(), info);
			mapSqlsById.put(info.getId(), info);
		}	
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, ExecuteSqlInfo> mapSqlsByUUID = new HashMap<String, ExecuteSqlInfo>();	
		HashMap<Long, ExecuteSqlInfo> mapSqlsById = new HashMap<Long, ExecuteSqlInfo>();	
		
		String sql = "select * from wb_execute_sql";
		getDataFromDB(mapSqlsByUUID, mapSqlsById, sql);
		sqlsByUUID = mapSqlsByUUID;
		sqlsById = mapSqlsById;
	}
	
	public static ExecuteSqlInfo getSqlByUUID(String uuid) throws Exception{
		if(SysParams.isUseSysCache() || !timer.isRefreshTable()){
			if(sqlsByUUID.containsKey(uuid)){
				return sqlsByUUID.get(uuid);
			}
		}

		String sql = "select * from wb_execute_sql where uuid='" + uuid + "'";
		getDataFromDB(sqlsByUUID, sqlsById, sql);
		timer.startClocking();
		
		return sqlsByUUID.get(uuid);
	}
	public static MyJDBC getdb(String uuid) throws Exception{
		ExecuteSqlInfo info = getSqlByUUID(uuid);
		if(info == null || info.getDbs().isEmpty()){
			return null;
		}
		return SysParams.getdb(info.getDbs());
	}
	public static ExecuteSqlInfo getSqlById(long id) throws Exception{
		if(SysParams.isUseSysCache() || !timer.isRefreshTable()){
			if(sqlsById.containsKey(id)){
				return sqlsById.get(id);
			}
		}

		String sql = "select * from wb_execute_sql where id=" + id + "";
		getDataFromDB(sqlsByUUID, sqlsById, sql);
		timer.startClocking();
		
		return sqlsById.get(id);
	}
}
