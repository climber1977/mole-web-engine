package com.mole.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.net.HttpClientUtil;
import com.mole.webengine.resmanager.I18n;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.utils.ResponseUtils;
import com.mole.webengine.utils.SystemUtils;
import com.mole.webengine.web.MyWebRequest;


@Controller
@RequestMapping(method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
public class MyController {
	private static MyWebRequest web = new MyWebRequest();
	
	//数据库连接池 使用C3P0数据库连接池 使用named-config模式 则通过有参构造函数初始化  带上name  
	static { 
		init();
	}
	
	private static HashMap<String, DataSource> getDBPools(){
		HashMap<String, DataSource> pools = new HashMap<String, DataSource>();
		ComboPooledDataSource ds = null;
		ds = new ComboPooledDataSource("web_engine");	
		pools.put("system", ds);
		pools.put("hr", ds);
		ds = new ComboPooledDataSource("web_test");	
		pools.put("wt", ds);
		ds = new ComboPooledDataSource("web_oracle");	
		pools.put("wo", ds);
		ds = new ComboPooledDataSource("oa");
		pools.put("oa", ds);
		ds = new ComboPooledDataSource("erp");
		pools.put("erp", ds);
		ds = new ComboPooledDataSource("erptest");
		pools.put("erptest", ds);
		ds = new ComboPooledDataSource("erpatest");
		pools.put("erpatest", ds);
		ds = new ComboPooledDataSource("hrplatform");
		pools.put("hp", ds);
		ds = new ComboPooledDataSource("task");
		pools.put("task", ds);
		ds = new ComboPooledDataSource("portal");
		pools.put("portal", ds);
		return pools;
	}
	
	private static void init() {
		try {
			//当前运行目录的，的相对目录
			String path = "dbconfig.xml";
			if(SystemUtils.isTomcatApp()){
				path = SystemUtils.getResourcePath() + path;
			} 
			System.setProperty("com.mchange.v2.c3p0.cfg.xml",path);
			
			HashMap<String, DataSource> pools = getDBPools();
			
			path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
			web.init(pools, path, true);
			
			
		} catch (Exception e) {
			MyDebug.dealException("MyController", "init", e);
		}
	}
		
	@ResponseBody
	@RequestMapping(value = "/request/{uuid}", method = { RequestMethod.GET, RequestMethod.POST })
	public  Object request(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable(value = "uuid") String uuid
			) throws Exception{
		return web.action(request, response, uuid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/login")
	public  Object login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		MyResult rs = web.requestByDBSource(request, response, "hr", "myweb_login", MyJDBC.RETURN_LIST_MAP);
		if(rs.getRowCount() == 0){
			ResponseUtils.writeUtf8(response, "用户名密码错误");
			return null;
		}
		Long userid = (Long)rs.getLong(0, "id");
		SecurityUser.registerUser(request, userid);			
		
		I18n.setLanague(request, SysParams.getLanue());
		
		response.sendRedirect("/myweb/my/index.jsp");
		return null;
	}
	private String MergeResult(MyResult rs1, MyResult rs2, HttpServletRequest request) throws Exception{
		@SuppressWarnings("unchecked")
		List<List<Object>> ll1 = (List<List<Object>>)rs1.getResultObject();			
		
		for(int i=0; i<rs1.getRowCount(); i++){
			for(int j=0; j<rs2.getRowCount(); j++){
				if(rs1.getLong(i, "id") == rs2.getLong(j, "id")){
					List<Object> l1 = ll1.get(i);
					String enname = rs2.getString(j, "en_name");
					l1.add(enname);
				}
			}
		}
		
		return MyWebRequest.ConvertListPaginResult(ll1, ll1.size(), request);
	}
	private  String getIds(MyResult rs) throws Exception{ 
		String ids = "";
		for(int i=0; i<rs.getRowCount(); i++){
			if(!ids.equals("")){
				ids += ",";
			}
			ids += String.valueOf(rs.getLong(i, "id"));
		}
		
		return ids;
	}
	@ResponseBody
	@RequestMapping(value = "/dtcustom1")
	public  Object dtcustom1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MyResult rs1 = web.requestByUUID(request, response, "custom_query_1_1", 
				true, true, MyJDBC.RETURN_LIST_LIST);
		
		String ids = getIds(rs1);
		RequestUtils.putThreadParam(request, "t_ids", ids);
		MyResult rs2 = web.requestByUUID(request, response, "custom_query_1_2", 
				false, false, MyJDBC.RETURN_LIST_LIST);
		
		return MergeResult(rs1, rs2, request);
			
	}
	@ResponseBody
	@RequestMapping(value = "/dtcustom2")
	public  Object dtcustom2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String dbsource = RequestUtils.getParamString(request, "dbs"); 
		MyJDBC db = MyDBPool.getMyJDBC(dbsource);	
		
		String sql1 = "select id, name from wb_user";
		MyResult rs1 = db.queryReturnListList(sql1);
		
		String ids = getIds(rs1);
		String sql2 = "select id, en_name from wb_user where id in (" + ids + ")";
		
		SecurityUser.initColumnPrivilege(db, true);			
		
		MyResult rs2 = db.queryReturnListList(sql2);
		return MergeResult(rs1, rs2, request);
			
	}
	@ResponseBody
	@RequestMapping(value = "/dtcustom3")
	public  Object dtcustom3(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sql1 = "select id, name from wb_user";
		
		MyResult rs1 = web.requestBySql(request, response, sql1, true,
				true, MyJDBC.SELECT_SQL, MyJDBC.RETURN_LIST_LIST);
		
		//用于取得查询得到的字段
		String sql = "select id, en_name from wb_user where id in ({t_ids})";
		
		String ids = getIds(rs1);
		RequestUtils.putSessionParam(request, "t_ids", ids);		
		MyResult rs2 = web.requestBySqlWithParam(request, response, sql, false,
				false, MyJDBC.SELECT_SQL, MyJDBC.RETURN_LIST_LIST);
		return MergeResult(rs1, rs2, request);
	}
	@ResponseBody
	@RequestMapping(value = "/get_data_from_server")
	public  Object getDataFromServer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("rtype", "2");
		paramMap.put("act", "datatable");
		String url =  SysParams.getStringParam("testServerUrl") + "/myweb/request/ajax_datatable_get_user?dbs=hr&secretKey=123456";
		return HttpClientUtil.get(url, paramMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/get_data_from_server_clone_param")
	public  Object getDataFromServer2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String url = SysParams.getStringParam("testServerUrl") + "/myweb/request/ajax_datatable_get_user?secretKey=123456";
		return HttpClientUtil.get(url, request, null);
	}
}
