package com.mole.webengine.web.tld;

import javax.servlet.jsp.JspException;

import com.alibaba.fastjson.JSONObject;
import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.resmanager.ButtonInfo;
import com.mole.webengine.resmanager.ButtonManager;
import com.mole.webengine.resmanager.I18n;
import com.mole.webengine.security.SecurityUser;

@SuppressWarnings({ "serial" })
public class MyButton extends MyBase {

	private String onClick;
	public String getOnClick() {
		return onClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
	private String id;	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String other;
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Override
	public int doStartTag() throws JspException {
		//MyDebug.println(this.getClass().getName(), "doStartTag", "lable=", lable);
		try {
			ButtonInfo button = ButtonManager.getButtonString(getUuid());			
			boolean access = SecurityUser.hasButtonPrivilege(button.getId());
			if(!access && button.getHide()){
				return SKIP_BODY;
			}
			
			String color = "";
			String disabled = ""; 
			//String onClick = "";	
			if(!onClick.equals("")){
				onClick = "onClick="+ onClick;
			}
			
			if(access){
				color = "blue";
				//onClick = "onClick=\"alert('hello')\"";
			} else if(!button.getHide()){
				color = "default";				
				disabled = "disabled=\"disabled\"";
			}
			String caption = I18n.getLangue(button.getCaption(), pageContext);
			
			JSONObject jroot = new JSONObject();
			jroot.put("id", id);			
			jroot.put("name", name);
			jroot.put("color", color);
			jroot.put("onClick", onClick);
			jroot.put("disabled", disabled);
			jroot.put("other", getOther());
			jroot.put("caption", caption);
			jroot.put("templet", "default_button.html");
			
			String html = createPortal(jroot, 0, null, ""); 						
			pageContext.getOut().write(html);
			
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}
}
