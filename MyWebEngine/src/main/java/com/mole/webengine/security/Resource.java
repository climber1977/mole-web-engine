package com.mole.webengine.security;


import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;
import com.mole.webengine.utils.Pair;

public class Resource {
	private static MyResult reses = null;
	private static MyTimer timer = new MyTimer();
	
	public synchronized static void init() throws Exception{
		if(reses != null && !timer.isRefreshTable()){
			return;
		}		
		String sql = "select * from wb_res order by priority DESC";	
		reses = SysParams.getSysdb().queryReturnMap(sql);
		timer.startClocking();
	}
	
	public static Pair<Long, Boolean> getResId(String url) throws Exception{
		if(!SysParams.isUseSysCache()){
			//一次请求，访问的资源会较多，所以重新初始化，而不是多次请求数据库
			init();
		}
		for(int i=0; i<reses.getRowCount(); i++){
			String regex = reses.getString(i, "url") + ".*?";
			if(url.matches(regex)){
				if(reses.getBoolean(i, "is_white_list")){
					return Pair.of(-1L, false);
				}
				return Pair.of(reses.getLong(i, "id"), reses.getBoolean(i, "login_permite"));
			}
		}
		return Pair.of(-1L, false);
	}
}
