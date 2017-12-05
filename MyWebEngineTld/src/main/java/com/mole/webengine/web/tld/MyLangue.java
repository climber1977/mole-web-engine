package com.mole.webengine.web.tld;

import javax.servlet.jsp.JspException;

import com.mole.webengine.debug.MyDebug;
import com.mole.webengine.resmanager.I18n;


@SuppressWarnings( { "serial" })
public class MyLangue extends MyBase {

	private String lable;

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Override
	public int doStartTag() throws JspException {
		//MyDebug.println(this.getClass().getName(), "doStartTag", "lable=", lable);
		try {
			String word = I18n.getLangue(lable, pageContext);
			pageContext.getOut().write(word);
		} catch (Exception e) {
			MyDebug.dealException(this.getClass().getName(), "doStartTag",  e);
		}
		
		return SKIP_BODY;
	}

}
