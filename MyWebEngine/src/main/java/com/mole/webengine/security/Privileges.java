package com.mole.webengine.security;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;

public class Privileges<K> {
	private HashMap<K, HashMap<Long, Long>> privileges = new HashMap<K, HashMap<Long, Long>>();
	private String sql = null;
	private String firstIdFiled = null;
	private String secondIdFiled = null;
	
	private static MyTimer timer = new MyTimer();
	
	@SuppressWarnings("unchecked")
	public synchronized void init() throws Exception{
		if(privileges != null && !timer.isRefreshTable()){
			return;	
		}
		//不使用clear，避免多线程冲突
		HashMap<K, HashMap<Long, Long>> mapPrivileges = new HashMap<K, HashMap<Long, Long>>();
		MyResult rs = SysParams.getSysdb().queryReturnListList(sql);
		K firstId;
		for(int i=0; i<rs.getRowCount(); i++){
			firstId = (K)rs.getValue(i, firstIdFiled);
			if(!mapPrivileges.containsKey(firstId)){
				HashMap<Long, Long> m = new HashMap<Long, Long>();
				mapPrivileges.put(firstId, m);
			}
			mapPrivileges.get(firstId).put(rs.getLong(i, secondIdFiled), Long.valueOf(1));
		}
		privileges = mapPrivileges;
		timer.startClocking();
	}
	
	public synchronized void init(String sql, String firstIdFiled, String secondIdFiled) throws Exception{
		this.sql = sql;
		this.firstIdFiled = firstIdFiled;
		this.secondIdFiled = secondIdFiled;
		
		init();
	}
	
	public boolean hasPrivilege(K firstId, Long secondId) throws Exception{
		if(!SysParams.isUseSysCache()){
			//一次请求，访问的菜单会较多，所以重新初始化，而不是多次请求数据库
			init();
		}
		if(!privileges.containsKey(firstId)){
			return true;
		}
		if(privileges.get(firstId).containsKey(secondId)){
			return true;
		}		
		return false;
	}
	
	public boolean containsKey(K firstId){
		return privileges.containsKey(firstId);
	}
	
	public boolean hasPrivilegeFromDB(String sql) throws Exception{		
		MyResult rs;
		rs = SysParams.getSysdb().queryReturnListList(sql);
		if(rs.getRowCount() >= 1){
			return true;
		}		
		return false;
	}
	
	public boolean hasPrivilegeFromDB(String sql, Long secondId) throws Exception{		
		MyResult rs;
		rs = SysParams.getSysdb().queryReturnListList(sql);
		if(rs.getRowCount() == 0){
			return true;
		}
		for(int i=0; i<rs.getRowCount(); i++){
			if(secondId == rs.getLong(i, 0)){
				return true;
			}
		}
		
		return false;
	}
}
