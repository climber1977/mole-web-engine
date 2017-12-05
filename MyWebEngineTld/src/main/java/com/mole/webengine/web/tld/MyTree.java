package com.mole.webengine.web.tld;

import javax.servlet.jsp.JspException;

import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.dbcore.MyJDBC;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.security.SecurityUser;
import com.mole.webengine.utils.PageContextUtils;

@SuppressWarnings( { "serial" })
public class MyTree extends MyBase {	
	private String rootid;
	public String getRootid() {
		return rootid;
	}

	public void setRootid(String rootid) {
		this.rootid = rootid;
	}
	public String createTree(MyJDBC db, String uuid, SecurityUser u) throws Exception{	
		String url = getUrl(uuid, "");
		
		JSONObject jroot = new JSONObject();
		jroot.put("uuid", uuid);
		jroot.put("url", url);
		jroot.put("root", rootid);
		jroot.put("templet", "default_tree.html");
		
		String result = MyBase.createPortal(jroot, 0, null, ""); 
		return result;
	}
	@Override
	public int doStartTag() throws JspException {
		//MyDebug.println(this.getClass().getName(), "doStartTag", "uuid=", uuid);		
		String result;
		try {
			MyJDBC db = (MyJDBC)PageContextUtils.getAttribute(pageContext, "db");
			SecurityUser u = SecurityUser.getUser();
			result = createTree(db, getUuid(), u);
			pageContext.getOut().write(result);
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}
}
