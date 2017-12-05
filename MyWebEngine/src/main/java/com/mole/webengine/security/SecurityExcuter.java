package com.mole.webengine.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mole.webengine.dbapp.DataTableExecuter;
import com.mole.webengine.dbapp.ExecuteSqlInfo;
import com.mole.webengine.dbapp.ExecuteSqlManager;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyParams;


public class SecurityExcuter extends DataTableExecuter {

	public MyResult executeByUUID(HttpServletRequest request,
			HttpServletResponse response, MyJDBC db, String uuid, boolean order,
			boolean paging, MyParams params, int returnType) throws Exception {
		if(SysParams.isUseColumnPrivilege()){
			ExecuteSqlInfo sqlInfo = ExecuteSqlManager.getSqlByUUID(uuid);
			if(sqlInfo == null){
				throw new MyException("SecurityExcuter::execute-->can't not find the sql by ", uuid, " of uuid");
			}
			String sql = sqlInfo.getExpression();
			if(sql.equals("")){
				return null;
			}
			if(db == null && !sqlInfo.getDbs().isEmpty()){
				db = SysParams.getdb(sqlInfo.getDbs());
			}
			SecurityUser.initColumnPrivilege(db, true);
		}
		
		return super.executeByUUID(db, uuid, order, paging, returnType, params);
	}

	public MyResult executeBySql(HttpServletRequest request,
			HttpServletResponse response, MyJDBC db, String sql, 
			boolean order, boolean paging, MyParams params, int requestType, int returnType) throws Exception {
		
		SecurityUser.initColumnPrivilege(db, true);
		
		return super.executeBySql(db, sql, order, paging, requestType, returnType, params);
	}
	
	public MyResult secrityGetDataTablesPagingData(HttpServletRequest request,
			HttpServletResponse response, MyJDBC db, String uuid, MyParams params, int returnType)
			throws Exception {		
		if(SysParams.isUseColumnPrivilege()){
			String strShowColumn = request.getParameter("showColumn");
			boolean isShowColumn = true;
			if(strShowColumn != null){
				isShowColumn = (Integer.valueOf(strShowColumn) == 1);			
			}
			
			SecurityUser.initColumnPrivilege(db, isShowColumn);
		}
		
		return super.getDataTablesPagingData(db, uuid, params, returnType);
	}	
}
