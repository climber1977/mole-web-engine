package com.mole.plugin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.system.GlobalContext;
import com.mole.webengine.utils.MyParams;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.web.MyWebRequest;

public class MyTestPlugin {
	@SuppressWarnings("unchecked")
	public String queryUsers(String uuid, HttpServletRequest request,HttpServletResponse response, MyJDBC db) throws Exception {
		System.out.println("GlobalContext.getGlobal(\"test\")=" + GlobalContext.getGlobal("test"));
		String sql = "select ID, work_code, name, ch_name, en_name from wb_user";
		
		sql += " where id>=12";
		sql += " order by id";		
		
		MyParams params = RequestUtils.getParams(request, "");
		Integer startPageIndex = params.getInt("start");		
		Integer pageCount = params.getInt("length");
		sql = db.getPagingSql(sql, startPageIndex, pageCount);	
		
		sql = db.addCalcFoundRowsOfPaginSql(sql);
		
		SecurityUser.initColumnPrivilege(db, true);
		MyResult rs = db.queryReturnListList(sql, true);
		return MyWebRequest.ConvertListPaginResult((List<List<Object>>)rs.getResultObject(), rs.getFoundRows(), request);	
	}
	
}
