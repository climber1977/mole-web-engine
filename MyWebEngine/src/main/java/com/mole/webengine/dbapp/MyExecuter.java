package com.mole.webengine.dbapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyParams;
import com.mole.webengine.utils.StringUtils;


public class MyExecuter {
	final static Logger logger = LoggerFactory.getLogger(MyExecuter.class);
	class Condition{
		String conj;
		String expression;
		boolean keepOriginal;
	}
	
	protected String constructConditionSql(MyJDBC db, MyParams params, long whereGroup) throws Exception{
		if(whereGroup == -1){
			return "";
		}
		List<ExecuteWhereSqlInfo> conditions = ExecuteWhereSqlManager.getWhereByGroupid(whereGroup);

		String conditionSql = "";
		boolean firstCondition = true;
		for(int i =0; i<conditions.size(); i++){
			String sql = StringUtils.formateTemplate(conditions.get(i).getExpression(), params, false, 
					db.getSingleQuoteTransfer(), conditions.get(i).isKeepOriginal());			
			if(sql.equals("")){
				continue;
			}
			if(conditionSql.equals("") && ! conditions.get(i).getConj().equals("null")){
				conditionSql += " where ";
			}
			conditionSql += " ";
			if(!conditionSql.equals("") && !conditions.get(i).getConj().equals("null") && !firstCondition){
				conditionSql += conditions.get(i).getConj() + " ";
			}
			conditionSql += sql;
			
			firstCondition = false;
		}
		return conditionSql;
	}	
	public MyResult executeBySql(MyJDBC db, String sql, boolean order, boolean paging, int requestType, int returnType, MyParams params) throws Exception{
		MyResult rs = null;
		if(requestType== MyJDBC.SELECT_SQL){
			String orderPaingSql = appendSqlOrderByPaging(db, sql, order, paging, params);
			if(returnType == MyJDBC.RETURN_LIST_LIST){
				rs = db.queryReturnListList(orderPaingSql, paging);
			}else{
				rs = db.queryReturnMap(orderPaingSql, paging);
			}
		}else{
			rs = db.executeUpdate(sql);
		}
		rs.setNoPagingSql(sql);
		
		return rs;
	}
	public MyResult executeBySql(MyJDBC db, ExecuteSqlAndResult esql, boolean order, boolean paging, int requestType, int returnType, MyParams params) throws Exception{
		if(esql == null || esql.getSql() == null || esql.getSql().equals("")){
			return null;
		}
		
		String orderPaingSql = appendSqlOrderByPaging(db, esql.getSql(), order, paging, params);
		esql.setSql(orderPaingSql);
		MyResult rs = MyJDBCCache.getResult(esql, params);
		if(rs != null){
			return rs;
		}
		
		rs = executeBySql(db, orderPaingSql, order, paging, requestType, returnType, params);
		
		MyJDBCCache.putCache(esql, rs);
		
		rs.setNoPagingSql(esql.getSql());
		return rs;
	}
	public MyResult executeByUUID(MyJDBC db, String uuid, boolean order, boolean paging, int returnType, MyParams params) throws Exception{
		ExecuteSqlAndResult esql = constructSqlByUUID(db, uuid, params);
		
		MyResult rs = executeBySql(db, esql, order, paging, esql.getRequestType(), returnType, params);
		
		return rs;
	}	
	protected String getOrderBySql(MyParams params){
		String sql = "";
		String orderField = params.getString("orderField");
		if(orderField == null){
			return "";
		}
		sql += " order by ";
		sql += orderField;
				
		String orderType = params.getString("orderType");
		if(orderType != null){
			sql += " ";
			sql += orderType;
		}
		
		return sql;
	}
	
	private ExecuteSqlAndResult constructSql(MyJDBC db, ExecuteSqlInfo sqlInfo, MyParams params) throws Exception{
		String sql = StringUtils.formateTemplate(sqlInfo.getExpression(), params, false, db.getSingleQuoteTransfer(), sqlInfo.isKeepOriginal());
		if(sql.equals("")){
			logger.error("formate is error. expression=" + sqlInfo.getExpression());
			logger.error("formate is error. params=" + params.toString());
			return null;
		}
		
		sql += constructConditionSql(db, params, sqlInfo.getWhereGroup());		
		sql = StringUtils.modifySql(sql);
		
		ExecuteSqlAndResult esql = new ExecuteSqlAndResult(sqlInfo.getUuid(), sql, sqlInfo.getRequestType(), 
				sqlInfo.getCacheSecond(), sqlInfo.getRefreshTime());	
		return esql;
	}
	protected ExecuteSqlAndResult constructSqlById(MyJDBC db, long id, MyParams params) throws Exception{
		ExecuteSqlInfo sqlInfo = ExecuteSqlManager.getSqlById(id);
		if(sqlInfo == null){
			logger.error("getExecuteSqlByUid cann't find item by " + id);
			return null;
		}
		if(db == null && !sqlInfo.getDbs().isEmpty()){
			db = SysParams.getdb(sqlInfo.getDbs());
		}
		if(db == null){
			logger.error("getExecuteSqlByUid db == null");
			throw new MyException("getExecuteSqlByUid db == null");
		}
		return constructSql(db, sqlInfo, params);
		
	}
	protected ExecuteSqlAndResult constructSqlByUUID(MyJDBC db, String uuid, MyParams params) throws Exception{
		ExecuteSqlInfo sqlInfo = ExecuteSqlManager.getSqlByUUID(uuid);
		if(sqlInfo == null){
			logger.error("getExecuteSqlByUid cann't find item by " + uuid);
			return null;
		}
		if(db == null && !sqlInfo.getDbs().isEmpty()){
			db = SysParams.getdb(sqlInfo.getDbs());
		}
		return constructSql(db, sqlInfo, params);
	}
	public MyResultSetMetaData getQueryColumns(MyJDBC db, String uuid, MyParams params) throws Exception{
		ExecuteSqlInfo sqlInfo = ExecuteSqlManager.getSqlByUUID(uuid);
		String sql = sqlInfo.getExpression();
		if(db == null && !sqlInfo.getDbs().isEmpty()){
			db = SysParams.getdb(sqlInfo.getDbs());
		}
		sql = StringUtils.formateTemplate(sql, params, false, db.getSingleQuoteTransfer(), false);
		return db.getQueryColumns(sql);
	}
	public String appendSqlOrderByPaging(MyJDBC db, String sql, boolean order, boolean paging, MyParams params) throws Exception{	
		if(db.isAddPagingSql()){
			return sql;
		}
		if(paging){
			sql = db.addCalcFoundRowsOfPaginSql(sql);
		}
		if(order && !StringUtils.matchOrder(sql)){
			sql += getOrderBySql(params);
		}
		
		db.setAddPagingSql(true);
		
		if(paging){
			Integer startPageIndex = params.getInt("start");		
			Integer pageCount = params.getInt("length");
			if(startPageIndex != null && pageCount != null && pageCount != -1){
				return db.getPagingSql(sql, startPageIndex, pageCount);			
			}
		}
		
		return sql;
	}
}
