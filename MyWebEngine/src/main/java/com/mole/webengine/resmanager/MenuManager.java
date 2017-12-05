package com.mole.webengine.resmanager;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;
import com.mole.webengine.utils.MyTimer;

public class MenuManager {
	//Long id
	private static HashMap<Long, MenuInfo> menus = null;
	private static MyTimer timer = new MyTimer();
	
	public synchronized static void init() throws Exception{
		if(menus != null && !timer.isRefreshTable()){
			return;	
		}
		//不使用clear，避免多线程冲突
		HashMap<Long, MenuInfo> mapMenus = new HashMap<Long, MenuInfo>();		
		getMenu(mapMenus, 0);
		menus = mapMenus;
		timer.startClocking();
	}
	private static MenuInfo getMenu(HashMap<Long, MenuInfo> mapMenus, long rootid) throws Exception{		
		String sql = "select * from wb_menu where is_delete = 0 order by show_order DESC";
		MyResult rs = SysParams.getSysdb().queryReturnListList(sql);
		
		MenuInfo entry = null;
		
		for(int i=0; i<rs.getRowCount(); i++){
			long id = rs.getLong(i, "id");
			long parentid = rs.getLong(i, "parentid");
			MenuInfo menu = null;
			if(mapMenus.containsKey(id)){
				menu = mapMenus.get(id);
			} else {
				menu = new MenuInfo();
				mapMenus.put(id, menu);
			}
			
			menu.setId(id);
			menu.setParentid(parentid);
			menu.setCaption(rs.getString(i, "caption"));
			menu.setUrl(rs.getString(i, "url"));
			menu.setParams(rs.getString(i, "params"));
			menu.setTemplet(rs.getString(i, "templet"));
			menu.setShowOrder(rs.getInt(i, "show_order"));

			MenuInfo parentMenu = null;
			if(mapMenus.containsKey(parentid)){
				parentMenu = mapMenus.get(parentid);
			}
			
			if(parentMenu == null){
				parentMenu = new MenuInfo();
				parentMenu.setId(parentid);
				mapMenus.put(parentid, parentMenu);
			}
			parentMenu.addSubMenu(menu);
			
			if(parentid == rootid){
				entry = parentMenu;
			}
		}
		return entry;
	}
	public static MenuInfo getRootMenu() throws Exception{
		return getSubMenu(0);
	}
	public static MenuInfo getSubMenu(long menuId) throws Exception{
		if(!SysParams.isUseSysCache()){
			//一次请求，访问的菜单会较多，所以重新初始化，而不是多次请求数据库
			init();
		}
		return menus.get(menuId);
		
	}
}
