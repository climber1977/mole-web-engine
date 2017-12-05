package com.mole.webengine.dbapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.dbcore.MyResult;
import com.mole.webengine.system.SysParams;

public class ExecutePageDatablesDescManager {
	private static HashMap<Long, List<ExecutePageDatablesDescInfo>> descs = new HashMap<Long, List<ExecutePageDatablesDescInfo>>();

	private static ExecutePageDatablesDescInfo createInfo(MyResult rs, int i) throws Exception{
		ExecutePageDatablesDescInfo info = new ExecutePageDatablesDescInfo();
		info.setId(rs.getLong(i, "id"));		
		info.setGroupid(rs.getLong(i, "groupid"));
		info.setCheckbox(rs.getBoolean(i, "checkbox"));
		info.setSorting(rs.getBoolean(i, "sorting"));
		info.setWidth(rs.getString(i, "width"));
		info.setField(rs.getString(i, "field"));
		info.setAlias(rs.getString(i, "alias"));
		info.setHtml(rs.getString(i, "html"));
		info.setTrTemplate(rs.getString(i, "tr_template"));
		info.setTdTemplate(rs.getString(i, "td_template"));
		info.setShowOrder(rs.getInt(i, "show_order"));
		
		return info;
	}
	//clean 是否需要清除已有数据，实际上是只有取到分组的第一条数据时才需要清除数据
	private static void put(HashMap<Long, List<ExecutePageDatablesDescInfo>> m, ExecutePageDatablesDescInfo info, boolean clean) throws Exception{
		List<ExecutePageDatablesDescInfo> lst = null;
		if(m.containsKey(info.getGroupid())){
			lst = m.get(info.getGroupid());	
			if(clean){
				lst.clear();
			}
		} else {
			lst = new ArrayList<ExecutePageDatablesDescInfo>();
			m.put(info.getGroupid(), lst);
		}
		lst.add(info);
	}
	private synchronized static void getDataFromDB(HashMap<Long, List<ExecutePageDatablesDescInfo>> m, String sql, boolean clean) throws Exception{
		MyJDBC db = SysParams.getSysdb();
		MyResult rs = db.queryReturnListList(sql);
		ExecutePageDatablesDescInfo info = null;
		for(int i=0; i<rs.getRowCount(); i++){
			info = createInfo(rs, i);
			put(m, info, clean);
			clean = false;
		}
	}
	public static void init() throws Exception{
		//不使用clear，避免多线程冲突
		HashMap<Long, List<ExecutePageDatablesDescInfo>> m = new HashMap<Long, List<ExecutePageDatablesDescInfo>>();
		String sql = "select * from wb_page_datatables_desc order by groupid, show_order";
		getDataFromDB(m, sql, false);
		descs = m;
	}
	
	public static List<ExecutePageDatablesDescInfo> getDatableDescByGroupid(long groupid) throws Exception{
		if(SysParams.isUseSysCache()){
			if(descs.containsKey(groupid)){
				return descs.get(groupid);
			}
		}

		String sql = "select * from wb_page_datatables_desc " +
				" where groupid=" + groupid + " order by show_order";
		getDataFromDB(descs, sql, true);
				
		return descs.get(groupid);
	}
}
