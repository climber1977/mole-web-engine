package com.mole.webengine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import com.mole.webengine.dbapp.ExecutePageDatablesManager;
import com.mole.webengine.dbapp.ExecuteSqlManager;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.resmanager.I18n;
import com.mole.webengine.resmanager.PageChartManager;
import com.mole.webengine.resmanager.PageInfo;
import com.mole.webengine.resmanager.PageManager;
import com.mole.webengine.resmanager.PagePortManager;
import com.mole.webengine.security.SecurityExcuter;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.MyParams;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.web.WebRequest;
import com.mole.webengine.web.tld.MyBase;
import com.mole.webengine.web.tld.MyTable;

public class MyWebRequest extends WebRequest{	
	
	protected void initPagingParam(MyJDBC db,HttpServletRequest request, MyParams params, MyResultSetMetaData rsm){
		int orderColumn = RequestUtils.getParamInt(request, "order[0][column]");
		if(orderColumn >= rsm.getColumnCount()){
			orderColumn = 0;
		}
		if(orderColumn != -1 && !params.containsKey("orderField")){			
			params.put("orderField", rsm.getColumnName(orderColumn));
			String orderType = RequestUtils.getParamString(request, "order[0][dir]");
			params.put("orderType", orderType);
		}
	}
	
	//转换为分页结果
	public static String ConvertListPaginResult(List<List<Object>> lst, int recordsTotal, HttpServletRequest request) throws Exception{
		
		String draw = request.getParameter("draw");
		JSONObject jo = new JSONObject();
		jo.put("data", lst);
		if(draw != null){
			jo.put("draw", Integer.valueOf(draw) + 1);
		}		
		jo.put("recordsTotal", lst.size());
		jo.put("recordsFiltered", recordsTotal);
		String result = JsonUtils.entity2JString2(jo);
		return result;
	}
	
	public static String ConvertMapPaginResult(MyResult rs, HttpServletRequest request) throws Exception{		
		JSONObject jo = new JSONObject();
		jo.put("data", rs.getResultObject());
		jo.put("total", rs.getFoundRows());
		String result = JsonUtils.entity2JString2(jo);
		return result;
	}
		
	//data的请求处理入口
	protected String dataTableRequest(HttpServletRequest request, HttpServletResponse response, 
			MyJDBC db, String uuid, int returnType) throws Exception {	
		MyParams params = RequestUtils.getParams(request, uuid);
		if(db == null){
			db = ExecuteSqlManager.getdb(uuid);
		}
		if(db == null){
			db = ExecutePageDatablesManager.getdb(uuid);
		}		
		SecurityExcuter ex = new SecurityExcuter();
		//这里设置用于排序
		MyResultSetMetaData rsm = ex.getTableColumns(db, uuid, params);
		initPagingParam(db, request, params, rsm);
		
		MyResult rs = ex.secrityGetDataTablesPagingData(request, response, db, uuid,
			params, returnType);
		
		if (rs == null) {
			return "error";
		}
		//保存最后执行的sql，datatables导出时使用
		RequestUtils.saveExportSql(request, rs.getNoPagingSql(), uuid);		
		
		if(returnType == MyJDBC.RETURN_LIST_MAP){
			return ConvertMapPaginResult(rs, request);
		}else {
			List<List<Object>> lst = MyTable.formatResult(uuid, rs);	
			return ConvertListPaginResult(lst, rs.getFoundRows(), request);
		}
	}
	
	public String createPage(HttpServletRequest request,
			HttpServletResponse response, MyJDBC db,
			String uuid) throws Exception {
		//还未从数据库获取新的uuid
		List<PageInfo> pages = PageManager.getPage(uuid);
		if(pages.size() <= 0){
			return "cann't find " + uuid + " from wb_page";
		}
		long langueIndex = I18n.getLanague(request);
		String result = "";
		
		String zoneUUID = null;
		String zoneType = null;
		for(int i=0; i<pages.size(); i++){
			zoneUUID = pages.get(i).getZoneUUID();
			zoneType = pages.get(i).getZoneType();
			if(zoneType.equals("port")){
				JSONObject jmain = PagePortManager.getPortalJson(zoneUUID);
				result += MyBase.createPortal(jmain, 0, null, "");
			} else if(zoneType.equals("datatable")){
				String dataUrl = MyBase.getUrl("", zoneUUID, "showColumn=0");
				result += MyTable.createTable("", db, zoneUUID, 
						"", dataUrl, langueIndex);
			} else if(zoneType.equals("chart")){
				JSONObject jmain = PageChartManager.getChartJson(zoneUUID);
				result += MyBase.createPortal(jmain, 0, null, "");
			}
		}
		
		return result;
	}
}
