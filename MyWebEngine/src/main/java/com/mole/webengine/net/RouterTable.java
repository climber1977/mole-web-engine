package com.mole.webengine.net;

import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;
import com.mole.webengine.utils.Pair;
import com.mole.webengine.utils.StringUtils;

public class RouterTable {
	private static MyResult routerTable = null; 
	private static MyTimer timer = new MyTimer();
	
	class MyRouters{
		String urlRegex;
		String remoteIp;
		int remotePort;
	}
	
	public synchronized static void init() throws Exception{
		if(routerTable != null && !timer.isRefreshTable()){
			return;	
		}
		String sql = "select * from wb_router";		
		routerTable = SysParams.getSysdb().queryReturnMap(sql);
		timer.startClocking();
	}
	
	public static Pair<String, Integer> getIpPort(String url) throws Exception{
		if(!SysParams.isUseSysCache()){
			init();
		}
		int i=0;		
		for(i=0; routerTable !=null && i<routerTable.getRowCount(); i++){
			String regex = routerTable.getString(i, "regex")  + ".*?";
			if(url.matches(regex)){
				return Pair.of(routerTable.getString(i, "remote_ip"), routerTable.getInt(i, "remote_port"));
			}
		}
		return null;		
    }
	
	public static String getDestinationlUrl(String url) throws Exception{
		if(!SysParams.isUseSysCache()){
			//url不能精确定位，所以重新初始化
			init();
		}
		for(int i=0; i<routerTable.getRowCount(); i++){
			String regex = routerTable.getString(i, "regex")  + ".*?";
			if(url.matches(regex)){
				regex = routerTable.getString(i, "regex");
				return StringUtils.getDestinationlUrl(url, routerTable.getString(i, "remote_prefix_url"), regex);
			}
		}
		throw new MyException("RouterTable::getDestinationlUrl-->cann't found match url=", url);
	}	
}
