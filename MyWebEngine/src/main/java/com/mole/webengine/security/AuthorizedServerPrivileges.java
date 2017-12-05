package com.mole.webengine.security;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;
import com.mole.webengine.utils.RequestUtils;


public class AuthorizedServerPrivileges {	
	private static HashMap<String, Long> servers = null;
	private static MyTimer timer = new MyTimer();
	
	private static String generateKey(String ip, String secretKey){
		return ip + "_" + secretKey;
	}
	private static void getDataFromDB(HashMap<String, Long> mapServers, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();

		MyResult rs = db.queryReturnListList(sql);
		String key = null;
		for(int i=0; i<rs.getRowCount(); i++){
			if(rs.getBoolean(i, "is_delete")){
				continue;
			}
			key = generateKey(rs.getString(i, "ip"), rs.getString(i, "secret_key"));
			mapServers.put(key, 0L);
		}
	}
	public synchronized static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, Long> mapServers = new HashMap<String, Long>();
		String sql = "select * from wb_authorized_server";
		getDataFromDB(mapServers, sql);
		servers = mapServers;
	}
	
	private static boolean isAuthorized(String ip, String secretKey) throws Exception{
		String key = generateKey(ip, secretKey);
		if(SysParams.isUseSysCache() && !timer.isRefreshTable()){
			return servers.containsKey(key);
		}
		
		String sql = "select * from wb_authorized_server where is_delete=0 " 
				+ " and ip='" + ip + "'"
				+ " and secret_key='" + secretKey + "'";
		getDataFromDB(servers, sql);
		timer.startClocking();
		return  servers.containsKey(key);
	}
	public static boolean isAuthorized(HttpServletRequest request) throws Exception{		
		String ip = request.getRemoteAddr();
		String secretKey = RequestUtils.getParamString(request, "secretKey");
		return isAuthorized(ip, secretKey);		
	}
}
