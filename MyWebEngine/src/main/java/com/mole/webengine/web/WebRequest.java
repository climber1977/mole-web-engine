package com.mole.webengine.web;

import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;

import com.mole.webengine.dbapp.ExportToExcel;
import com.mole.webengine.dbapp.ImportToDB;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.dbcore.MyResultSetMetaData;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.debug.MyException;
import com.mole.webengine.file.JSFileManager;
import com.mole.webengine.security.SecurityExcuter;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.MyParams;
import com.mole.webengine.utils.PluginManager;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.utils.StringUtils;

public abstract class WebRequest {	
	public void init(HashMap<String, DataSource> pools, String webRootPath, boolean force) throws Exception{
		Starter.init(this, pools, webRootPath);
	}

	private MyJDBC getdb(HttpServletRequest request) throws Exception{
		String dbs = RequestUtils.getParamString(request, "dbs"); 
		MyJDBC db = SysParams.getdb(dbs);		
		return db;
	}
	public MyResult requestByDB(HttpServletRequest req, HttpServletResponse resp, 
			MyJDBC db, String uuid, int rtype) throws Exception {

		MyParams params = RequestUtils.getParams(req, uuid);
		SecurityExcuter ex = new SecurityExcuter();
		
		MyResult rs = ex.executeByUUID(req, resp, db, uuid, false, false, params, rtype);
		return rs;
	}
	public MyResult requestByDBSource(HttpServletRequest req, HttpServletResponse resp, 
			String dbsource, String uuid, int rtype) throws Exception {
		MyJDBC db = MyDBPool.getMyJDBC(dbsource);
		return requestByDB(req, resp, db, uuid, rtype);
	}

	private String requestReturnJson(HttpServletRequest request, HttpServletResponse response, 
			MyJDBC db, String uuid, int rtype) throws Exception {
		String result = "";
		MyResult rs = requestByDB(request, response, db, uuid, rtype);		
		MyDebug.println("DBRequest", "defaultRequest2 sql =" +	rs.getSql());
		switch (rtype) {
		case MyJDBC.RETURN_INT:
			result = String.valueOf(rs.getRowCount());
			break;
		case MyJDBC.RETURN_LIST_LIST:
		case MyJDBC.RETURN_LIST_MAP:
			result = JsonUtils.entity2JString2(rs.getResultObject());
			break;
		}		
		return result;
	}
	
	protected abstract void initPagingParam(MyJDBC db,HttpServletRequest request, MyParams params, MyResultSetMetaData rsm);
	protected abstract String dataTableRequest(HttpServletRequest request, HttpServletResponse response, 
			MyJDBC db, String uuid, int retMap) throws Exception;
	
	protected abstract String createPage(HttpServletRequest request, HttpServletResponse response, 
			MyJDBC db, String uuid) throws Exception;
			
	public String importRequest(HttpServletRequest request, HttpServletResponse response, MyJDBC db) throws Exception {
		FileItem fileitem = UploadFiles.getUploadFirstFile(request, response);
		ImportToDB imp = new ImportToDB();
		boolean result = imp.importToDB(fileitem.getInputStream(), db);
		return result ? "1" : "0";		
	}
	public void exportRequest(HttpServletRequest request, HttpServletResponse response, MyJDBC db, String uuid) throws Exception {
		ExportToExcel ex = new ExportToExcel();
		String sql = RequestUtils.getExportSql(request, uuid);
		if(sql == ""){
			throw new MyException("WebRequest::exportRequest-->sql == []");
		}
		
		String filename = uuid + ".xls";
		response.addHeader("content-disposition", "attachment;filename=" + filename);
		response.addHeader("content_length", String.valueOf(filename.length()));
		response.addHeader("contentType", "application/octet-stream");
		OutputStream out = response.getOutputStream();
		ex.export(out, SysParams.getExportTemplatePath(), db, sql);
		
		out.flush();
		out.close();
	}
	
	public String action(HttpServletRequest request,HttpServletResponse response, String uuid) throws Exception {
		MyJDBC db = getdb(request);
		
		int rtype = RequestUtils.getParamInt(request, "rtype", 0); 		
		String act = RequestUtils.getParamString(request, "act");
		
		if (act.equals("datatable")) {
			return dataTableRequest(request, response, db, uuid, MyJDBC.RETURN_LIST_LIST);
		} 
		if (act.equals("maptable")) {
			return dataTableRequest(request, response, db, uuid, MyJDBC.RETURN_LIST_MAP);
		} 
		if (act.equals("querypage")) {
			return createPage(request, response, db, uuid);
		}
		if (act.equals("page")) {
			return createPage(request, response, db, uuid);
		}
		if (act.equals("import")) {
			return importRequest(request, response, db);
		}
		if (act.equals("export")) {
			exportRequest(request, response, db, uuid);
			return "";
		}
		if (act.equals("plugin")) {
			return PluginManager.callFun(uuid, request, response, db);
		}
		if (act.equals("jsplugin")) {
			String jsfile = RequestUtils.getParamString(request, "js"); 
			return (String) JSFileManager.callJs("jsplugin", jsfile, "request", uuid, request, response, db);		
		}
		if (act.equals("recache")) {
			return Starter.refreshCache(request, response);
		}
		return requestReturnJson(request, response, db, uuid, rtype);
	}
	
	public MyResult requestBySql(HttpServletRequest request,
			HttpServletResponse response, String sql, boolean order,
			boolean paging, int requestType, int returnType) throws Exception {

		MyJDBC db = getdb(request);		
		MyParams params = RequestUtils.getParams(request, "");
		SecurityExcuter ex = new SecurityExcuter();
		if(paging){
			//这里设置用于排序
			MyResultSetMetaData rsm = db.getQueryColumns(sql);			
			initPagingParam(db, request, params, rsm);
		}
		
		MyResult rs = ex.executeBySql(request, response, db, sql, order, paging, params, requestType, returnType);
		return rs;
	}
	public MyResult requestBySqlWithParam(HttpServletRequest request,
			HttpServletResponse response, String sql, boolean order,
			boolean paging, int requestType, int returnType) throws Exception {

		MyJDBC db = getdb(request);		
		MyParams params = RequestUtils.getParams(request, "");
		sql = StringUtils.formateTemplate(sql, params, false, db.getSingleQuoteTransfer(), false);
		return requestBySql(request, response, sql, order,
				paging, requestType, returnType);
	}
	public MyResult requestByUUID(HttpServletRequest request,
			HttpServletResponse response, String uuid, boolean order,
			boolean paging, int returnType) throws Exception {

		MyJDBC db = getdb(request);		
		MyParams params = RequestUtils.getParams(request, "");	
		SecurityExcuter ex = new SecurityExcuter();
		if(paging){
			//这里设置用于排序
			MyResultSetMetaData rsm = ex.getQueryColumns(db, uuid, params);
			initPagingParam(db, request, params, rsm);
		}
		
		MyResult rs = ex.executeByUUID(request, response, db, uuid, order, paging, params, returnType);
		return rs;
	}
}
