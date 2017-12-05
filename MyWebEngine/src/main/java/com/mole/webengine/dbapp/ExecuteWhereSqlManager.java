package com.mole.webengine.dbapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;

public class ExecuteWhereSqlManager {
	private static HashMap<Long, List<ExecuteWhereSqlInfo>>  wheres = new HashMap<Long, List<ExecuteWhereSqlInfo>>();

	private static ExecuteWhereSqlInfo createInfo(MyResult rs, int i) throws Exception{
		ExecuteWhereSqlInfo info = new ExecuteWhereSqlInfo();
		info.setId(rs.getLong(i, "id"));		
		info.setWhereGroup(rs.getLong(i, "where_group"));
		info.setConj(rs.getString(i, "conj"));
		info.setExpression(rs.getString(i, "expression"));
		info.setKeepOriginal(rs.getBoolean(i, "keep_original"));
		
		return info;
	}
	//clean 第一条分组数据，需要清除数据
	private static void put(HashMap<Long, List<ExecuteWhereSqlInfo>> mapWheres, 
			ExecuteWhereSqlInfo info, boolean clean) throws Exception{
		List<ExecuteWhereSqlInfo> lst = null;
		if(mapWheres.containsKey(info.getWhereGroup())){
			lst = mapWheres.get(info.getWhereGroup());		
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<ExecuteWhereSqlInfo>();
			mapWheres.put(info.getWhereGroup(), lst);
		}
		lst.add(info);
	}
	private synchronized static void getDataFromDB(HashMap<Long, List<ExecuteWhereSqlInfo>> mapWheres, 
			String sql, boolean clean) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		MyResult rs = db.queryReturnListList(sql);
		ExecuteWhereSqlInfo info = null;
		for(int i=0; i<rs.getRowCount(); i++){
			info = createInfo(rs, i);
			put(mapWheres, info, clean);
			clean = false;
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<Long, List<ExecuteWhereSqlInfo>> mapWheres = new HashMap<Long, List<ExecuteWhereSqlInfo>>();
		
		String sql = "select * from wb_execute_where_sql order by where_group, show_order";
		getDataFromDB(mapWheres, sql, false);
		wheres = mapWheres; 
	}
	
	public static List<ExecuteWhereSqlInfo> getWhereByGroupid(long groupid) throws Exception{
		if(SysParams.isUseSysCache()){
			if(wheres.containsKey(groupid)){
				return wheres.get(groupid);
			}
		}
		
		String sql = "select * from wb_execute_where_sql where where_group=" + groupid
				+ " order by show_order";
		getDataFromDB(wheres, sql, true);
		
		return wheres.get(groupid);
	}
}
