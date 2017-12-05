package com.mole.webengine.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mole.webengine.dbapp.ExecutePageDatablesDescManager;
import com.mole.webengine.dbapp.ExecutePageDatablesManager;
import com.mole.webengine.dbapp.ExecuteSqlManager;
import com.mole.webengine.dbapp.ExecuteWhereSqlManager;
import com.mole.webengine.dbapp.ImportConfigManager;
import com.mole.webengine.dbcore.MyDBPool;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.file.FilesCache;
import com.mole.webengine.file.JSFileManager;
import com.mole.webengine.net.RouterTable;
import com.mole.webengine.resmanager.ButtonManager;
import com.mole.webengine.resmanager.I18n;
import com.mole.webengine.resmanager.MenuManager;
import com.mole.webengine.resmanager.PageChartManager;
import com.mole.webengine.resmanager.PageManager;
import com.mole.webengine.resmanager.PagePortItemManager;
import com.mole.webengine.resmanager.PagePortItemSelectItemManager;
import com.mole.webengine.resmanager.PagePortManager;
import com.mole.webengine.security.AuthorizedServerPrivileges;
import com.mole.webengine.security.ButtonPrivileges;
import com.mole.webengine.security.ColumnPrivileges;
import com.mole.webengine.security.CompentPrivileges;
import com.mole.webengine.security.MenuPrivileges;
import com.mole.webengine.security.Resource;
import com.mole.webengine.security.RolePrivileges;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.security.UserPrivileges;
import com.mole.webengine.security.UserRoleRelation;
import com.mole.webengine.system.GlobalContext;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.ClsTool;
import com.mole.webengine.utils.PluginManager;
import com.mole.webengine.utils.RequestUtils;
import com.mole.webengine.utils.ResponseUtils;

public abstract class Starter {
	private static void cacheInit() throws Exception{
		SysParams.init();
		
		Resource.init();
		RolePrivileges.init();
		UserPrivileges.init();
		
		ButtonManager.init();
		ButtonPrivileges.init();
		
		ColumnPrivileges.init();
		CompentPrivileges.init();
		
		MenuManager.init();
		MenuPrivileges.init();
		
		RouterTable.init();
		
		PageManager.init();
		PagePortManager.init();
		PageChartManager.init();
		PagePortItemManager.init();
		PagePortItemSelectItemManager.init();
		
		ExecuteSqlManager.init();
		ExecutePageDatablesManager.init();
		ExecutePageDatablesDescManager.init();
		ExecuteWhereSqlManager.init();
		
		UserRoleRelation.init();
		ImportConfigManager.init();
		
		AuthorizedServerPrivileges.init();
		
		I18n.init();
	}
	public static void init(WebRequest request, HashMap<String, DataSource> pools, 
			String webRootPath) throws Exception{
		SysParams.setWebRootPath(webRootPath);
		PluginManager.init(webRootPath);
		
		MyDBPool.init(pools);
		cacheInit();
		
		GlobalContext.setGlobal("test", "hello world");
		GlobalContext.setGlobal("debug", new MyDebug());
		GlobalContext.setGlobal("RequestUtils", new RequestUtils());
		GlobalContext.setGlobal("ResponseUtils", new ResponseUtils());
		GlobalContext.setGlobal("WebRequest", request);
		GlobalContext.setGlobal("SecurityUser", new SecurityUser());
		
		FilesCache.loadFiles(SysParams.getTemplatePath());
		FilesCache.loadFiles(SysParams.getJsonPath());	
		
		JSFileManager.loadJSFiles("javajs", SysParams.getJavaJsPath());
		JSFileManager.loadJSFiles("jsplugin", SysParams.getJavajsPluginPath());
	}
	public static String refreshCache(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String cachecls = RequestUtils.getParamString(request, "cachecls"); 
		if(cachecls.isEmpty()){
			cacheInit();
		}
		else{
			ClsTool.call(cachecls, "init");
		}
		return "{\"ret\":0}";
	}
}
