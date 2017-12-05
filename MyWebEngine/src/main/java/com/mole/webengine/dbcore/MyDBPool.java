package com.mole.webengine.dbcore;


import java.util.HashMap;

import javax.sql.DataSource;

import com.mole.webengine.debug.MyException;

public class MyDBPool {
	private static HashMap<String, DataSource> pools;
	
	public  HashMap<String, DataSource> getPools() {
		return pools;
	}
	public  void setPools(HashMap<String, DataSource> pools) {
		init(pools);
	}
	public static void init(HashMap<String, DataSource> pools){
		MyDBPool.pools = pools;		
	}
	private static int getDBType(DataSource ds){
		String s = ds.toString().toLowerCase();
		if(s.toString().indexOf("mysql") >= 0){
			return MyJDBC.DB_TYPE_MYSQL;
		} else if(s.toString().indexOf("oracle") >= 0){
			return MyJDBC.DB_TYPE_ORACLE;
		}
		return 0;
	}
	public static MyJDBC getMyJDBC(String dbsource) throws Exception{
		DataSource ds = null; 
		if(!pools.containsKey(dbsource)){
			return null; 
		} else {
			ds = pools.get(dbsource);
		}
		
		int dbtype = getDBType(ds);
		switch(dbtype){
		case MyJDBC.DB_TYPE_MYSQL:
			return new MyMysqlJDBC(ds, dbtype, dbsource);
		case MyJDBC.DB_TYPE_ORACLE:
			return new MyOracleJDBC(ds, dbtype, dbsource);
		} 
		throw new MyException("MyDBPool::getMyJDBC-->unkown ", dbtype, " type of database");
	}
}
