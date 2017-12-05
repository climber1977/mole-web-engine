package com.mole.webengine.web.tld;

import javax.servlet.jsp.JspException;

import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.file.FileUtils;
import com.mole.webengine.file.FilesCache;
import com.mole.webengine.resmanager.PagePortManager;
import com.mole.webengine.utils.JsonUtils;

@SuppressWarnings( { "serial" })
public class MyPortal extends MyBase {
	
	private int index = 0;	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}	
	
	@Override
	public int doStartTag() throws JspException {
		try {	
			JSONObject jmain = null;
			if(getUuid() != null && !getUuid().isEmpty()){
				jmain = PagePortManager.getPortalJson(getUuid());				
			} else if(getJsonDesc() != null && !getJsonDesc().isEmpty()){
				String json = FilesCache.getJson(getJsonDesc());	
				jmain = JsonUtils.jstring2JObject(json);
			}
			String result = createPortal(jmain, index, null, "");

			pageContext.getOut().write(result);
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}
	
	public static void main(String args[]) throws Exception {		
		//String json = FileUtils.readToString("./data/my_portal_1.json", "UTF-8");
		//String json = FileUtils.readToString("./data/my_portal_2.json", "UTF-8");
		String json = FileUtils.readToStringDefaultChartSet("./data/my_portal_3.json");
		JSONObject jmain = JsonUtils.jstring2JObject(json);
		
		String result = MyBase.createPortal(jmain, 0, null, "");
		
		System.out.println(result);
	}
}
