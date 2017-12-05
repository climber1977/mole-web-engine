package com.mole.webengine.resmanager;

import java.util.HashMap;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;


public class ButtonManager {	
	private static HashMap<String, ButtonInfo> buttons = new HashMap<String, ButtonInfo>();
	
	private static ButtonInfo createButtonInfo(MyResult rs, int i) throws Exception{
		ButtonInfo info = new ButtonInfo();
		info.setId(rs.getLong(i, "id"));
		info.setUid(rs.getString(i, "uuid"));
		info.setName(rs.getString(i, "name"));
		info.setCaption(rs.getString(i, "caption"));
		info.setUrl(rs.getString(i, "url"));
		info.setHide(rs.getBoolean(i, "hide"));
		info.setTemplate(rs.getString(i, "template"));
		
		return info;
	}
	private synchronized static void getDataFromDB(HashMap<String, ButtonInfo> mapButtons, String sql) throws Exception{
		MyJDBC db = SysParams.getSysdb();

		MyResult rs = db.queryReturnListList(sql);
		for(int i=0; i<rs.getRowCount(); i++){
			ButtonInfo info = createButtonInfo(rs, i);
			mapButtons.put(info.getUid(), info);
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<String, ButtonInfo> mapButtons = new HashMap<String, ButtonInfo>();
		String sql = "select * from wb_button";
		getDataFromDB(mapButtons, sql);
		buttons = mapButtons;
	}
	
	public static ButtonInfo getButtonString(String uuid) throws Exception{
		if(SysParams.isUseSysCache()){
			return  buttons.get(uuid.toLowerCase());
		}
		
		String sql = "select * from wb_button where uuid='" + uuid + "'";
		getDataFromDB(buttons, sql);
		return  buttons.get(uuid.toLowerCase());
	}
}
