package com.mole.webengine.dbapp;


import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyParams;

public class DataTableExecuter extends MyExecuter{
	private static HashMap<String, MyResultSetMetaData> rsms = new HashMap<String, MyResultSetMetaData>();
	
	private String getSqlFileds(long groupid) throws Exception{
		List<ExecutePageDatablesDescInfo> lst = ExecutePageDatablesDescManager.getDatableDescByGroupid(groupid);
		String sql = "";
		String field = "";
		for(int i=0; i<lst.size(); i++){
			field = lst.get(i).getField();
			if(field == null || field.equals("")){
				continue;
			}
			if(!sql.equals("")){
				sql += ", ";
			}
			sql += field;
		}
		return sql;
	}
	private String getExecuteSql(MyJDBC db, ExecutePageDatablesInfo info, 
			MyParams params) throws Exception{
		String sql = "";
		
		String sqlFields = getSqlFileds(info.getDescGroupid());
		
		sql = db.constructSelectSql(info.getTableName(), sqlFields);	
				
		sql +=  constructConditionSql(db, params, info.getWhereGroup());
		
		return sql;
	}	
	
	private ExecuteSqlAndResult getSqlFromDatatablesTable(MyJDBC db, String uuid, MyParams params) throws Exception{
		ExecutePageDatablesInfo info = ExecutePageDatablesManager.getDatableByUUID(uuid);
		if(info == null){
			return null;
		}
		if(db == null && !info.getDbs().isEmpty()){
			db = SysParams.getdb(info.getDbs());
		}
		ExecuteSqlAndResult esql = null;
		if(info.getExecuteid() == -1){
			String sql = getExecuteSql(db, info, params);
			esql = new ExecuteSqlAndResult(info.getUuid(), sql, MyJDBC.SELECT_SQL, 
					info.getCacheSecond(), info.getRefreshTime());
		} else {
			esql = constructSqlById(db, info.getExecuteid(), params);			
		}
		
		return esql;
	}	

	private ExecuteSqlAndResult getDatablesSql(MyJDBC db, String uuid, MyParams params) throws Exception{
		ExecuteSqlAndResult esql = null;
		String subact = params.getString("subact");
		if(subact != null && subact.equals("sql")){
			esql = constructSqlByUUID(db, uuid, params);
		} else {
			esql = getSqlFromDatatablesTable(db, uuid, params);
		}
		return esql;
	}
	public MyResult getDataTablesPagingData(MyJDBC db, String uuid, MyParams params, int returnType) throws Exception{
		ExecuteSqlAndResult esql = getDatablesSql(db, uuid, params);		
		MyResult rs = executeBySql(db, esql, true, true, MyJDBC.SELECT_SQL, returnType, params);
		return rs;
	}
	
	public MyResultSetMetaData getTableColumns(MyJDBC db, String uuid, MyParams params) throws Exception{
		if(rsms.containsKey(uuid)){
			return rsms.get(uuid);
		}
		ExecuteSqlAndResult esql = getDatablesSql(db, uuid, params);
		
		MyResultSetMetaData rsm = db.getQueryColumns(esql.getSql());		
		
		rsms.put(uuid, rsm);
		
		return rsm;
	}
}
