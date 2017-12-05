package com.mole.webengine.web.tld;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.resmanager.MenuInfo;
import com.mole.webengine.resmanager.MenuManager;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.utils.JsonUtils;
import com.mole.webengine.utils.RequestUtils;

@SuppressWarnings( { "serial" })
public class MyMenu extends MyBase {
	
	public void createMenu(MenuInfo menu, JSONObject jparent, boolean sub) throws Exception{
		
		JSONArray jmenu = new JSONArray();
		for(int i=0; i<menu.getSubMenu().size(); i++){
			MenuInfo m = menu.getSubMenu().get(i);
			JSONObject jobj = new JSONObject();			

			if(!SecurityUser.hasMenuPrivilege(m.getId())){
				continue;
			}
			jobj.put("id", "menu_" + m.getId());
			jobj.put("parentid", m.getParentid());
			jobj.put("caption", m.getCaption().trim());			
			jobj.put("url", m.getUrl().trim());
			String params = m.getParams().trim();
			if(!params.equals("")){
				JSONObject jparams = JsonUtils.jstring2JObject(params);
				Set<String> keys = jparams.keySet();
				Iterator<String> ite = keys.iterator();
				while(ite.hasNext()){
					String key = ite.next();
					jobj.put(key, jparams.get(key));
				}
			}
			jobj.put("templet", m.getTemplet());
			
			jmenu.add(jobj);
			if(sub && m.getSubMenu().size() > 0){
				createMenu(m, jobj, sub);
			}
		}		
		
		jparent.put("child", jmenu);
		jparent.put("content", "content");
	}
	@Override
	public int doStartTag() throws JspException {
		//MyDebug.println(this.getClass().getName(), "doStartTag", "uuid=", uuid);		
		try {
			long parentId = RequestUtils.getParamLong((HttpServletRequest)pageContext.getRequest(), "id");
			MenuInfo menu = MenuManager.getSubMenu(parentId);
			
			int sub = RequestUtils.getParamInt((HttpServletRequest)pageContext.getRequest(), "sub");
			JSONObject jroot = new JSONObject();
			createMenu(menu, jroot, sub == 1);
			
			String result = MyBase.createPortal(jroot, 0, null, ""); 
			
			pageContext.getOut().write(result);
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}
}
