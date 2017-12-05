package com.mole.webengine.dbapp;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyParams;

public class MyJDBCCache {
	private static final Logger logger = LoggerFactory.getLogger(MyJDBCCache.class);
	private static HashMap<String, ExecuteSqlAndResult> cache = new HashMap<String, ExecuteSqlAndResult>();	
	
	public static void putCache(ExecuteSqlAndResult esql, MyResult rs) throws Exception{
		if(!SysParams.isUseSelectCahce()){
			return;
		}
		if(!esql.isNeedCache()){
			return;
		}
		esql.setMyResult(rs);
		String key = esql.generateKey();
		synchronized (cache) {
			cache.put(key, esql);
		}
	}
	public static MyResult getResult(ExecuteSqlAndResult esql, MyParams params) throws Exception{
		if(!SysParams.isUseSelectCahce()){
			return null;
		}
		
		if(params.getString("forcequery") != null && params.getString("forcequery").equals("fromdatabase")){
			return null;
		}
		String key = esql.generateKey();
		if(!cache.containsKey(key)){
			return null;
		}
		ExecuteSqlAndResult esql2 = cache.get(key);
		//当不使用缓存时，会从数据库重新读取记录，所以需重新设置
		esql2.setCacheSecond(esql.getCacheSecond());
		esql2.setRefreshTime(esql.getRefreshTime());
		MyResult rs = esql2.getMyResult();
		
		if(rs != null){
			logger.info("from cache:" + esql.getSql());
		}
		
		return rs;
	}
}
