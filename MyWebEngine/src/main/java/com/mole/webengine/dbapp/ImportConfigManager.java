package com.mole.webengine.dbapp;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;

public class ImportConfigManager {
	private static HashMap<String, ImportConfigInfo> configs = new HashMap<String, ImportConfigInfo>();	

	private static ImportConfigInfo createInfo(MyResult rs, int i) throws Exception{
		ImportConfigInfo info = new ImportConfigInfo();
		info.setId(rs.getLong(i, "id"));
		info.setTableName(rs.getString(i, "table_name"));
		info.setIdField(rs.getString(i, "id_field"));	
		info.setGenerateIdSql(rs.getString(i, "generate_id_sql"));
		info.setUniqueField(rs.getString(i, "unique_field"));
		
		return info;
	}
	private synchronized static void getDataFromDB(HashMap<String, ImportConfigInfo> mapconfigs, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		
		MyResult rs = db.queryReturnListList(sql);
		ImportConfigInfo info = null;
		for(int i=0; i<rs.getRowCount(); i++){
			info = createInfo(rs, i);
			configs.put(info.getTableName(), info);
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, ImportConfigInfo> mapconfigs = new HashMap<String, ImportConfigInfo>();	
		
		String sql = "select * from wb_import_config";
		getDataFromDB(mapconfigs, sql);
		configs = mapconfigs;
	}
	
	public static ImportConfigInfo getDatableByUUID(String tableName) throws Exception{
		if(SysParams.isUseSysCache()){
			if(configs.containsKey(tableName)){
				return configs.get(tableName);
			}
		}

		String sql = "select * from wb_import_config where table_name='" + tableName + "'";
		getDataFromDB(configs, sql);
		return configs.get(tableName);
	}
}
