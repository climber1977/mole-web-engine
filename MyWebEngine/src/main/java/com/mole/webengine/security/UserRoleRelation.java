package com.mole.webengine.security;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.system.SysParams;

public class UserRoleRelation {
	private static HashMap<Long, List<Long>> userRoles = new HashMap<Long, List<Long>>();
	
	private static void put(long userid, long roleid) throws Exception{
		List<Long> lst = null;
		synchronized (userRoles) {
			if(userRoles.containsKey(userid)){
				lst = userRoles.get(userid);
			} else {
				lst = new ArrayList<Long>();
				userRoles.put(userid, lst);
			}
			lst.add(roleid);
		}
	}
	private static void getDataFromDB(String sql) throws Exception{	
		MyJDBC db = SysParams.getSysdb();
		db.Connection();
		try {
			ResultSet rs = db.executeQuery(sql);
			
			long userid = 0;
			long roleid = 0;
			
			while(rs.next()){
				userid = rs.getLong("userid");
				roleid = rs.getLong("roleid");
				put(userid, roleid);
			}
			
		} finally {
			db.DisconnectDB();
		}	
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		userRoles = new HashMap<Long, List<Long>>();
		
		String sql = "select * from wb_user_role_relation";
		getDataFromDB(sql);
	}
	
	public static List<Long> getDatableDescByGroupid(long userid) throws Exception{
		if(SysParams.isUseSysCache()){
			if(userRoles.containsKey(userid)){
				return userRoles.get(userid);
			}
		}
		
		String sql = "select * from wb_user_role_relation " +
				" where userid=" + userid ;
		getDataFromDB(sql);
		
		if(userRoles.containsKey(userid)){
			return userRoles.get(userid);
		}
		
		return new ArrayList<Long>();
	}
}
