package com.mole.webengine.system;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.file.FileUtils;
import com.mole.webengine.utils.MyTimer;


public class SysParams {
	private final static int VALUE_TYPE_LONG = 0;	
	private final static int VALUE_TYPE_FlOAT = 1;
	private final static int VALUE_TYPE_STRING = 2;
	
	private static MyTimer timer = new MyTimer();
	
	private static String webRootPath = "";	
	public static  void setWebRootPath(String webRootPath) {
		SysParams.webRootPath = webRootPath;
	}

	private final static String javaJsDir = "WEB-INF/javajs";
	public static String getJavaJsPath(){
		return FileUtils.conjPaths(webRootPath, javaJsDir);
	}
	
	private final static String jsPluginPath = "WEB-INF/jsplugin";
	public static String getJavajsPluginPath(){
		return FileUtils.conjPaths(webRootPath, jsPluginPath);
	}
	
	private final static String template = "WEB-INF/template";
	public static String getTemplatePath(){
		return FileUtils.conjPaths(webRootPath, template);
	}
	public static String getTemplateFilePath(String fileName){
		return FileUtils.conjPaths(webRootPath, template, fileName);
	}
	
	private final static String jsonPath = "WEB-INF/json";
	public static String getJsonPath(){
		return FileUtils.conjPaths(webRootPath, jsonPath);
	}
	public static String getJsonPath(String fileName){
		return FileUtils.conjPaths(webRootPath, jsonPath, fileName);
	}

	public static String getWebRootPath() {
		return webRootPath;
	}

	public static String getUploadPath() {
		return FileUtils.conjPaths(webRootPath + "upload");
	}

	public static String getUploadTempPath() {
		return FileUtils.conjPaths(webRootPath, "uploadTemp");
	}
	
	public static MyJDBC getSysdb() throws Exception {
		return MyDBPool.getMyJDBC("system");
	}
	
	public static MyJDBC getdb(String dbsource) throws Exception {
		if(dbsource == null || dbsource.equals("")){
			return null;
		}
		return MyDBPool.getMyJDBC(dbsource);
	}
	
	private static GlobalContext global = new GlobalContext();
	public static GlobalContext getGlobalContext() {
		return SysParams.global;
	}
	private static HashMap<String, Object> params = new HashMap<String, Object>();	
	
	private static Object getValue(String key){
		return params.get(key.toLowerCase());
	}
	
	private static boolean useSysCacheFlag = false;
	public static boolean isUseSysCache() throws Exception{
		return useSysCacheFlag; 
	}
	
	public static boolean isUsePrivilege() throws Exception{
		return getBooleanParam("usePrivilege");
	}
	public static boolean isUseProxy() throws Exception{
		return getBooleanParam("useProxy");
	}
	public static boolean isUseSelectCahce() throws Exception{
		return getBooleanParam("useSelectCahce");
	}
	public static boolean isUseColumnPrivilege() throws Exception{
		return getBooleanParam("useColumnPrivilege");
	}
	public static boolean isServerPrivilege() throws Exception{
		return getBooleanParam("useServerPrivilege");
	}
	private static boolean isRefresh() throws Exception{
		if(params.isEmpty()){
			return true;
		}
		if(timer.isRefreshTable()){
			return true;			
		}
		return false;
	}
	public static void init() throws Exception{
		if(!isRefresh()){
			return;
		}
		String sql = "select * from wb_params";
		MyResult rs = getSysdb().queryReturnListList(sql);
		for(int i=0; i<rs.getRowCount(); i++){
			String key = rs.getString(i, "name");
			int type = rs.getInt(i, "type");
			String value = rs.getString(i, "value");
			Object obj = null;
			switch(type){
			case VALUE_TYPE_LONG:
				obj = Long.valueOf(value);
				break;
			case VALUE_TYPE_STRING:
				obj = value;
				break;
			case VALUE_TYPE_FlOAT:
				obj = Double.valueOf(value);
				break;
			}
			params.put(key.toLowerCase(), obj);
		}
		
		useSysCacheFlag = ((Long)getValue("useSysCacheFlag") != 0L);
		MyTimer.setRefreshTableSeconds((Long)getValue("refreshTablesSeconds"));
		
		timer.startClocking();
	}
	public static Object setParam(String key, Object value){		
		return params.put(key.toLowerCase(), value);
	}
	public static Object getParam(String key) throws Exception{
		if(!isUseSysCache()){
			init();
		}
		return params.get(key.toLowerCase());
	}
	
	public static String getStringParam(String key) throws Exception{		
		return (String)getParam(key);
	}
	public static Long getLongParam(String key) throws Exception{		
		return (Long)getParam(key);
	}
	public static Double getDoubleParam(String key) throws Exception{		
		return (Double)getParam(key);
	}	
	public static boolean getBooleanParam(String key) throws Exception{		
		return  ((Long)getParam(key) != 0L);
	}
	public static String getRequestUrl() throws Exception{
		return getStringParam("mainurl");
	}
	public static Long getLanue() throws Exception{
		return getLongParam("langue");
	}
	public static String getExportTemplatePath(){
		return FileUtils.conjPaths(webRootPath, "WEB-INF/config/export_template.xls");
	}
}
